package org.netsharp.organization.base;

import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Operation;
import org.netsharp.resourcenode.entity.ResourceNode;

public interface IOperationService extends IPersistableService<Operation> {
	
	void addOperation(ResourceNode node,OperationTypes type);
	void addOperation(ResourceNode node,String code,String name);
	void addOperations(ResourceNode node);
	void addAllOperations(ResourceNode node);
	void addOperations(ResourceNode node,List<OperationTypes> ots);
    // 查询功能权限对应的资源节点
    List<ResourceNode> queryOperationResourceNodes(Boolean isClassification);

    // 功能权限设置时，查询可设置的功能权限
    List<Operation> querySettingOperations(Boolean isClassification);

    // 查询当前用户拥有的所有功能权限,以及没有的字段权限
    List<Operation> queryAuthorizedOperation();
}
