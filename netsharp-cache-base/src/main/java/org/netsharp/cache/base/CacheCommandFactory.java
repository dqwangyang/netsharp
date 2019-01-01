package org.netsharp.cache.base;

import org.netsharp.cache.base.dict.CacheType;
import org.netsharp.util.ReflectManager;

public class CacheCommandFactory {
	
	public static ICacheCommand create(CacheType cacheType,String conf){
		
		if(cacheType == CacheType.db) {
			
			String db="org.netsharp.cache.service.db.DbCacheCommand";
			ICacheCommand cmd = (ICacheCommand)ReflectManager.newInstance(db,conf);
			
			return cmd; 
		}
		else if(cacheType == CacheType.redis) {
			
			String db="org.netsharp.cache.service.redis.RedisCacheCommand";
			ICacheCommand cmd = (ICacheCommand)ReflectManager.newInstance(db,conf);
			
			return cmd;
		}
		else {
			throw new CacheException("not implements!");
		}
	}

}
