package org.netsharp.scrum.meta.my;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Story;
import org.netsharp.scrum.entity.StoryParticipant;
import org.netsharp.scrum.meta.story.StoryWorkspaceTest;
import org.netsharp.scrum.web.StoryFormPart;

public class StoryUnFinishedWorkspaceTest extends StoryWorkspaceTest {
	
	@Override
	@Before
	public void setup() {	
		
		super.setup();
		urlList = "/scrum/my/unfinished/list";
		urlForm = "/scrum/my/unfinished/form";
		entity = Story.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode="my-scrum-unfinished";
		
		this.listPartJsController = "org.netsharp.scrum.web.StoryListPart";
		this.listPartImportJs = "/addins/scrum/StoryListPart.js";
		this.listToolbarPath = "story/list/toolbar";
		formServiceController = StoryFormPart.class.getName();
		
		String tableName = MtableManager.getMtable(StoryParticipant.class).getTableName();
		listFilter = "status in (1,3) and story.status != 10 and (story.owner_id = '{userId}' or story.id in (select story_id from "+tableName+" where participant_id = '{userId}'))";
	} 
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		{
			queryProject.toNew();
			queryProject.setName("研发任务");
			queryProject.setResourceNode(node);
			queryProject.setColumnCount(4);
		}
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "status", "状态", ControlTypes.ENUM_BOX);

		return queryProject;
	}
	
	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}
