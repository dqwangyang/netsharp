package org.netsharp.scrum.meta.my;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.StoryTrace;
import org.netsharp.scrum.meta.story.StoryTraceWorkspaceTest;

public class MyTraceWorkspaceTest extends StoryTraceWorkspaceTest {

	@Override
	@Before
	public void setup() {
		super.setup();
		// 在子类中重定义
		urlList = "/scrum/my/trace/list";
		urlForm = "/scrum/my/trace/form";
		this.resourceNodeCode = "mytrace";
		listFilter = "creatorId='{userId}'";
		this.listPartJsController = "org.netsharp.scrum.web.StoryTraceListPart";
		this.listPartImportJs = "/addins/scrum/StoryTraceListPart.js";
		entity = StoryTrace.class;
		meta = MtableManager.getMtable(entity);
		listPartName = formPartName = "我的跟进";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setAutoQuery(false);
//		String filter = "createTime>= CURDATE()";
//		datagrid.setFilter(filter);
		return datagrid;
	}

	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}