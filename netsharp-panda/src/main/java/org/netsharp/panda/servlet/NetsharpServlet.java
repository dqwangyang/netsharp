package org.netsharp.panda.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.comunication.HtmlWriter;
import org.netsharp.panda.core.comunication.ServletRequest;
import org.netsharp.panda.core.comunication.ServletResponse;
import org.netsharp.panda.servlet.invoke.PandaInvoke;
import org.netsharp.panda.servlet.invoke.PandaInvokeFactory;

public class NetsharpServlet extends HttpServlet {

	private static final long serialVersionUID = 3395712372985541365L;

	public NetsharpServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.run(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.run(request, response);
	}

	private void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");

		HttpContext ctx = new HttpContext();
		{
			ctx.setRequest(new ServletRequest(request, response));
			ctx.setResponse(new ServletResponse(response));
			ctx.setContext(this.getServletContext());
			ctx.setWriter(new HtmlWriter(response.getWriter()));
		}
		HttpContext.setCurrent(ctx);
		
		PandaInvoke pandaInvoke = PandaInvokeFactory.crate();
		pandaInvoke.processRequest();

	}
}
