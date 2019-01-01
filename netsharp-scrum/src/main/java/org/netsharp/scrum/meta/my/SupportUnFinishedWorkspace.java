package org.netsharp.scrum.meta.my;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Support;
import org.netsharp.scrum.meta.story.SupportWorkspaceTest;

public class SupportUnFinishedWorkspace extends SupportWorkspaceTest {
	
	@Override
	@Before
	public void setup() {
		
		// 在子类中重定义
		urlList = "/scrum/my/support/unfinished/list";
		urlForm = "/scrum/my/support/unfinished/form";

		entity = Support.class;
		meta = MtableManager.getMtable(entity);
		this.resourceNodeCode="my-support-unfinished";
		listPartName =formPartName= "未完成支持";
		
		listFilter = "status in (1,3) and support.ownerId = '{userId}'";
	}
	
	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}
