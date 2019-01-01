package org.netsharp.communication.consumer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.core.InvokeRequest;
import org.netsharp.communication.core.InvokeResponse;
import org.netsharp.session.ThreadContext;

public class ClientInvoker implements InvocationHandler {

	protected static Log logger = LogFactory.getLog(ClientInvoker.class);	
	protected List<IClientFilter> filters = new ArrayList<IClientFilter>();
	protected String interfaceType;

	public Object invoke(Object proxy, Method method, Object[] args)  throws Throwable {

		CiContext ctx = this.createContext(interfaceType, proxy, method, args);

		// 调用前拦截
		for (IClientFilter interceptor : this.filters) {
			interceptor.invoking(ctx);
		}

		// 调用后拦截
		for (int i = this.filters.size() - 1; i >= 0; i--) {
			IClientFilter interceptor = this.filters.get(i);
			interceptor.invoked(ctx);
		}

		// 返回
		InvokeResponse response = ctx.getResponse();
		Object ret = response.getReturnObject();

		return ret;
	}
	
	private CiContext createContext(String interfaceType,Object proxy, Method method, Object[] args) {
		
		InvokeRequest request = new InvokeRequest();
		{
			request.setType(interfaceType);
			request.setMethod(method.getName());
			request.setPars(args);
			
			ThreadContext threadContext = ThreadContext.get();
			if(threadContext!=null) {
				request.setRequestId(threadContext.getRequestId());
			}else {
				request.setRequestId(UUID.randomUUID());
			}
			
			String[] parTypes = new String[method.getParameterTypes().length];
			for(int i=0;i<parTypes.length;i++){
				Class<?> pt = method.getParameterTypes()[i];
				parTypes[i] = pt.getName();
			}
			
			request.setParType(parTypes);
		}		
		
		CiContext ctx = new CiContext();
		{
			ctx.setName(interfaceType + "." + request.getMethod());
			ctx.setRequest(request);
			ctx.setMethod(method);
		}
		
		return ctx;
	}
}
