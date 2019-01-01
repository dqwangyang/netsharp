package org.netsharp.cache.service.redis;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.application.ApplicationConfiguration;
import org.netsharp.application.ConfigItem;
import org.netsharp.application.Configuration;

@Configuration(file = "conf/redis.properties")
public class RedisConnection extends ApplicationConfiguration{
	
	private static Map<String,RedisConnection> map = new HashMap<String,RedisConnection>();
	
	private RedisConnection() {}
	
	public static RedisConnection getInstance(String name) {
		
		if(!map.containsKey(name)) {
			
			RedisConnection con = new RedisConnection();
			con.deserialize(name);
			
			map.put(name, con);
		}
		
		return map.get(name);
	}
	
	@ConfigItem(key="redis.xxx.pool.maxActive",defaultValue="200")
	private int maxActive;
	
	@ConfigItem(key="redis.xxx.pool.maxIdle",defaultValue="100")
	private int maxIdle;
	
	@ConfigItem(key="redis.xxx.pool.maxWait",defaultValue="3000")
	private int maxWait;
	
	@ConfigItem(key="redis.xxx.ip",required=true,defaultValue="127.0.0.1")
	private String ip;
	
	@ConfigItem(key="redis.xxx.port",required=true,defaultValue="6379")
	private int port;
	
	@ConfigItem(key="redis.xxx.password",required=true,defaultValue="")
	private String password;
	
	@ConfigItem(key="redis.xxx.timeout",defaultValue="5000")
	private int timeout;
	
	@ConfigItem(key="redis.xxx.database",required=true,defaultValue="-1")
	private int database;
	
	@ConfigItem(key="redis.xxx.disabled", defaultValue="false")
	private boolean disabled;
	
	public int getMaxActive() {
		return maxActive;
	}
	public int getMaxIdle() {
		return maxIdle;
	}
	public int getMaxWait() {
		return maxWait;
	}
	public String getIp() {
		return ip;
	}
	public int getPort() {
		return port;
	}
	public String getPassword() {
		return password;
	}
	public int getTimeout() {
		return timeout;
	}
	public int getDatabase() {
		return database;
	}
	public boolean isDisabled() {
		return disabled;
	}
}
