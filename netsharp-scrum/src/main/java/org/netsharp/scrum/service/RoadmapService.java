package org.netsharp.scrum.service;

import org.netsharp.communication.Service;
import org.netsharp.scrum.base.IRoadmapService;
import org.netsharp.scrum.entity.Roadmap;
import org.netsharp.service.BizService;

@Service
public class RoadmapService extends BizService<Roadmap> implements IRoadmapService {
	
	public RoadmapService(){
		this.type = Roadmap.class;
	}
}
