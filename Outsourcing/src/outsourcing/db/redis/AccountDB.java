package outsourcing.db.redis;

import java.util.Date;

import outsourcing.exceptions.InvalidUserNameException;
import outsourcing.exceptions.TransactionCollisionException;
import outsourcing.model.Account;
import outsourcing.utils.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * �˻������ݿ����
 * @version 1.2.1
 */
public class AccountDB {
	public static final int expireTime = 30*60;//����ʱ�� ���Сʱ
	
	/**
	 * ������id,�����µ�id�����˻�,�������������account�����зǿ��ֶε�redis��
	 * ���ע��������,�ֻ�����,���½��ʱ�����ʹ����������ֻ������½
	 * @param account
	 * @throws TransactionCollisionException
	 * @throws InvalidUserNameException 
	 */
	public void Register(Account account) throws TransactionCollisionException,InvalidUserNameException{
		//getting the connection to redis
		Jedis jedis = JedisConnectionPool.getJedisConnection();		
		//lock the max uid
		jedis.watch("User:maxUid");
		String uid = jedis.get("User:maxUid");
		//begin the transaction
		Transaction trans = jedis.multi();
		if(uid==null){//initialize User:maxUid
			trans.set("User:maxUid", "1");
			uid = "1";
		}
		//mapping the uid with userName
		trans.set("User:"+uid+":userName", account.getUserName());//required
		trans.set("User:userName:"+account.getUserName(), uid);
		//mapping the uid with email
		if(!StringUtils.isEmpty(account.getEmail())){
			trans.set("User:"+uid+":email", account.getEmail());
			trans.set("User:email:"+account.getEmail(), uid);
		}
		//mapping the uid with phoneNumber
		if(!StringUtils.isEmpty(account.getPhoneNumber())){
			trans.set("User:"+uid+":phoneNumber", account.getPhoneNumber());
			trans.set("User:phoneNumber:"+account.getPhoneNumber(), uid);
		}
		trans.incr("User:maxUid");
		if(trans.exec()==null){
			jedis.disconnect();
			throw new TransactionCollisionException("��⵽������ײ");
		}		
		//end of the transaction
		jedis.unwatch();
		
		account.setUserID(uid);
		//fill the other attributes of account
		SaveToRedis(account);
		
		//release the connection
		jedis.disconnect();
	}
	
	/**
	 * �����˻�,ֻ����account�����зǿյ��ֶε�redis���ݿ���
	 * ͨ��account��uid����username����email����phoneNumber��ָ���û�
	 * @param account
	 * @throws InvalidUserNameException
	 */
	public void Update(Account account) throws InvalidUserNameException{
		//getting the connection to redis
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		String uid = account.getUserID();
		//if the user is specified by username
		if(StringUtils.isEmpty(uid) && !StringUtils.isEmpty(account.getUserName())){
			uid = getUidByAllMeans(account);
		}
		//invalid account if uid is still null
		if(StringUtils.isEmpty(uid)){
			jedis.disconnect();
			throw new InvalidUserNameException("invalid account!");
		}
		////fill the other attributes of account
		SaveToRedis(account);
		//release the connection
		jedis.disconnect();
	}
	
	/**
	 * ����˺�(�û���/��������/�ֻ���)�Ƿ����
	 * @param account this argument could be userName,email or phoneNumber
	 * @return return true if the userName exists,otherwise return false.
	 */
	public boolean Exist(String account){
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		//get the uid from mapped uid--userName
		String uid = jedis.get("User:userName:"+account);		
		if(uid==null){
			uid = jedis.get("User:email:"+account);
		}
		if(uid==null){
			uid = jedis.get("User:phoneNumber:"+account);
		}
		//release the connection
		jedis.disconnect();
		
		return uid!=null;
	}
	
	public String getUidByAllMeans(String account){
		String uid = null;
		
		uid = getUidByUserName(account);
		if(uid==null){
			uid = getUidByEmail(account);
		}
		if(uid==null){
			uid = getUidByPhoneNumber(account);
		}
		return uid;
	}
	
