package org.netsharp.communication.protocol.local.consumer;

import org.netsharp.communication.consumer.ClientInvoker;
import org.netsharp.communication.consumer.filters.ExceptionFilter;
import org.netsharp.communication.consumer.filters.LogFilter;
import org.netsharp.communication.consumer.filters.cache.CacheFilter;

/* 本地调用接口代理 */
public class LocalInvoker extends ClientInvoker {

	public LocalInvoker(String interfaceType) {

		this.interfaceType = interfaceType;
		
		this.filters.add(new ExceptionFilter());
		this.filters.add(new LogFilter());
		this.filters.add(new ServiceRegistryFilter());
		this.filters.add(new CacheFilter());
		this.filters.add(new LocalInvokeFilter());
	}

}