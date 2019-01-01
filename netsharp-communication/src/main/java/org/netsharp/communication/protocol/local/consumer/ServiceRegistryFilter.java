package org.netsharp.communication.protocol.local.consumer;

import org.netsharp.communication.consumer.CiContext;
import org.netsharp.communication.consumer.IClientFilter;
import org.netsharp.communication.registry.ServiceRegistry;

public class ServiceRegistryFilter implements IClientFilter {

	public void invoking(CiContext ctx) {
		
		ServiceRegistry.initialize();
		
	}

	public void invoked(CiContext ctx) {
	}

}