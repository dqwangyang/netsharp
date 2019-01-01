package org.netsharp.panda.plugin.service;

import org.netsharp.communication.Service;
import org.netsharp.panda.plugin.base.IPToolbarItemService;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.service.PersistableService;

@Service
public class PToolbarItemService extends PersistableService<PToolbarItem> implements IPToolbarItemService {
	
    public PToolbarItemService()
    {
        super();
        this.type = PToolbarItem.class;
    }

}
