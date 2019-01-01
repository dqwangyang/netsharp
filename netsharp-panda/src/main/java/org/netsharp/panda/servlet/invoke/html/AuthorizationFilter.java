package org.netsharp.panda.servlet.invoke.html;

import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.comunication.IRequest;
import org.netsharp.panda.servlet.filters.IPandaFilter;
import org.netsharp.panda.servlet.invoke.PandaContext;
import org.netsharp.util.StringManager;

public class AuthorizationFilter implements IPandaFilter {
	
	private static String[] urls = {"/nav/panda-bizbase/authorization/login","/nav/panda-bizbase/authorization/nopermission"};

	@Override
	public void invoking(PandaContext pandaContext) {
		
		IRequest request = HttpContext.getCurrent().getRequest();
		String uri = request.getRequestURI();
		
		for(String url : urls) {
			if(StringManager.equals(uri, url)) {
				pandaContext.setIsAuthorization(false);
				return;
			}
		}
	}

	@Override
	public void invoked(PandaContext ctx) {
		//如果没有权限的话，要如何处理呢
		//如果是workbench直接跳转到登录页
		//如果是workspace则通知workbench跳转到登录页
		//无论是谁没有登录，都要跳转到登录页
	}

}
