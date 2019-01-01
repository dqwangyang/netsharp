package org.netsharp.panda.service;

import org.netsharp.communication.Service;
import org.netsharp.panda.base.IPQueryItemService;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.service.PersistableService;

@Service
public class PQueryItemService extends PersistableService<PQueryItem> implements IPQueryItemService {

	public PQueryItemService() {
		super();
		this.type = PQueryItem.class;
	}
}
