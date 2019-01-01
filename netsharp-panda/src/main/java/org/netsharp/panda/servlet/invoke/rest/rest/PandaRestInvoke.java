package org.netsharp.panda.servlet.invoke.rest.rest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.netsharp.core.BusinessException;
import org.netsharp.core.NetsharpException;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.authorization.LoginException;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.View;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.servlet.filters.LogFilter;
import org.netsharp.panda.servlet.invoke.PandaContext;
import org.netsharp.panda.servlet.invoke.PandaInvoke;
import org.netsharp.panda.servlet.invoke.PandaInvokeType;
import org.netsharp.panda.servlet.invoke.rest.Exceptions;
import org.netsharp.panda.servlet.invoke.rest.RestContext;
import org.netsharp.panda.servlet.invoke.rest.Result;
import org.netsharp.panda.servlet.invoke.rest.Result.Type;
import org.netsharp.panda.servlet.invoke.rest.filters.AuthorizationFilter;
import org.netsharp.panda.servlet.invoke.rest.filters.ExceptionFilter;
import org.netsharp.panda.servlet.invoke.rest.rest.filter.ContextFilter;
import org.netsharp.panda.servlet.invoke.rest.rest.filter.PartFilter;
import org.netsharp.panda.servlet.invoke.rest.rest.filter.ResponseWriteFilter;
import org.netsharp.util.ExceptionUtil;
import org.netsharp.util.JsonManage;
import org.netsharp.util.ReflectManager;

// 参数在json中,返回值是Result格式
public class PandaRestInvoke extends PandaInvoke {

	public PandaRestInvoke() {

		this.filters.add(new LogFilter(PandaInvokeType.rest));
		this.filters.add(new ContextFilter());
		this.filters.add(new AuthorizationFilter());
		this.filters.add(new PartFilter());
		this.filters.add(new ExceptionFilter());
		this.filters.add(new ResponseWriteFilter());
		
	}

	public void doProcessRequest(PandaContext pandaContext) {

		RestContext ctx = (RestContext) pandaContext;

		if (ctx.getIsTruncation()) {
			return;
		}

		try {
			this.execute(ctx);
		} catch (Exception ex) {
			this.exception(ex, ctx);
		}
	}

	private void execute(RestContext ctx)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Method method = ctx.getMethod();
		RequestPanda request = (RequestPanda) ctx.getRequest();
		Result<Object> result = ctx.getResponse();
		Object serviceObject = ctx.getServiceObject();

		Class<?>[] pts = method.getParameterTypes();
		Object[] pars = new Object[pts.length];
		for (int i = 0; i < pts.length; i++) {

			String parValue = request.getParameters().get(i).getValue();
			String json = UrlHelper.decode(parValue);

			// 参数类型可能是接口，默认设置一个当前工作区实体的类型
			if (ReflectManager.isInterface(pts[i], IPersistable.class)) {

				PPart ppart = request.getPpart();
				if (ppart != null) {
					View view = (View) serviceObject;
					Class<?> type = ReflectManager.getType(view.getContext().getEntityId());
					pts[i] = type;
				}
			}

			// 反序列化参数对象
			Object obj = JsonManage.deSerialize2(pts[i], json);
			pars[i] = obj;
		}

		Object obj = method.invoke(serviceObject, pars);

		result.setData(obj);
		result.setType(Type.info);
	}

	private void exception(Exception ex, RestContext ctx) {

		Result<Object> result = ctx.getResponse();

		ctx.setIsTruncation(true);

		LoginException le = Exceptions.causedby(ex, LoginException.class);
		if (le != null) {
			result.setType(Type.loginTimeout);
			result.setMessage(le.getMessage());

			ctx.setIsTruncation(true);
			return;
		}

		BusinessException be = Exceptions.causedby(ex, BusinessException.class);
		if (be != null) {
			result.setType(Type.warn);
			result.setMessage(be.getMessage());

			return;
		}

		NetsharpException ne = Exceptions.causedby(ex, NetsharpException.class);
		if (ne != null) {
			result.setType(Type.error);
			result.setMessage(ne.getMessage());

			ctx.setException(ex);
			return;
		}

		result.setType(Type.error);
		result.setMessage(ExceptionUtil.extractMsg(ex));
		ctx.setException(ex);
	}
	
	@Override
	protected PandaContext createContext() {
		
		RestContext pandaContext = new RestContext();
		PandaContext.setCurrent(pandaContext);
		
		return pandaContext;
	}
}
