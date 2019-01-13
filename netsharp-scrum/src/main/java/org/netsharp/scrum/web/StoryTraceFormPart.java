package org.netsharp.scrum.web;

import org.netsharp.entity.IPersistable;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.scrum.base.IStoryService;
import org.netsharp.scrum.entity.Story;
import org.netsharp.scrum.entity.StoryTrace;

//项目跟进表单
public class StoryTraceFormPart extends FormPart {
	
	@Override
	public IPersistable newInstance(Object par) {

		this.getService();
		IPersistable entity = this.service.newInstance();
		StoryTrace trace = (StoryTrace)entity;
		
		if(par!=null){
			
			Long storyId = Long.valueOf(par.toString());
			IStoryService prjService = ServiceFactory.create(IStoryService.class);
			Story project = prjService.byId(storyId);
			trace.setStoryId(storyId);
			trace.setStory(project);	
		}

		return entity;
	}
}
