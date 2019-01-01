package org.netsharp.cache.plugin.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.cache.plugin.entity.CachePlugin;
import org.netsharp.core.DataTable;


public interface ICachePluginService extends IPersistableService<CachePlugin>{
	
	public DataTable getKeys(String pattern);
	
	public boolean flushDB();
	
	public boolean delteByKey(String key);
	
	public Object viewByKey(String key);
}
