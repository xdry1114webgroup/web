package outsourcing.db.redis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import outsourcing.exceptions.TransactionCollisionException;
import outsourcing.model.Grocery;
import outsourcing.model.Outsourcing;
import outsourcing.model.Paging;
import outsourcing.utils.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Tuple;

public class OutsourcingDB {
	
	/**
	 * 获取到指定服务类别的所有科目
	 * @param type
	 * @return
	 */
	public Grocery[] getGroceries(String type){
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		
		Map<String, String> types = jedis.hgetAll("type:"+type);	
		Set<String> keys = types.keySet();
		List<Grocery> groceries = new ArrayList<Grocery>();
		for(String key:keys){
			Grocery grocery = new Grocery();
			grocery.setGroceryType(key);
			grocery.setItems(getItems(key));
			groceries.add(grocery);
		}
		jedis.disconnect();		
		return groceries.toArray(new Grocery[groceries.size()]);
	}
	
	/**
	 * 设置指定服务类别的所有科目
	 * @param type
	 * @param groceries
	 */
	public void setGroceries(String type,Grocery[] groceries){
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		
		for(int i=0;i<groceries.length;i++){
			Grocery grocery = groceries[i];
			jedis.hset("type:"+type, grocery.getGroceryType(), String.valueOf(i));
			setItems(grocery.getGroceryType(),grocery.getItems());
		}
		
		jedis.disconnect();
		
	}
	
	/**
	 * 设置服务科类
	 * @param grocery
	 * @param items
	 */
	public void setItems(String grocery,String[] items){
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		for(int i=0;i<items.length;i++){
			String item = items[i];
			jedis.hset("grocery:"+grocery, item,String.valueOf(i));
		}
		jedis.disconnect();	
	}
	
	/**
	 * 获取服务科类
	 * @param grocery
	 * @return
	 */
	public String[] getItems(String grocery){
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		Map<String,String> groceries = jedis.hgetAll("grocery:"+grocery);
		jedis.disconnect();	
		Set<String> keys = groceries.keySet();
		return keys.toArray(new String[keys.size()]);
	}
	
	/**
	 * 产生需求订单编号
	 * @return 编号
	 */
	public String generateID(){
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		//lock the max uid
		jedis.watch("Outsourcing:maxOid");
		String oid = jedis.get("Outsourcing:maxOid");
		//begin the transaction
		Transaction trans = jedis.multi();
		if(oid==null){//initialize User:maxUid
			trans.set("Outsourcing:maxOid", "1");
			oid = "1";
		}
		trans.incr("Outsourcing:maxOid");
		jedis.disconnect();
		return oid;
	}
	
	/**
	 * 发布需求订单 传入对象必须有oid字段
	 * @param os outsourcing object
	 * @throws TransactionCollisionException
	 */
	public void addOutsourcing(Outsourcing os) throws TransactionCollisionException{
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		String oid = os.getTaskID();
		if(StringUtils.isEmpty(oid))
			throw new IllegalArgumentException("TaskID字段不能为空");
		
		jedis.set("Outsourcing:"+oid+":releaseUser", os.getReleaseUser());
		jedis.set("Outsourcing:"+oid+":taskTitle", os.getTaskTitle());
		jedis.set("Outsourcing:"+oid+"taskInfo", os.getTaskInfo());
		jedis.set("Outsourcing:"+oid+":taskPay",String.valueOf(os.getTaskPay()));
		jedis.set("Outsourcing:"+oid+":isComplete",String.valueOf(false));
		if(!StringUtils.isEmpty(os.getTaskPlace()))
			jedis.set("Outsourcing:"+oid+":taskPlace", os.getTaskPlace());
		if(null!=os.getReleaseTime())
			jedis.set("Outsourcing:"+oid+":releaseTime", String.valueOf(os.getReleaseTime()));
		if(null!=os.getDeadline())
			jedis.set("Outsourcing:"+oid+":deadline", String.valueOf(os.getDeadline()));
		jedis.set("Outsourcing:"+oid+":collectValue", "0");
		if(StringUtils.isEmpty(os.getType()))
			throw new IllegalArgumentException("Type字段不能为空");
		jedis.set("Outsourcing:"+oid+":type", os.getType());
		if(StringUtils.isEmpty(os.getGrocery()))
			throw new IllegalArgumentException("Grocery字段不能为空");
		jedis.set("Outsourcing:"+oid+":grocery", os.getGrocery());
		if(StringUtils.isEmpty(os.getItem()))
			throw new IllegalArgumentException("Item字段不能为空");
		jedis.set("Outsourcing:"+oid+":item", os.getItem());
		if(!StringUtils.isEmpty(os.getAttachURL()))
			jedis.set("Outsourcing:"+oid+":attachURL", os.getAttachURL());
		if(!StringUtils.isEmpty(os.getQq()))
			jedis.set("Outsourcing:"+oid+":qq", os.getQq());
		if(!StringUtils.isEmpty(os.getTel()))
		jedis.set("Outsourcing:"+oid+":tel", os.getTel());
		//mapping the item with task id;
		mapping(jedis,os);
		
		jedis.disconnect();
	}
	
