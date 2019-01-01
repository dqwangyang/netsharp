package org.netsharp.cache.base;

import java.util.Set;

public interface ICacheCommand {
	void set(String key, Object value);
	void set(String key, int seconds, Object value);
	boolean exists(String key);
	Object get(String key);
	void del(String key);
	public Set<String> keys(String pattern);
	public String flushDB();
}
