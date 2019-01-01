package org.netsharp.panda.core.comunication.session;

import org.netsharp.core.NotImplementException;

public class DbSession implements ISession {
	
//	private HttpServletRequest request;
//	private ICacheCommand cacheCommand = CacheCommandFactory.create(CacheType.db);
//	
//	public DbSession(HttpServletRequest request) {
//		this.request = request;
//	}
//	
	public String getId() {
		throw new NotImplementException();
	}
	
	public Object getSession(String key) {
		
		throw new NotImplementException();
	}

	public void removeSession(String key) {
		throw new NotImplementException();
	}

	public void setSession(String key, Object obj) {
		throw new NotImplementException();
	}
}