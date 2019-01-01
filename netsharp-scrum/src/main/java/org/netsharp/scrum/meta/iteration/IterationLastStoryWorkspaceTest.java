/**  
* @Title: CurrentIterationProjectWorkspaceTest.java 
* @Package com.ykx.panda.it 
* @Description: TODO
* @author hanwei
* @date 2015-5-25 下午12:32:51 
* @version V1.0  
*/ 

package org.netsharp.scrum.meta.iteration;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.scrum.entity.Story;
import org.netsharp.scrum.meta.story.StoryWorkspaceTest;
import org.netsharp.scrum.web.StoryFormPart;

public class IterationLastStoryWorkspaceTest extends StoryWorkspaceTest {
	@Override
	@Before
	public void setup() {		
		super.setup();
		urlList = "/scrum/story/lastIterationProject/list";
		urlForm = "/scrum/story/form";
		listFilter = "iterationId IN (SELECT MAX(id)-1 FROM scrum_iteration WHERE id IN ( SELECT id FROM scrum_iteration WHERE is_current=1))";
		resourceNodeCode="lastIterationProject";
		entity = Story.class;
		meta = MtableManager.getMtable(entity);

		this.listPartJsController = "org.netsharp.scrum.web.StoryListPart";
		this.listPartImportJs = "/addins/scrum/StoryListPart.js";
		this.listToolbarPath = "story/list/toolbar";
		formServiceController = StoryFormPart.class.getName();
	}
	
	@Override
	@Test
	public void run() {
		this.createListWorkspace();
	}
}
