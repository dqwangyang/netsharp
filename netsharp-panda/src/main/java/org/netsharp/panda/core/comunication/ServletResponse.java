package org.netsharp.panda.core.comunication;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.netsharp.panda.core.PandaConfiguration;
import org.netsharp.panda.core.PandaException;

public class ServletResponse implements  IResponse {
	
	private HttpServletResponse response;
	
	public ServletResponse(HttpServletResponse response){
		this.response = response;
	}
	public void sendRedirect(String url){
		
		try {
			this.response.sendRedirect(url);
		} catch (IOException e) {
			
			throw new PandaException(e.getMessage());
		}
	}
	
	public void write(String html){
		try {
			this.response.getWriter().write(html);
		} catch (IOException e) {
			throw new PandaException(e.getMessage());
		}
	}
	
	public void reset(){
		this.response.reset();
	}
	
	public void setContentType(String contentType){
		this.response.setContentType(contentType);
	}
	
	public void setHeader(String header,String value){
		this.response.setHeader(header,value);
	}
	
	public void addCookie(String key,String value) {
		Cookie cookie = new Cookie(key,value);
		{
			int timeout = PandaConfiguration.getInstance().getSessionTimtout() * 60;
			cookie.setMaxAge(timeout);
		}
		this.response.addCookie(cookie);
	}
	
	public OutputStream getOutputStream(){
		try {
			return this.response.getOutputStream();
		} catch (IOException e) {
			throw new PandaException(e.getMessage());
		}
	}
}