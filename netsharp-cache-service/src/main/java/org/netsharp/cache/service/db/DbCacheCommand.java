package org.netsharp.cache.service.db;

import java.util.Set;

import org.netsharp.cache.base.ICacheCommand;
import org.netsharp.cache.base.util.SerizlizeUtil;
import org.netsharp.communication.ServiceFactory;

/*数据库缓存实现*/
public class DbCacheCommand implements ICacheCommand {
	
	private String type;
	
    private static ICacheItemService service = ServiceFactory.create(ICacheItemService.class);
    
    public DbCacheCommand(String type) {
    	this.type = type;
    }
    
    public void set(String key, Object value) {
    	
    	if(value == null){
    		return;
    	}
    	
    	byte[] json = SerizlizeUtil.serialize(value);
    	
    	CacheItem item = service.byId(key);
    	if(item==null){
    		item = new CacheItem();
    		{
    			item.setId(key);
    			item.setValue(json);
    			item.setType(this.type);
    			
    			item.toNew();
    		}
    	}
    	else{
    		item.setValue(json);
			item.setType(this.type);
			
			item.toPersist();
    	}

    	service.save(item);
    }

	public Object get(String key, Class<?> type) {
		
		CacheItem item = service.byId(key);
    	if(item==null){
    		return null;
    	}
    	
    	Object obj = SerizlizeUtil.unserizlize(item.getValue());
    	
		return obj;
	}

	public void del(String key) {
    	
    	CacheItem item = new CacheItem();
    	{
    		item.toDeleted();
    		
    		item.setId(key);
    	}
    	
    	service.save(item);
    }
    
	public boolean exists(String key) {
    	CacheItem item = service.byId(key);
    	return item != null;
    }
	
	@Override
	public void set(String key, int seconds, Object value) {
	}

	@Override
	public Set<String> keys(String pattern) {
		return null;
	}

	@Override
	public String flushDB() {
		return null;
	}

	@Override
	public Object get(String key) {
		// TODO Auto-generated method stub
		return null;
	}
}
