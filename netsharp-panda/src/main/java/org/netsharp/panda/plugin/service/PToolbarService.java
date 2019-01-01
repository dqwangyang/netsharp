package org.netsharp.panda.plugin.service;

import org.netsharp.communication.Service;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.plugin.base.IPToolbarService;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.service.PersistableService;

@Service
public class PToolbarService extends PersistableService<PToolbar> implements IPToolbarService {
	
    public PToolbarService()
    {
        super();
        this.type = PToolbar.class;
    }
    
	@Override
	public PToolbar save(PToolbar entity){
		
		Mtable meta = MtableManager.getMtable(type);
		
		if(meta.getId().isEmpty(entity.getResourceNodeId())){
			throw new PandaException("PToolbar["+entity.getName()+"]必须设置ResourceNode");
		}
		
		return super.save(entity);
	}
}
