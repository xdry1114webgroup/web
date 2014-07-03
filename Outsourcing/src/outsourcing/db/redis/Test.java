package outsourcing.db.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.TransactionBlock;



public class Test {

	private JedisPool pool;
	private Jedis jedis;
	
	public void SetUp(){
		pool = new JedisPool(new JedisPoolConfig(),"127.0.0.1");
		
		jedis = pool.getResource();
		jedis.auth("abc123");		
//		TransactionBlock jedisTransaction = null;
//		jedis.multi(jedisTransaction);
		
	}
	
	public void testBasicString(){
		//----------------------------�����������----------------------------
		jedis.set("name", "huang");
		System.out.println("key-->name:"+jedis.get("id"));
				
		//----------------------------�����޸�����----------------------------
		jedis.append("name", "lipeng");
		System.out.println("key-->name:"+jedis.get("name"));
		
		//----------------------------��������----------------------------
		jedis.set("name", "������");
		System.out.println("key-->name"+jedis.get("name"));
		
		//----------------------------ɾ������----------------------------
		jedis.del("name");
		System.out.println("key-->name:"+jedis.get("name"));//�������null
		
		//----------------------------��Ӷ������----------------------------
		jedis.mset("name","huanglipeng","id","13111329","title","student");
		List<String> rsmap = jedis.mget("name","id","title");
		System.out.println(rsmap);
	}
	
	public void testMap(){
		Map<String,String> user = new HashMap<String,String>();
		//----------------------------����Map����----------------------------
		user.put("name", "huanglipeng");
		user.put("password", "123456789");
		user.put("title", "student");
		jedis.hmset("user", user);
		
		//----------------------------��ȡMap����----------------------------
		List<String> rsmap = jedis.hmget("user", "name","password","title");
		System.out.println(rsmap);
		
		//--------------------------ɾ��Map�е�ĳ����ֵ-----------------------
		jedis.hdel("user", "title");
		System.out.println(jedis.hmget("user", "title"));//�˼�ֵ��ɾ�� ���null
		
		//���user�Ӽ��ĸ���
		System.out.println("user���Ӽ�����:"+jedis.hlen("user"));
		//�Ƿ����user��
		System.out.println("�Ƿ����key-->user:"+jedis.exists("user"));
		//���user�������Ӽ�
		System.out.println("user�������Ӽ�:"+jedis.hkeys("user"));
		//���user�����м�ֵ
		System.out.println("user�����м�ֵ:"+jedis.hvals("user"));
		
		//--------------------------��ֵ��������--------------------------
		Iterator<String> it = jedis.hkeys("user").iterator();
		while(it.hasNext()){
			String key = it.next();
			System.out.println(key+":"+jedis.hmget("user", key));
		}
	}
	
	public void testList(){
		//--------------------------�����������--------------------------
		jedis.del("java framework");
		System.out.println(jedis.lrange("java framework", 0, -1));
		
		
		//--------------------------�����������--------------------------
		jedis.lpush("java framework", "sprint");
		jedis.lpush("java framework", "struts");
		jedis.lpush("java framework", "hibernate");
		
		//--------------------------ȡ����������--------------------------
		System.out.println(jedis.lrange("java framework", 0, -1));
	}
	
	public void testSet(){
		//--------------------------��Ӽ���Ԫ��--------------------------
		jedis.sadd("sname", "spring");
		jedis.sadd("sname", "struts");
		jedis.sadd("sname", "hibernate");
		jedis.sadd("sname", "noname");
		
		//--------------------------�Ƴ�����Ԫ��--------------------------
		jedis.srem("sname", "noname");
		
		//��ȡ���м���Ԫ��
		System.out.println(jedis.smembers("sname"));
		//spring�Ƿ�Ϊsname���ϵ�Ԫ��
		System.out.println(jedis.sismember("sname", "spring"));
		//���ȡ�������е�һ��Ԫ��
		System.out.println(jedis.srandmember("sname"));
		//��ȡ����Ԫ�ظ���
		System.out.println(jedis.scard("sname"));
	}
	
	public void test() throws InterruptedException{
		//--------------------------ʹ��ͨ���--------------------------
		
		System.out.println(jedis.keys("*"));
		System.out.println(jedis.keys("*name"));
		
		//--------------------------��ֵ����Чʱ��-----------------------
		
		jedis.setex("timekey", 3, "second");
		System.out.println(jedis.ttl("timekey"));
		Thread.sleep(2000);
		System.out.println(jedis.ttl("timekey"));
		
		
		//--------------------------��������--------------------------
		jedis.del("array");
		jedis.lpush("array", "6");
		jedis.lpush("array","1");
		jedis.lpush("array","2");
		jedis.lpush("array", "3");
		
		System.out.println("����δ����ǰ:"+jedis.lrange("array", 0, -1));
		System.out.println("�������򷵻�ֵ:"+jedis.sort("array"));
		System.out.println("���������:"+jedis.lrange("array", 0, -1));
	}
	
	public void close(){
		jedis.disconnect();
	}
		
	public static void main(String[] args) throws InterruptedException {
		Test t = new Test();
		t.SetUp();
		//t.testBasicString();
		//t.testMap();
		//t.testList();
		//t.testSet();
		//t.test();
		t.close();
		return;
	}

}
