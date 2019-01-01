package org.netsharp.scrum.meta.department;

import org.junit.Before;
import org.netsharp.scrum.meta.story.StoryWorkspaceTest;

public class DepartmentStoryLastTest extends StoryWorkspaceTest {

	@Override
	@Before
	public void setup() {
		
		super.setup();
		
		// 在子类中重定义
		urlList = "/scrum/department/last/list";
		urlForm = "/scrum/department/last/form";
		listFilter = "iterationId IN (SELECT MAX(id)-1 FROM scrum_iteration WHERE id IN ( SELECT id FROM scrum_iteration WHERE is_current=1)) and organization_id in ({departments})";
		resourceNodeCode="department-scrum-last";
	}
}
