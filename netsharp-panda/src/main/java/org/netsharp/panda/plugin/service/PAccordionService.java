package org.netsharp.panda.plugin.service;

import org.netsharp.communication.Service;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.plugin.base.IPAccordionService;
import org.netsharp.panda.plugin.entity.PAccordion;
import org.netsharp.panda.plugin.entity.PAccordionItem;
import org.netsharp.service.PersistableService;

@Service
public class PAccordionService extends PersistableService<PAccordion> implements IPAccordionService {

	public PAccordionService() {
		super();
		this.type = PAccordion.class;
	}
	
	@Override
	public PAccordion save(PAccordion entity){
		
		Mtable meta = MtableManager.getMtable(type);
		
		//如果不设置资源则无法导出
		//如果设置资源那么路径可能会出现冲突
		//所以设置资源仅仅为导出，而不进行权限控制
		if(meta.getId().isEmpty(entity.getResourceNodeId())){
			throw new PandaException("PAccordion["+entity.getName()+"]必须设置ResourceNode");
		}
		
		for(PAccordionItem item : entity.getItems()){
			meta = MtableManager.getMtable(PAccordionItem.class);
			
			if(meta.getId().isEmpty(item.getResourceNodeId())){
				throw new PandaException("PAccordionItem["+entity.getName()+"."+item.getName()+"]必须设置ResourceNode");
			}
		}
		
		return super.save(entity);
	}
}
