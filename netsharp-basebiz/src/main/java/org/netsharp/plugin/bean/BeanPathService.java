package org.netsharp.plugin.bean;

import org.netsharp.communication.Service;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.NetsharpException;
import org.netsharp.plugin.entity.Codonable;
import org.netsharp.service.PersistableService;

@Service
public class BeanPathService extends PersistableService<BeanPath> implements IBeanPathService {
	
	public BeanPathService(){
		
		super();
		this.type = BeanPath.class;
		
	}
	
	@Override
	public BeanPath save(BeanPath entity){
		
		Mtable meta = MtableManager.getMtable(type);
		
		//如果不设置资源则无法导出
		//如果设置资源那么路径可能会出现冲突
		//所以设置资源仅仅为导出，而不进行权限控制
		if(meta.getId().isEmpty(entity.getResourceNodeId())){
			throw new NetsharpException("BeanPath["+entity.getName()+"]必须设置ResourceNode");
		}
		
		for(Codonable item : entity.getCodons()){
			meta = MtableManager.getMtable(Bean.class);
			
			if(meta.getId().isEmpty(item.getResourceNodeId())){
				throw new NetsharpException("Bean["+entity.getName()+"."+item.getName()+"]必须设置ResourceNode");
			}
		}
		
		return super.save(entity);
	}
}
