package org.netsharp.scrum.meta.trace;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.StoryTrace;
import org.netsharp.scrum.meta.story.StoryTraceWorkspaceTest;

public class Trace0Test extends StoryTraceWorkspaceTest {

	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/scrum/trace0/list";
		urlForm = "/scrum/trace/form";
		this.resourceNodeCode = "trace0";

		entity = StoryTrace.class;
		meta = MtableManager.getMtable(entity);
		listPartName = formPartName = "今日跟进";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);

		String filter = "createTime>= CURDATE()";

		datagrid.setFilter(filter);

		return datagrid;
	}

	@Override
	protected void createFormWorkspace() {
	}
}
