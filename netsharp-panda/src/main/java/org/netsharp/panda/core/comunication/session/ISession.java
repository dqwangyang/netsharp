package org.netsharp.panda.core.comunication.session;

public interface ISession {
	String getId();
	Object getSession(String key);
	void removeSession(String key);
	void setSession(String key, Object obj);
}
