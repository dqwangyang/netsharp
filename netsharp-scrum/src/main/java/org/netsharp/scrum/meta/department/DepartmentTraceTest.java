package org.netsharp.scrum.meta.department;

import org.junit.Before;
import org.netsharp.scrum.meta.story.StoryTraceWorkspaceTest;

public class DepartmentTraceTest extends StoryTraceWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		
		urlList = "/scrum/department/trace/list";
		urlForm = "/scrum/department/trace/form";
		this.resourceNodeCode="department-trace";
		listFilter = "story.organization_id in ({departments})";
	} 
}
