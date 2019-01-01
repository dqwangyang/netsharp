package org.netsharp.panda.servlet.invoke.nav;

import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.comunication.IRequest;
import org.netsharp.panda.servlet.filters.LogFilter;
import org.netsharp.panda.servlet.invoke.PandaContext;
import org.netsharp.panda.servlet.invoke.PandaInvoke;
import org.netsharp.panda.servlet.invoke.PandaInvokeType;
import org.netsharp.panda.servlet.invoke.html.AuthorizationFilter;

public class PandaNavInvoke extends PandaInvoke {

	public PandaNavInvoke() {

		this.filters.add(new LogFilter(PandaInvokeType.nav));
		this.filters.add(new AuthorizationFilter());
	}

	public void doProcessRequest(PandaContext pandaContext) {
		
		IRequest request = HttpContext.getCurrent().getRequest();

		String url = request.getRequestURI();
		url = url.replace("/nav", "")+".jsp";
		
		request.sendRedirect(url);
		
//		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//		dispatcher.forward(request,response); 
		
	}
}