	private void mapping(Jedis jedis,Outsourcing os){
		
//		jedis.lpush("tasklist:item:"+os.getItem(), os.getTaskID());
//		jedis.lpush("tasklist:grocery:"+os.getGrocery(), os.getTaskID());
//		jedis.lpush("tasklist:type:"+os.getType(), os.getTaskID());
		
		jedis.zadd("tasklist:type:全部", Double.valueOf(os.getTaskID()),os.getTaskID());
		jedis.zadd("tasklist:type:"+os.getType(),Double.valueOf(os.getTaskID()),os.getTaskID());
		jedis.zadd("tasklist:location:全部", Double.valueOf(os.getTaskID()),os.getTaskID());
		jedis.zadd("tasklist:location:"+os.getTaskPlace(), Double.valueOf(os.getTaskID()),os.getTaskID());
		jedis.zadd("tasklist:releaseTime:全部", Double.valueOf(os.getTaskID()),os.getTaskID());
		jedis.zadd("tasklist:releaseTime:"+os.getReleaseTime(), Double.valueOf(os.getTaskID()),os.getTaskID());
		jedis.zadd("tasklist:pay:全部", Double.valueOf(os.getTaskID()),os.getTaskID());
		
		if(os.getTaskPay()<100)
			jedis.zadd("tasklist:pay:<100", Double.valueOf(os.getTaskID()),os.getTaskID());
		else if(os.getTaskPay()<500)
			jedis.zadd("tasklist:pay:100-500", Double.valueOf(os.getTaskID()),os.getTaskID());
		else if(os.getTaskPay()<=1000)
			jedis.zadd("tasklist:pay:500-1000", Double.valueOf(os.getTaskID()),os.getTaskID());
		else
			jedis.zadd("tasklist:pay:>1000", Double.valueOf(os.getTaskID()),os.getTaskID());
	}
	
	/**
	 * 根据小类别来获取一部分外包事务列表的一小段
	 * @param type 大类别的名称
	 * @param page_index 第几页显示 从第一页开始
	 * @param count_per_page 一张页面显示几个外包事务
	 * @return
	 */
	public Outsourcing[] getTasksGroupByType(String type,Paging page){
		Jedis jedis = JedisConnectionPool.getJedisConnection();
//		List<String> taskids = jedis.lrange("tasklist:item:"+type, 
//				count_per_page*(page_index-1), count_per_page*page_index-1);
		
		
		ScanParams params = new ScanParams();
		int count = page.getRecord_per_page();
		String cursor = String.valueOf(page.getCursor());
		params.count(count);
		
		ScanResult<Tuple> scan = jedis.zscan("tasklist:type:"+type, cursor, params);
		System.out.println(scan);
		cursor = scan.getStringCursor();
		page.setCursor(Integer.valueOf(cursor));
		List<Outsourcing> outsourcings = new ArrayList<Outsourcing>();
		for(Tuple tuple:scan.getResult()){
			String taskid = tuple.getElement();
			Outsourcing os = getOutsourcingById(taskid);
			outsourcings.add(os);
		}
		jedis.disconnect();
		return outsourcings.toArray(new Outsourcing[outsourcings.size()]);
	}
	
