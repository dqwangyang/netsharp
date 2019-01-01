package org.netsharp.scrum.meta.department;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Support;
import org.netsharp.scrum.meta.story.SupportWorkspaceTest;

public class DepartmentSupportAllTest extends SupportWorkspaceTest{
	@Override
	@Before
	public void setup() {
		
		// 在子类中重定义
		urlList = "/scrum/department/support/finished/list";
		urlForm = "/scrum/department/support/finished/form";

		entity = Support.class;
		meta = MtableManager.getMtable(entity);
		this.resourceNodeCode="department-support-all";
		listPartName =formPartName= "全部支持";
		listFilter = "organization_id in ({departments})";
	}
	
	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}
