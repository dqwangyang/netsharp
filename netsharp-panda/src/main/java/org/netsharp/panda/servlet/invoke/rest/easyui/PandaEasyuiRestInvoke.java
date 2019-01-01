package org.netsharp.panda.servlet.invoke.rest.easyui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.netsharp.core.BusinessException;
import org.netsharp.core.NetsharpException;
import org.netsharp.panda.authorization.LoginException;
import org.netsharp.panda.servlet.filters.LogFilter;
import org.netsharp.panda.servlet.invoke.PandaContext;
import org.netsharp.panda.servlet.invoke.PandaInvoke;
import org.netsharp.panda.servlet.invoke.PandaInvokeType;
import org.netsharp.panda.servlet.invoke.rest.Exceptions;
import org.netsharp.panda.servlet.invoke.rest.RestContext;
import org.netsharp.panda.servlet.invoke.rest.Result;
import org.netsharp.panda.servlet.invoke.rest.Result.Type;
import org.netsharp.panda.servlet.invoke.rest.easyui.filter.ContextFilter;
import org.netsharp.panda.servlet.invoke.rest.easyui.filter.ResponseWriteFilter;
import org.netsharp.panda.servlet.invoke.rest.filters.AuthorizationFilter;
import org.netsharp.panda.servlet.invoke.rest.filters.ExceptionFilter;
import org.netsharp.util.ExceptionUtil;

public class PandaEasyuiRestInvoke extends PandaInvoke {

	public PandaEasyuiRestInvoke() {

		this.filters.add(new LogFilter(PandaInvokeType.rest_easyui));
		this.filters.add(new ContextFilter());
		this.filters.add(new AuthorizationFilter());
//		this.filters.add(new PartFilter());
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
		Result<Object> result = ctx.getResponse();
		Object serviceObject = ctx.getServiceObject();

		Object obj = method.invoke(serviceObject);

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
