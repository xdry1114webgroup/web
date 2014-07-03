package outsourcing.db.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionPool {
	private static JedisPool pool;
	private static final String auth = "abc123";
	public static final String Host = "10.170.20.216";
		
	static{
		pool = new JedisPool(new JedisPoolConfig(),Host);
		
	}
	
	private JedisConnectionPool(){
		//此处构造设置为private 防止生成此对象
	}
	
	public static Jedis getJedisConnection(){
		Jedis jedis = pool.getResource();
		jedis.auth(auth);
		return jedis;
	}
	
	public static void main(String[] args){
		Jedis jedis = JedisConnectionPool.getJedisConnection();
		System.out.println(jedis.isConnected());
		jedis.disconnect();
	}
}
