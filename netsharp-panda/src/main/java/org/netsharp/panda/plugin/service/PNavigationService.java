package org.netsharp.panda.plugin.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.plugin.base.IPNavigationService;
import org.netsharp.panda.plugin.entity.PNavigation;
import org.netsharp.service.PersistableService;

@Service
public class PNavigationService extends PersistableService<PNavigation> implements IPNavigationService {

	public PNavigationService() {
		super();
		this.type = PNavigation.class;
	}
	
	@Override
	public PNavigation save(PNavigation entity){
		
		Mtable meta = MtableManager.getMtable(type);
		
		//如果不设置资源则无法导出
		//如果设置资源那么路径可能会出现冲突
		//所以设置资源仅仅为导出，而不进行权限控制
		if(meta.getId().isEmpty(entity.getResourceNodeId())){
			throw new PandaException("PTree["+entity.getName()+"]必须设置ResourceNode");
		}
		
//		for(PTreeNode item : entity.getTreeNodes()){
//			meta = MtableManager.getMtable(PTreeNode.class);
//			
//			if(meta.getId().isEmpty(item.getResourceNodeId())){
//				throw new PandaException("PTreeNode["+entity.getName()+"."+item.getName()+"]必须设置ResourceNode");
//			}
//		}
		
		return super.save(entity);
	}

	@Override
	public PNavigation byPath(String path) {

		Oql oql = Oql.newOne();
		{
			oql.setSelects("*");
			oql.setType(type);
			oql.setFilter("path=?");
			oql.setParameters(new QueryParameters());
			oql.getParameters().add("@path", path, Types.VARCHAR);
		}

		return this.queryFirst(oql);
	}
}
