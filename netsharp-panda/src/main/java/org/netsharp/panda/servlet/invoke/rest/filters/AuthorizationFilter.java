package org.netsharp.panda.servlet.invoke.rest.filters;

import org.netsharp.panda.annotation.Authorization;
import org.netsharp.panda.authorization.LoginException;
import org.netsharp.panda.servlet.filters.IPandaFilter;
import org.netsharp.panda.servlet.invoke.PandaContext;
import org.netsharp.panda.servlet.invoke.rest.RestContext;
import org.netsharp.panda.servlet.invoke.rest.Result;
import org.netsharp.panda.servlet.invoke.rest.Result.Type;
import org.netsharp.panda.session.PandaSessionPersister;
import org.netsharp.util.ExceptionUtil;

public class AuthorizationFilter implements IPandaFilter {

	@Override
	public void invoking(PandaContext pandaContext) {

		RestContext ctx = (RestContext) pandaContext;

		if (ctx.getIsTruncation()) {
			return;
		}

		// authorization
		Authorization authorization = ctx.getMethod().getAnnotation(Authorization.class);
		boolean isauthorization = authorization == null || authorization.is();

		ctx.setIsAuthorization(isauthorization);

		if (!isauthorization) {
			return;
		}

		Result<Object> result = ctx.getResponse();
		try {
			boolean logined = PandaSessionPersister.logined();
			if (!logined) {
				result.setType(Type.loginTimeout);
				result.setMessage("登录超时,请退出重新登录！");
				ctx.setIsTruncation(true);
			}
		} catch (LoginException ex) {
			result.setType(Type.loginTimeout);
			String errMsg = ExceptionUtil.extractMsg(ex);
			result.message = errMsg;
			ctx.setIsTruncation(true);

		} catch (Exception ex) {

			result.setType(Type.error);
			String errMsg = ExceptionUtil.extractMsg(ex);
			result.message = errMsg;
			ctx.setIsTruncation(true);

			ctx.setException(ex);
		}
	}

	@Override
	public void invoked(PandaContext ctx) {
	}

}
