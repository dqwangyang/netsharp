package org.netsharp.scrum.meta.viewpoint;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.scrum.entity.Viewpoint;

public class MyViewpointWorkspaceTest extends ViewpointWorkspaceTest {

	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/scrum/my/viewpoint/list";
		urlForm = "/scrum/my/viewpoint/form";
		resourceNodeCode = "viewpoint-my";
		entity = Viewpoint.class;
		meta = MtableManager.getMtable(entity);
		meta = MtableManager.getMtable(entity);
		listPartName = formPartName = meta.getName();
		
		this.listFilter="ownerId = '{userId}'";
	}
}
