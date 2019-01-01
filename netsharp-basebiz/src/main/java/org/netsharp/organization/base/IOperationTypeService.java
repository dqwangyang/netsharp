package org.netsharp.organization.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;

public interface IOperationTypeService extends IPersistableService<OperationType> {
	
	OperationType byCode(OperationTypes type);
	OperationType byCode(String code);
	
}
