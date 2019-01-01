package org.netsharp.cache.service.db;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class CacheItemService extends PersistableService<CacheItem> implements ICacheItemService {
	
	public CacheItemService(){
		super();
		this.type=CacheItem.class;
	}
}
