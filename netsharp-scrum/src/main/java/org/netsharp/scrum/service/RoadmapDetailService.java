package org.netsharp.scrum.service;

import org.netsharp.communication.Service;
import org.netsharp.scrum.base.IRoadmapDetailService;
import org.netsharp.scrum.entity.RoadmapDetail;
import org.netsharp.service.PersistableService;

@Service
public class RoadmapDetailService extends PersistableService<RoadmapDetail> implements IRoadmapDetailService {
	
	public RoadmapDetailService(){
		this.type = RoadmapDetail.class;
	}
}
