package org.netsharp.panda.plugin.service;

import org.netsharp.communication.Service;
import org.netsharp.panda.plugin.base.IPNavigationItemService;
import org.netsharp.panda.plugin.entity.PNavigationItem;
import org.netsharp.service.PersistableService;

@Service
public class PNavigationItemService extends PersistableService<PNavigationItem> implements IPNavigationItemService {

	public PNavigationItemService() {
		super();
		this.type = PNavigationItem.class;
	}
}