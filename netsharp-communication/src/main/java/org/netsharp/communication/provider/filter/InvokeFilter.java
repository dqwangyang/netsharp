package org.netsharp.communication.provider.filter;

import java.lang.reflect.Method;

import org.netsharp.communication.provider.IServiceFilter;
import org.netsharp.communication.provider.SiContext;

//服务方法调用
public class InvokeFilter implements IServiceFilter {
	
	public void invoking(SiContext ctx) {
		
		if(ctx.IsCached){
			return;
		}
		
		Method method = ctx.Method;
		Object serviceObject = ctx.ServiceObject;
		Object[] args = ctx.Request.getPars();
		
		try {
			Object ret = method.invoke(serviceObject, args);
			ctx.Response.setReturnObject( ret );
			ctx.Response.setSucceed(true);
		} catch (Exception ex) {
			
			ctx.Response.setSucceed(false);
			ctx.Response.setException(ex);
			
	        Throwable cause = ex.getCause();
	        if(cause==null){
	            ctx.Response.setException(cause);
	        }
		}
	}

	public void invoked(SiContext ctx) {
		
	}
}
