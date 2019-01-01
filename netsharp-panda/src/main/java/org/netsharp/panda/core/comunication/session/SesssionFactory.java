package org.netsharp.panda.core.comunication.session;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.cache.base.dict.CacheType;
import org.netsharp.panda.core.PandaConfiguration;

public class SesssionFactory {
	
	protected static Log logger = LogFactory.getLog(SesssionFactory.class);
	
	
	public static ISession create(HttpServletRequest request) {

		CacheType sessionType = PandaConfiguration.getInstance().getSessionCacheType();
		
		if(sessionType == CacheType.redis) {
			return new RedisSession(request); 
		}
		
		return new MemorySession(request);
	}

}
