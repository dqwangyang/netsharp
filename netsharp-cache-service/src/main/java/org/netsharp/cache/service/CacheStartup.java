package org.netsharp.cache.service;

import org.netsharp.cache.base.CacheCommandFactory;
import org.netsharp.cache.base.ICacheCommand;
import org.netsharp.cache.base.dict.CacheType;
import org.netsharp.communication.core.CommunicationConfiguration;
import org.netsharp.startup.IStartup;

public class CacheStartup implements IStartup {
	
	public boolean startCondition() {
		
		return true;
	}

	public void startup() {
		
		CacheType cacheType = CommunicationConfiguration.get().getCache();
		if(cacheType != CacheType.none) {
			ICacheCommand cmd = CacheCommandFactory.create(cacheType,"communication");
			cmd.flushDB();
		}
	}

	public void shutdown() {
		
	}

	public String getName() {
		return "缓存初始化";
	}

}