	private String getUidByAllMeans(Account account){
		String uid = null;
		//if the user is specified by username
		if(StringUtils.isEmpty(uid) && !StringUtils.isEmpty(account.getUserName())){
			uid = getUidByUserName(account.getUserName());
		}
		//if the user is specified by email
		if(StringUtils.isEmpty(uid) && !StringUtils.isEmpty(account.getEmail())){
			uid = getUidByEmail(account.getEmail());
		}
		//if the user is specified by phoneNumber
		if(StringUtils.isEmpty(uid) && !StringUtils.isEmpty(account.getPhoneNumber())){
			uid = getUidByPhoneNumber(account.getPhoneNumber());
		}
		return uid;
	}
	
	public String getUidByEmail(String email){
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		//get the uid from mapped uid--userName
		String uid = jedis.get("User:email:"+email);		
		//release the connection
		jedis.disconnect();
		
		return uid;
	}
	
	public String getUidByUserName(String userName){
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		//get the uid from mapped uid--userName
		String uid = jedis.get("User:userName:"+userName);		
		//release the connection
		jedis.disconnect();
		
		return uid;
	}
	
	public String getUidByPhoneNumber(String phoneNumber){
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		//get the uid from mapped uid--userName
		String uid = jedis.get("User:phoneNumber:"+phoneNumber);	
		//release the connection
		jedis.disconnect();
		
		return uid;
	}
	
	/**
	 * verifying the username with password
	 * @param userName this argument could be username,email or phoneNumber
	 * @param password
	 * @return true if passed verification, false if failed to passed the verification
	 * @throws InvalidUserNameException if the username does not exist
	 */
	public boolean Login(String userName,String password) throws InvalidUserNameException{
		boolean succeed = false;
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		
		String uid = getUidByAllMeans(userName);
		if(uid==null)
			throw new InvalidUserNameException("�û���������!");
		String pwd = jedis.get("User:"+uid+":loginPassword");
		
		if(pwd.equals(password))
			succeed = true;
		
		jedis.disconnect();
		return succeed;
	}
	
	/**
	 * account.UserID can't be null,if the UserID is null it throws 
	 * the InvalidUserNameException
	 * @param account
	 * @throws InvalidUserNameException 
	 */
	private void SaveToRedis(Account account) throws InvalidUserNameException{
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		String uid = account.getUserID();
		if(StringUtils.isEmpty(uid)){
			jedis.disconnect();
			throw new InvalidUserNameException("invalid account id");
		}
		if(!StringUtils.isEmpty(account.getLoginPassword()))
			jedis.set("User:"+uid+":loginPassword", account.getLoginPassword());
		if(account.getBirthday()!=null)
			jedis.set("User:"+uid+":birthday", String.valueOf(account.getBirthday()));
		if(!StringUtils.isEmpty(account.getRealName()))
			jedis.set("User:"+uid+":realName", account.getRealName());
		if(0!=account.getAge())
			jedis.set("User:"+uid+":age", String.valueOf(account.getAge()));
		if(!StringUtils.isEmpty(account.getGender()))
			jedis.set("User:"+uid+":gender", account.getGender());
		if(!StringUtils.isEmpty(account.getUserImage()))
			jedis.set("User:"+uid+":userImage", account.getUserImage());
		if(!StringUtils.isEmpty(account.getAddress()))
			jedis.set("User:"+uid+":address", account.getAddress());
		if(!StringUtils.isEmpty(account.getAcademy()))
			jedis.set("User:"+uid+":academy", account.getAcademy());
		if(!StringUtils.isEmpty(account.getGrade()))
			jedis.set("User:"+uid+":grade", account.getGrade());
		if(!StringUtils.isEmpty(account.getPhoneNumber())){
			jedis.set("User:"+uid+":phoneNumber", account.getPhoneNumber());
			jedis.set("User:phoneNumber:"+account.getPhoneNumber(), uid);
		}
		jedis.disconnect();
	}

	/**
	 * ��redis�ж�ȡ�˻���Ϣ
	 * ʹ�ô˷�������ȷ��uid����,�����׳��쳣
	 * @param uid
	 * @return
	 * @throws InvalidUserNameException
	 */
	public Account getAccountByUid(String uid) throws InvalidUserNameException{
		return ReadFromRedis(uid);
	}
	
