package org.netsharp.appconfig;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.EntityState;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.NetsharpException;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

@Service
public class AppconfigService extends PersistableService<Appconfig> implements IAppconfigService {
	
	public AppconfigService(){
		super(); 
		this.type=Appconfig.class;
	}
	
	public Appconfig byCode(String code){
		
		Oql oql=new Oql();
		{
			oql.setType(type);
			oql.setSelects("Appconfig.*");
			oql.setFilter("code = ?");
			oql.getParameters().add("@code",code,Types.VARCHAR);
		}
		
		Appconfig config= pm.queryFirst(oql);
		return config;
	}
	
	@Override
	public Appconfig save(Appconfig entity){
		
		if(entity.getEntityState() != EntityState.Deleted){

			Mtable meta = MtableManager.getMtable(type);
			if(meta.getId().isEmpty(entity.getResourceNodeId())){
				
				throw new NetsharpException("Appconfig["+entity.getCode()+","+entity.getName()+"]必须设置ResourceNode");
			}
		}
		
		return super.save(entity);
	}
}
