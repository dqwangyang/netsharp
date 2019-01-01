package org.netsharp.cache.plugin.service;

import org.netsharp.cache.base.CacheCommandFactory;
import org.netsharp.cache.base.ICacheCommand;
import org.netsharp.cache.base.dict.CacheType;
import org.netsharp.cache.plugin.base.ICachePluginService;
import org.netsharp.cache.plugin.entity.CachePlugin;
import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.NotImplementException;
import org.netsharp.service.PersistableService;

@Service
public class CachePluginService extends PersistableService<CachePlugin> implements ICachePluginService {
	
	ICacheCommand cacheCommand = CacheCommandFactory.create(CacheType.redis,"session");
	public CachePluginService() {
		super();
		this.type = CachePlugin.class;
	}
	
	@Override
	public DataTable getKeys(String pattern){
		
		throw new NotImplementException();
		
//		Set<byte[]> keySet = cacheCommand.keys(pattern);
//		DataTable table = new DataTable();
//		if(keySet!=null && keySet.size()>0){
//			int i = 0;
//			for(byte[] byteArray : keySet){
//
//				Row row = table.newLine();
//				row.add("id", i);
//				row.add("fullKey", new String(byteArray));
//				i++;
//			}
//		}
//		return table;
	}

	@Override
	public boolean flushDB(){
		
		try{
			cacheCommand.flushDB();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@Override
	public boolean delteByKey(String key){
		
		try{
			cacheCommand.del(key);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@Override
	public Object viewByKey(String key){
		
		Object object = cacheCommand.get(key);

		return object;
	}
}