	/**
	 * ����username email phoneNumber��ȡ�˻���Ϣ
	 * @param account
	 * @return
	 * @throws InvalidUserNameException
	 */
	public Account getAccount(String account) throws InvalidUserNameException{
		String uid = getUidByAllMeans(account);
		if(StringUtils.isEmpty(uid)){
			throw new InvalidUserNameException("the user does not exist");
		}
		return getAccountByUid(uid);
	}
	
	private Account ReadFromRedis(String uid) throws InvalidUserNameException{
		Account account = null;
		//uid can't be null
		if(StringUtils.isEmpty(uid))
			throw new IllegalArgumentException("uid can't be null");
		//connect to db
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		//check the uid exist in the db 
		if(!jedis.exists("User:"+uid+":loginPassword")){
			jedis.disconnect();
			throw new InvalidUserNameException("invalid account");
		}
		//generate new account object
		account = new Account();
		account.setUserID(uid);
		account.setUserName(jedis.get("User:"+uid+":userName"));
		account.setLoginPassword(jedis.get("User:"+uid+":loginPassword"));
		String birthday = jedis.get("User:"+uid+":birthday");
		if(!StringUtils.isEmpty(birthday)){
			Date d = new Date(birthday);
			account.setBirthday(d);
		}
		account.setRealName(jedis.get("User:"+uid+":realName"));
		String age = jedis.get("User:"+uid+":age");
		if(!StringUtils.isEmpty(age)){
			account.setAge(Integer.valueOf(age));
		}
		account.setGender(jedis.get("User:"+uid+":gender"));
		account.setUserImage(jedis.get("User:"+uid+":userImage"));
		account.setAddress(jedis.get("User:"+uid+":address"));
		account.setEmail(jedis.get("User:"+uid+":email"));
		account.setPhoneNumber(jedis.get("User:"+uid+":phoneNumber"));
		account.setAcademy(jedis.get("User:"+uid+":academy"));
		account.setGrade(jedis.get("User:"+uid+":grade"));
		//release the connection
		jedis.disconnect();
		return account;
	}
	
	/**
	 * �û��һ�������߸�������ʱ ���ô˺����������Ӵ������ݿ� �Ա���в�֤
	 * ͨ��emailȡ������  ��������  ���������ɵ�timestamp���û���uid��Ӧ �������ݿ�
	 * �û�ͨ����������Ӻ�  �����timestamp�����ҵ�Ψһ��uid��֮��Ӧ
	 * ����������ʧЧʱ�䵽�� �Զ�ɾ����Ӧ��ϵ��
	 * @param email
	 * @return
	 * @throws InvalidUserNameException
	 */
	public String GenerateTempLink2Uid(String email) throws InvalidUserNameException{
		
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		
		String uid = getUidByEmail(email);
		if(uid==null)
			throw new InvalidUserNameException("�����ڴ��˻�!");
		
		Date d = new Date();
		String timestamp = String.valueOf(d.getTime());
		
		jedis.set("TempLink:"+timestamp+":uid", uid);
		jedis.set("TempLink:"+timestamp+":generateTime", d.toLocaleString());
		
		jedis.expire("TempLink:"+timestamp+":uid", expireTime);
		jedis.expire("TempLink:"+timestamp+":generateTime",expireTime);
		
		jedis.disconnect();
		return timestamp;
	}
	
	/**
	 * �һ�����ʱ ����
	 * ���û����ʼ��е�����  ���Բ������url���timestamp
	 * ͨ��timestamp����ȡ�����һ�������߸�������������û� uid
	 * @param timestamp
	 * @return ��������Ŀͻ�uid
	 */
	public String getUidByTempLink(String timestamp){
		String uid = null;
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		
		uid = jedis.get("TempLink:"+timestamp+":uid");
		
		jedis.disconnect();
		return uid;
	}
	
	public static void main(String[] args){
		AccountDB db = new AccountDB();
		Account acc = new Account();
//		acc.setUserName("abc");
//		acc.setLoginPassword("123");
//		acc.setAge(1);
//		acc.setEmail("abc@qq.com");
//		acc.setGender("��");
		acc.setUserID("1");
		acc.setBirthday(new Date());
		try {
			db.Update(acc);
			//db.Login(acc.getUserName(), acc.getLoginPassword());
			Account acc_get = db.getAccountByUid("1");
			System.out.println(acc_get);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
