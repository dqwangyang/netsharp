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

public class IterationCurrentStoryWorkspaceTest extends StoryWorkspaceTest {
	
	@Override
	@Before
	public void setup() {
		super.setup();
		// 在子类中重定义
		urlList = "/scrum/story/CurrentIterationProject/list";
		urlForm = "/scrum/story/form";
		listFilter = "iteration.is_current=1";
		resourceNodeCode="currentIterationProject";
		entity = Story.class;
		meta = MtableManager.getMtable(entity);

		this.listPartJsController = "org.netsharp.scrum.web.StoryListPart";
		this.listPartImportJs = "/addins/scrum/StoryListPart.js";
		this.listToolbarPath = "story/list/toolbar";
		formServiceController = StoryFormPart.class.getName();
	}
	
	@Test
	public void run() {
		this.createListWorkspace();
		this.createFormWorkspace();
	}
}
