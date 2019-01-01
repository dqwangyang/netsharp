package org.netsharp.communication.provider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.core.InvokeRequest;
import org.netsharp.communication.core.InvokeResponse;
import org.netsharp.communication.provider.filter.CacheFilter;
import org.netsharp.communication.provider.filter.DbSessionFilter;
import org.netsharp.communication.provider.filter.InvokeFilter;
import org.netsharp.communication.provider.filter.LogFilter;
import org.netsharp.communication.provider.filter.SiContextFilter;
import org.netsharp.communication.registry.ServiceRegistry;
import org.netsharp.util.ReflectManager;

public class ServiceInvoker {

	protected static Log logger = LogFactory.getLog(ServiceInvoker.class);
	private Object serviceObject;
	private List<IServiceFilter> filters = new ArrayList<IServiceFilter>();

	public ServiceInvoker() {

		this.filters.add(new SiContextFilter());
		this.filters.add(new LogFilter());
		this.filters.add(new DbSessionFilter());
		this.filters.add(new CacheFilter());
		this.filters.add(new InvokeFilter());
	}

	public InvokeResponse invoke(InvokeRequest request) {

		SiContext ctx = this.createContext(request);

		// 调用前拦截
		for (IServiceFilter interceptor : this.filters) {
			interceptor.invoking(ctx);
		}

		// 调用后拦截
		for (int i = this.filters.size() - 1; i >= 0; i--) {
			IServiceFilter interceptor = this.filters.get(i);
			interceptor.invoked(ctx);
		}

		return ctx.Response;
	}
	
	private SiContext createContext(InvokeRequest request) {
		
		Class<?> interfaceType = ReflectManager.getType(request.getType());
		Class<?> serviceType = ServiceRegistry.getServiceType(interfaceType);

		Class<?>[] parTypes = new Class<?>[request.getParType().length];
		for (int i = 0; i < parTypes.length; i++) {
			Class<?> parType = ReflectManager.getType(request.getParType()[i]);
			parTypes[i] = parType;
		}

		Method method = ReflectManager.getMethod(serviceType, request.getMethod(), parTypes);{
			method.setAccessible(true);
		}

		this.serviceObject = ReflectManager.newInstance(serviceType);

		SiContext ctx = new SiContext();
		{
			ctx.Request = request;
			ctx.Response = new InvokeResponse();
			ctx.InterfaceType = interfaceType;
			ctx.ServiceObject = serviceObject;
			ctx.Method = method;
			ctx.Response.setSucceed(false);
			ctx.InterfaceMethod = ReflectManager.getMethod(interfaceType, ctx.Method.getName(),method.getParameterTypes());
			ctx.Name = ctx.InterfaceType.getName() + "." + ctx.Method.getName();
			ctx.setRequestId(request.getRequestId());
		}
		
		return ctx;
	}
}
