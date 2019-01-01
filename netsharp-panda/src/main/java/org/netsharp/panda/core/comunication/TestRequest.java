package org.netsharp.panda.core.comunication;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.core.NotImplementException;
import org.netsharp.panda.core.PandaException;

public class TestRequest implements IRequest {
	
	private Map<String,String> pars;
	private Map<String,Object> sessions = new HashMap<String,Object>();
	
	public TestRequest(Map<String,String> pars){
		this.pars=pars;
	}

	@Override
	public String getParameter(String key) {
		return pars.get(key);
	}
	
	public void sendRedirect(String url){
		throw new PandaException("不支持页面跳转:"+url);
	}

	@Override
	public String getContextPath() {
		throw new NotImplementException();
	}

	@Override
	public String getRequestURL() {
		throw new NotImplementException();
	}

	@Override
	public String getRequestURI() {
		throw new NotImplementException();
	}

	@Override
	public String getServletPath() {
		throw new NotImplementException();
	}

	@Override
	public String getQueryString() {
		throw new NotImplementException();
	}

	@Override
	public String getBody() {
		throw new NotImplementException();
	}

	public Object getSession(String key){
		return sessions.get(key);
	}
	
	public String getCookie(String cookieName) {
		throw new NotImplementException();
	}
	
	public long getSessionCreateTime(){
		throw new NotImplementException();
	}
	
	public void setSession(String key,Object value){
		sessions.put(key, value);
	}
	
    public String getSessionId(){
    	throw new NotImplementException();
    }

	@Override
	public void removeSession(String key) {
		throw new NotImplementException();
	}
	
	public void setsetMaxInactiveInterval(int interval) {
		throw new NotImplementException();
	}

	public String getClientIp() {
		throw new NotImplementException();
	}

	public String getUserAgent() {
		throw new NotImplementException();
	}

	public String getHeader(String headerAttributeName) {
		throw new NotImplementException();
	}
}