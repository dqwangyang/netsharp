package org.netsharp.panda.servlet.invoke.rest.rest.filter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.servlet.filters.IPandaFilter;
import org.netsharp.panda.servlet.invoke.PandaContext;
import org.netsharp.panda.servlet.invoke.rest.RestContext;
import org.netsharp.panda.servlet.invoke.rest.Result;
import org.netsharp.panda.servlet.invoke.rest.Result.Type;
import org.netsharp.panda.servlet.invoke.rest.rest.RequestPanda;
import org.netsharp.util.JsonManage;
import org.netsharp.util.StringManager;

public class ContextFilter implements IPandaFilter {

	@Override
	public void invoking(PandaContext pandaContext) {
		
		// ctx
		RestContext ctx = (RestContext)pandaContext;{
			ctx.setResponse(new Result<Object>());
			ctx.setIsAuthorization(true);
		}
		
		HttpContext context = HttpContext.getCurrent();
		String body = context.getRequest().getBody();

		RequestPanda request = (RequestPanda) JsonManage.deSerialize2(RequestPanda.class, body);
		ctx.setName(request.getService() + "." + request.getMethodName());
		ctx.setRequest(request);

		// serviceObject
		Object serviceObject = null;
		try {
			Class<?> type = Class.forName(request.getService());
			Constructor<?> constructor = type.getConstructor();
			serviceObject = constructor.newInstance();
			ctx.setServiceObject(serviceObject);
		}catch(Exception ex) {
			
			ctx.getResponse().setType(Type.error);
			ctx.getResponse().setMessage("未能实例化Controller服务对象:" + request.getService());
			ctx.setException(ex);
			ctx.setIsTruncation(true);
			
			return;
		}

		// method
		Method method = null;
		
		Method[] methods = serviceObject.getClass().getMethods();
		for (Method function : methods) {
			
			if(!StringManager.equals(function.getName(), request.getMethodName(),false)) {
				continue;
			}

			if(function.getParameterCount() != request.getParameters().size()) {
				continue;
			}
			
			method = function;
			break;
		}
		
		ctx.setMethod(method);
		
		// response
		if (method == null) {
			
			ctx.getResponse().setType(Type.error);
			ctx.getResponse().setMessage("方法不存在:" + ctx.getName());

			ctx.setIsTruncation(true);
			
			return;
		}
	}

	@Override
	public void invoked(PandaContext ctx) {
	}

}
