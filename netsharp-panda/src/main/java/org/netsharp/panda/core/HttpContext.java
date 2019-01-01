package org.netsharp.panda.core;

import javax.servlet.ServletContext;

import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.panda.core.comunication.IRequest;
import org.netsharp.panda.core.comunication.IResponse;

public class HttpContext {
	
	private static final ThreadLocal<HttpContext> threadLocal = new ThreadLocal<HttpContext>();
	
	private IRequest request;
	private IResponse response;
	private ServletContext context;
	private IHtmlWriter writer;
	
	public static HttpContext getCurrent(){
		return threadLocal.get();
	}

	public static void setCurrent(HttpContext cur){
		threadLocal.set(cur);
	}

	public IRequest getRequest() {
		return request;
	}

	public void setRequest(IRequest request) {
		this.request = request;
	}

	public IResponse getResponse() {
		return this.response;
	}

	public void setResponse(IResponse response) {
		this.response = response;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public IHtmlWriter getWriter() {
		return writer;
	}

	public void setWriter(IHtmlWriter writer) {
		this.writer = writer;
	}
}
