package org.netsharp.scrum.service;

import org.netsharp.communication.Service;
import org.netsharp.scrum.base.IProjectService;
import org.netsharp.scrum.entity.Project;
import org.netsharp.service.BizService;

@Service
public class ProjectService extends BizService<Project> implements IProjectService {
	public ProjectService(){
		this.type = Project.class;
	}
}
