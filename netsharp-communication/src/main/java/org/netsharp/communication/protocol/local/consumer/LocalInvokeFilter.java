package org.netsharp.communication.protocol.local.consumer;

import org.netsharp.communication.consumer.CiContext;
import org.netsharp.communication.consumer.IClientFilter;
import org.netsharp.communication.core.InvokeRequest;
import org.netsharp.communication.core.InvokeResponse;
import org.netsharp.communication.provider.ServiceInvoker;

public class LocalInvokeFilter implements IClientFilter {

	public void invoking(CiContext ctx) {
		
		if(ctx.getIsCacheHint()) {
			return;
		}
		
		InvokeRequest request = ctx.getRequest();
		
		ServiceInvoker serviceInvoker = new ServiceInvoker();
		InvokeResponse response = serviceInvoker.invoke(request);
		
		ctx.setResponse(response);
	}

	public void invoked(CiContext ctx) {
	}

}