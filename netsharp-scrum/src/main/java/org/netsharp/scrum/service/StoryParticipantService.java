package org.netsharp.scrum.service;

import org.netsharp.communication.Service;
import org.netsharp.scrum.base.IStoryParticipantService;
import org.netsharp.scrum.entity.StoryParticipant;
import org.netsharp.service.PersistableService;

@Service
public class StoryParticipantService extends PersistableService<StoryParticipant> implements IStoryParticipantService {
	public StoryParticipantService(){
		super();
		this.type=StoryParticipant.class;
	}
}