	/**
	 * 根据筛选条件筛选外包事务
	 * @param jedis
	 * @param condition
	 * @param count
	 * @param cursor
	 * @return
	 */
	public List<Outsourcing> viewOutsourcing(Map<String,String> condition,
			Paging page){
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		
		String type = condition.get("type");
		if(StringUtils.isEmpty(type))
			type = "全部";
		String location = condition.get("location");
		if(StringUtils.isEmpty(location))
			location = "全部";
		String releaseTime = condition.get("releaseTime");
		if(StringUtils.isEmpty(releaseTime))
			releaseTime = "全部";
		String pay = condition.get("pay");
		if(StringUtils.isEmpty(pay))
			pay = "全部";
		
		
		String key = "tasklist:"+type+"_"+location+"_"+releaseTime+"_"+pay;
		
		if(!jedis.exists(key)){
			jedis.zinterstore(key,
				"tasklist:type:"+type,
				"tasklist:location:"+location,
				"tasklist:releaseTime:"+releaseTime,
				"tasklist:pay:"+pay);
		}
		ScanParams params = new ScanParams();
		int count = page.getRecord_per_page();
		String cursor = String.valueOf(page.getCursor());		
		params.count(count);
		ScanResult<Tuple> res = jedis.zscan(key, cursor, params);
		cursor = res.getStringCursor();
		page.setCursor(Integer.valueOf(cursor));
		
		List<Outsourcing> tasklist = new ArrayList<Outsourcing>();
		for(Tuple tuple:res.getResult()){
			String oid = tuple.getElement();
			Outsourcing os = getOutsourcingById(oid);
			tasklist.add(os);
		}
		jedis.disconnect();
		return tasklist;
	}
	
	/**
	 * 根据任务id来获取外包事务
	 * @param taskid
	 * @return
	 */
	public Outsourcing getOutsourcingById(String taskid){
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		
		Outsourcing os = new Outsourcing();
		os.setTaskID(taskid);
		os.setReleaseUser(jedis.get("Outsourcing:"+taskid+":releaseUser"));
		os.setTaskTitle(jedis.get("Outsourcing:"+taskid+":taskTitle"));
		os.setTaskInfo(jedis.get("Outsourcing:"+taskid+"taskInfo"));
		os.setTaskPay(Double.valueOf(jedis.get("Outsourcing:"+taskid+":taskPay")));
		os.setComplete(Boolean.valueOf(jedis.get("Outsourcing:"+taskid+":isComplete")));
		os.setTaskPlace(jedis.get("Outsourcing:"+taskid+":taskPlace"));
		os.setReleaseTime(new Date(jedis.get("Outsourcing:"+taskid+":releaseTime")));
		os.setDeadline(new Date(jedis.get("Outsourcing:"+taskid+":deadline")));
		os.setCollectValue(Integer.valueOf(jedis.get("Outsourcing:"+taskid+":collectValue")));
		os.setType(jedis.get("Outsourcing:"+taskid+":type"));
		os.setGrocery(jedis.get("Outsourcing:"+taskid+":grocery"));
		os.setItem(jedis.get("Outsourcing:"+taskid+":item"));
		os.setAttachURL(jedis.get("Outsourcing:"+taskid+":attachURL"));
		os.setQq(jedis.get("Outsourcing:"+taskid+":qq"));
		os.setTel(jedis.get("Outsourcing:"+taskid+":tel"));
		
		jedis.disconnect();
		return os;
	}
	
	public void test(){
		Jedis jedis =  JedisConnectionPool.getJedisConnection();
		Set<String> sets = jedis.keys("tasklist:location*");
		sets.addAll(jedis.keys("tasklist:type*"));
		sets.addAll(jedis.keys("tasklist:rel*"));
		sets.addAll(jedis.keys("tasklist:p*"));
		for(String key:sets){
			//jedis.del(key);
			System.out.println(key);
			System.out.println(jedis.zrange(key, 0, -1));
		}
		jedis.disconnect();
	}
		
	public static void main(String[] args){
		
//		Jedis jedis = JedisConnectionPool.getJedisConnection();
//		System.out.println();
//		for(String key:jedis.keys("tasklist:*")){
//			jedis.del(key);
//		}
//		jedis.disconnect();
		
		OutsourcingDB db = new OutsourcingDB();
		
		Outsourcing os = new Outsourcing();
		String oid = "1";//db.generateID();
		os.setTaskID(oid);
		os.setReleaseUser("xido");
		os.setTaskTitle("title");
		os.setTaskInfo("info");
		os.setTaskPay(90.0);
		os.setTaskPlace("西电");
		os.setReleaseTime(new Date());
		os.setDeadline(new Date());
		os.setType("排队服务");
		os.setGrocery("排队买票");
		os.setItem("代抢火车票");
		
		
		try {
			//db.addOutsourcing(os);
			//os = db.getOutsourcing(oid);
//			Paging page = new Paging(10,0);
//			Outsourcing[] oss = db.getTasksGroupByType("排队服务",page);
//			Map<String,String> condition = new HashMap<String,String>();
//			Outsourcing[] oss = db.viewOutsourcing(condition, page);
//			System.out.println(oss);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(oid);
	}
}
