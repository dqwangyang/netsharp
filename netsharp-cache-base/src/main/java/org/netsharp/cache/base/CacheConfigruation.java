package org.netsharp.cache.base;

import org.netsharp.cache.base.dict.CacheType;

public class CacheConfigruation {
	
	private static boolean DBDisabled=true;
	private static boolean RedisDisabled=true;

	public static boolean getDisabled(CacheType cacheType) {
		
		if(cacheType == CacheType.db) {
			
			return DBDisabled; 
		}
		else if(cacheType == CacheType.redis) {
			
			return RedisDisabled;
		}
		else {
			throw new CacheException("not implements!");
		}
	}

	public static void setDisabled(CacheType cacheType, boolean disabled) {
		
		if(cacheType == CacheType.db) {
			
			DBDisabled = disabled; 
		}
		else if(cacheType == CacheType.redis) {
			
			RedisDisabled = disabled;
		}
		else {
			throw new CacheException("not implements!");
		}
	}
}
