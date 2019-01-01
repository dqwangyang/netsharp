package org.netsharp.communication.protocol.tcp.consumer;

import org.netsharp.communication.consumer.ClientInvoker;
import org.netsharp.communication.consumer.filters.ExceptionFilter;
import org.netsharp.communication.consumer.filters.LogFilter;
import org.netsharp.communication.consumer.filters.cache.CacheFilter;

public class TcpInvoker extends ClientInvoker {

	public TcpInvoker(String interfaceType) {

		this.interfaceType = interfaceType;
		
		this.filters.add(new ExceptionFilter());
		this.filters.add(new LogFilter());
		this.filters.add(new TcpSessionFilter());
		this.filters.add(new CacheFilter());
		this.filters.add(new TcpInvokeFilter());
		
	}

}