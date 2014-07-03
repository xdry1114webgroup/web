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
		//----------------------------测试添加数据----------------------------
		jedis.set("name", "huang");
		System.out.println("key-->name:"+jedis.get("id"));
				
		//----------------------------测试修改数据----------------------------
		jedis.append("name", "lipeng");
		System.out.println("key-->name:"+jedis.get("name"));
		
		//----------------------------覆盖数据----------------------------
		jedis.set("name", "黄丽鹏");
		System.out.println("key-->name"+jedis.get("name"));
		
		//----------------------------删除数据----------------------------
		jedis.del("name");
		System.out.println("key-->name:"+jedis.get("name"));//将会输出null
		
		//----------------------------添加多对数据----------------------------
		jedis.mset("name","huanglipeng","id","13111329","title","student");
		List<String> rsmap = jedis.mget("name","id","title");
		System.out.println(rsmap);
	}
	
	public void testMap(){
		Map<String,String> user = new HashMap<String,String>();
		//----------------------------构建Map数据----------------------------
		user.put("name", "huanglipeng");
		user.put("password", "123456789");
		user.put("title", "student");
		jedis.hmset("user", user);
		
		//----------------------------获取Map数据----------------------------
		List<String> rsmap = jedis.hmget("user", "name","password","title");
		System.out.println(rsmap);
		
		//--------------------------删除Map中的某个键值-----------------------
		jedis.hdel("user", "title");
		System.out.println(jedis.hmget("user", "title"));//此键值已删除 输出null
		
		//输出user子键的个数
		System.out.println("user的子键个数:"+jedis.hlen("user"));
		//是否存在user键
		System.out.println("是否存在key-->user:"+jedis.exists("user"));
		//输出user的所有子键
		System.out.println("user的所有子键:"+jedis.hkeys("user"));
		//输出user的所有键值
		System.out.println("user的所有键值:"+jedis.hvals("user"));
		
		//--------------------------键值迭代遍历--------------------------
		Iterator<String> it = jedis.hkeys("user").iterator();
		while(it.hasNext()){
			String key = it.next();
			System.out.println(key+":"+jedis.hmget("user", key));
		}
	}
	
	public void testList(){
		//--------------------------清空所有内容--------------------------
		jedis.del("java framework");
		System.out.println(jedis.lrange("java framework", 0, -1));
		
		
		//--------------------------存放数据数组--------------------------
		jedis.lpush("java framework", "sprint");
		jedis.lpush("java framework", "struts");
		jedis.lpush("java framework", "hibernate");
		
		//--------------------------取出数据数组--------------------------
		System.out.println(jedis.lrange("java framework", 0, -1));
	}
	
	public void testSet(){
		//--------------------------添加集合元素--------------------------
		jedis.sadd("sname", "spring");
		jedis.sadd("sname", "struts");
		jedis.sadd("sname", "hibernate");
		jedis.sadd("sname", "noname");
		
		//--------------------------移除集合元素--------------------------
		jedis.srem("sname", "noname");
		
		//获取所有集合元素
		System.out.println(jedis.smembers("sname"));
		//spring是否为sname集合的元素
		System.out.println(jedis.sismember("sname", "spring"));
		//随机取出集合中的一个元素
		System.out.println(jedis.srandmember("sname"));
		//获取集合元素个数
		System.out.println(jedis.scard("sname"));
	}
	
	public void test() throws InterruptedException{
		//--------------------------使用通配符--------------------------
		
		System.out.println(jedis.keys("*"));
		System.out.println(jedis.keys("*name"));
		
		//--------------------------键值的有效时间-----------------------
		
		jedis.setex("timekey", 3, "second");
		System.out.println(jedis.ttl("timekey"));
		Thread.sleep(2000);
		System.out.println(jedis.ttl("timekey"));
		
		
		//--------------------------数组排序--------------------------
		jedis.del("array");
		jedis.lpush("array", "6");
		jedis.lpush("array","1");
		jedis.lpush("array","2");
		jedis.lpush("array", "3");
		
		System.out.println("数组未排序前:"+jedis.lrange("array", 0, -1));
		System.out.println("数组排序返回值:"+jedis.sort("array"));
		System.out.println("数组排序后:"+jedis.lrange("array", 0, -1));
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
