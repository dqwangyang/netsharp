package org.netsharp.communication.consumer.filters.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.cache.base.CacheCommandFactory;
import org.netsharp.cache.base.ICacheCommand;
import org.netsharp.communication.consumer.CiContext;
import org.netsharp.communication.consumer.IClientFilter;
import org.netsharp.communication.core.CommunicationConfiguration;

public class CacheDeleteFilter implements IClientFilter {

	private final static Log logger = LogFactory.getLog(CacheDeleteFilter.class);

 	private String key = null;

	public void invoking(CiContext ctx) {
		
		this.key = CacheGetFilter.getKey(ctx);
	}

	public void invoked(CiContext ctx) {
		
		if(this.key ==null) {
			return;
		}
		
		if(!ctx.getResponse().getSucceed()) {
			return;
		}
		
		ICacheCommand cmd = CacheCommandFactory.create( CommunicationConfiguration.get().getCache(), "communication");
		cmd.del(key);
		
		logger.trace("cache delete : "+key);
	}

}