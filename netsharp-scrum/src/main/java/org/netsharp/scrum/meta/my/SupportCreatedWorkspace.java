package org.netsharp.scrum.meta.my;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Support;
import org.netsharp.scrum.meta.story.SupportWorkspaceTest;

/*我提的支持*/
public class SupportCreatedWorkspace extends SupportWorkspaceTest {
	
	@Override
	@Before
	public void setup() {
		
		// 在子类中重定义
		urlList = "/scrum/my/support/putor/list";
		urlForm = "/scrum/my/support/putor/form";

		entity = Support.class;
		meta = MtableManager.getMtable(entity);
		this.resourceNodeCode="my-support-putor";
		listPartName =formPartName= "我提的支持";
		
		listFilter = "support.putorId = '{userId}'";
		
	}
	
	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}
