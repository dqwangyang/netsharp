package org.netsharp.scrum.meta.department;

import org.junit.Before;
import org.netsharp.scrum.meta.story.StoryWorkspaceTest;

public class DepartmentStoryUnfinishedTest extends StoryWorkspaceTest{
	@Override
	@Before
	public void setup() {
		
		super.setup();
		
		// 在子类中重定义
		urlList = "/scrum/department/unfinished/list";
		urlForm = "/scrum/department/unfinished/form";
		listFilter = "status in (1,3) and story.status != 10 and organization_id in ({departments})";
		resourceNodeCode="department-scrum-unfinished";
	}
}
