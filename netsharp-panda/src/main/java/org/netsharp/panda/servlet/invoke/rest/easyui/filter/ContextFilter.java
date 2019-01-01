package org.netsharp.panda.servlet.invoke.rest.easyui.filter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.base.IPPartService;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.core.View;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.servlet.filters.IPandaFilter;
import org.netsharp.panda.servlet.invoke.PandaContext;
import org.netsharp.panda.servlet.invoke.rest.RestContext;
import org.netsharp.panda.servlet.invoke.rest.Result;
import org.netsharp.panda.servlet.invoke.rest.Result.Type;
import org.netsharp.panda.servlet.invoke.rest.easyui.RequestEasyui;
import org.netsharp.util.StringManager;

public class ContextFilter implements IPandaFilter {

	@Override
	public void invoking(PandaContext pandaContext) {
		
		// ctx
		RestContext ctx = (RestContext)pandaContext;{
			ctx.setResponse(new Result<Object>());
			ctx.setIsAuthorization(true);
		}
		
		RequestEasyui request = this.getRequest();
		
		ctx.setName(request.getService() + "." + request.getMethod());
		ctx.setRequest(request);
		
		// serviceObject
		Object serviceObject = null;
		try {
			Class<?> type = Class.forName(request.getService());
			Constructor<?> constructor = type.getConstructor();
			serviceObject = constructor.newInstance();
			ctx.setServiceObject(serviceObject);
		}catch(Exception ex) {
			
			ctx.getResponse().setType(Type.fatal);
			ctx.getResponse().setMessage("创建服务异常:" + request.getService());
			ctx.setException(ex);
			ctx.setIsTruncation(true);
			
			return;
		}

		if (serviceObject instanceof View) {
			View view = (View) serviceObject;
			view.setContext(request.getPpart());
		}

		// method
		Method method = null;
		
		Method[] methods = serviceObject.getClass().getMethods();
		for (Method function : methods) {
			
			if(!StringManager.equals(function.getName(), request.getMethod(),false)) {
				continue;
			}
			if(function.getParameterTypes().length > 0) {
				continue;
			}
			
			method = function;
			break;
		}
		
		ctx.setMethod(method);
		
		// response
		if (method == null) {
			
			ctx.getResponse().setType(Type.fatal);
			ctx.getResponse().setMessage("方法不存在:" + ctx.getName());

			ctx.setIsTruncation(true);
			
			return;
		}
	}
	
	// 参数在url的parameter中，返回值是easyui要求的格式
	// 好像主要是easyui的datagrid，所以可以使用两个url地址？
	private RequestEasyui getRequest() {

		HttpContext context = HttpContext.getCurrent();
		
		String vid = context.getRequest().getParameter("vid");
		String methodName = context.getRequest().getParameter("method");

		if (StringManager.isNullOrEmpty(methodName)) {
			throw new PandaException("panda调用必须传递参数:method");
		}

		IPPartService service = ServiceFactory.create(IPPartService.class);
		PPart ppart = service.byId(vid);

		String serviceController = ppart.getPartType().getServiceController();

		if (!StringManager.isNullOrEmpty(ppart.getServiceController())) {
			serviceController = ppart.getServiceController();
		}

		RequestEasyui request = new RequestEasyui();
		{
			request.setvid(Integer.parseInt(vid));
			request.setMethod(methodName);
			request.setService(serviceController);
			request.setPpart(ppart);
		}

		return request;
	}


	@Override
	public void invoked(PandaContext ctx) {
	}

}
