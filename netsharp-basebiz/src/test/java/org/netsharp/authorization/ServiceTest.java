package org.netsharp.authorization;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.entity.Operation;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ServiceTest {
	
    //查询功能权限对应的资源节点
	@Test
	public void queryOperationResourceNodes(){
		IOperationService service = ServiceFactory.create(IOperationService.class);
		List<ResourceNode> nodes = service.queryOperationResourceNodes(false);
		
		Assert.assertTrue(nodes.size()>0);
		
		for(ResourceNode node : nodes){
			System.out.println(node.getName());
		}
	}
	
	  // 功能权限设置时，查询可设置的功能权限
    @Test
    public void querySettingOperations(){
    	IOperationService service = ServiceFactory.create(IOperationService.class);
		List<Operation> operations = service.querySettingOperations(false);
		
		Assert.assertTrue(operations.size()>0);
		
		for(Operation node : operations){
			System.out.println(node.getId());
		}
    }

    // 查询当前用户拥有的所有功能权限,以及没有的字段权限
    @Test
    public void queryAuthorizedOperation(){
    	IOperationService service = ServiceFactory.create(IOperationService.class);
		service.queryAuthorizedOperation();
    }
}
