package org.netsharp.scrum.meta.bug;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.scrum.entity.Bug;

public class UnfinishedBugWorkspaceTest extends BugWorkspaceTest {
	
	@Override
	@Before
	public void setup() {

		super.setup();
		// 在子类中重定义
		urlList = "/bug/unfinished/list";
		urlForm = "/bug/unfinished/form";

		entity = Bug.class;
		meta = MtableManager.getMtable(entity);
		this.resourceNodeCode="bug-all-unfinished";
		listPartName =formPartName=  "未完成缺陷";
		this.listFilter="status in (1,3)";
	}
}
