package org.netsharp.organization.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.service.PersistableService;

@Service
public class OperationTypeService extends PersistableService<OperationType> implements IOperationTypeService {
	public OperationTypeService(){
		super();
		this.type=OperationType.class;
	}
	
	public OperationType byCode(OperationTypes type){
		
		OperationType ot = this.byCode(type.name());
		return ot;
	}
	
	public OperationType byCode(String code){
		
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("OperationType.*");
			oql.setFilter("code = ?");
			oql.getParameters().add("@code",code,Types.VARCHAR);
		}
		
		OperationType ot = this.pm.queryFirst(oql);
		return ot;
	}
}
