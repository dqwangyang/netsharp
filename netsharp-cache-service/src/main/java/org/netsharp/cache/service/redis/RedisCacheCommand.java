package org.netsharp.cache.service.redis;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.cache.base.ICacheCommand;
import org.netsharp.cache.base.util.SerizlizeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisCacheCommand implements ICacheCommand {
	
	protected static Log logger = LogFactory.getLog(RedisCacheCommand.class);
    private static JedisPool jedisPool = null;
    private String conf;
    
    public RedisCacheCommand(String conf) {
    	this.conf = conf;
    }

    public Object get(String key) {
        Jedis jedis = getRedis();
        try {
            byte[] byteArray = jedis.get(key.getBytes());
            if (byteArray == null || byteArray.length <= 0) {
                return null;
            } else {
                Object value = SerizlizeUtil.unserizlize(byteArray);
                return value;
            }
        } catch (Exception e) {
        	logger.error("redis get exception",e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }
    
    public boolean exists(String key) {
    	 Jedis jedis = getRedis();
         try {
            return jedis.exists(key.getBytes());
         } catch (Exception e) {
         	logger.error("redis exists exception",e);
         } finally {
             if (jedis != null) {
                 jedis.close();
             }
         }
         return false;
    }

    public void set(String key, Object value) {
        Jedis jedis = getRedis();
        try {
            if (value == null) {
                return;
            }

            byte[] byteArray = SerizlizeUtil.serialize(value);
            jedis.set(key.getBytes(), byteArray);
        } catch (Exception e) {
        	logger.error("redis set exception",e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void set(String key, int seconds, Object value) {

        Jedis jedis = getRedis();
        try {
            if (value == null) {
                return;
            }

            byte[] byteArray = SerizlizeUtil.serialize(value);
            jedis.set(key.getBytes(), byteArray);
            jedis.expire(key.getBytes(), seconds);
        } catch (Exception e) {
        	logger.error("redis set exception",e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    public void del(String keys) {

        Jedis jedis = getRedis();
        try {
            jedis.del(keys.getBytes());
        } catch (Exception e) {
        	logger.error("redis del exception",e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    public Set<String> keys(String pattern) {

        Jedis jedis = getRedis();
        try {
            return jedis.keys(pattern);
        } catch (Exception e) {
        	logger.error("redis keys exception",e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public Set<byte[]> keys(byte[] pattern) {

        Jedis jedis = getRedis();
        try {
            return jedis.keys(pattern);
        } catch (Exception e) {
        	logger.error("redis keys exception",e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public String flushDB() {
        return this.getRedis().flushDB();
    }

    public Jedis getRedis() {
    	
    	RedisConnection con = RedisConnection.getInstance(this.conf);
    	
        if (jedisPool == null) {
        	
            JedisPoolConfig config = new JedisPoolConfig();
            {
                config.setMaxTotal(con.getMaxActive());
                config.setMaxIdle(con.getMaxIdle());
                config.setMaxWaitMillis(con.getMaxWait());
            }

            jedisPool = new JedisPool(config, con.getIp(), con.getPort(), con.getTimeout(), con.getPassword());
        }

        Jedis jedis = jedisPool.getResource();
        jedis.select(con.getDatabase());

        return jedis;
    }
}
