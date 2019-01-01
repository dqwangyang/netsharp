package org.netsharp.panda.core.comunication.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.netsharp.util.StringManager;

public class MemorySession implements ISession {
	
	private HttpServletRequest request;
	
	public MemorySession(HttpServletRequest request) {
		this.request = request;
	}
	
	public String getId() {
		
		HttpSession session = this.request.getSession();
		return session.getId();
	}
	
	public Object getSession(String key) {
		
		HttpSession session = this.request.getSession();
		if (session == null) {
			return null;
		}
		if (StringManager.isNullOrEmpty(key)) {
			return null;
		}
		return session.getAttribute(key);
	}
	
	public void setSession(String key, Object obj) {
		HttpSession session = this.request.getSession();
		if (session == null) {
			return;
		}

		session.setAttribute(key, obj);
	}
	
	public void removeSession(String key) {
		HttpSession session = this.request.getSession();
		if (session != null && StringManager.isNullOrEmpty(key)) {

			session.removeAttribute(key);
		}
	}
}
