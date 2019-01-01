package org.netsharp.scrum.meta.bug;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.scrum.entity.Bug;

public class MyBugWorkspaceTest extends BugWorkspaceTest {
	
	@Override
	@Before
	public void setup() {

		super.setup();
		// 在子类中重定义
		urlList = "/bug/testor/my/list";
		urlForm = "/bug/testor/my/form";

		entity = Bug.class;
		meta = MtableManager.getMtable(entity);
		this.resourceNodeCode="bug-testor-my";
		listPartName =formPartName=  "我的缺陷";
		this.listFilter="testorId = '{userId}' or developerId = '{userId}'";
	}
}
