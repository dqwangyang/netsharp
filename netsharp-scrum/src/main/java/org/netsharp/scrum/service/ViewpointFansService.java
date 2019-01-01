package org.netsharp.scrum.service;

import org.netsharp.communication.Service;
import org.netsharp.scrum.base.IViewpointFansService;
import org.netsharp.scrum.entity.ViewpointFans;
import org.netsharp.service.PersistableService;

@Service
public class ViewpointFansService extends PersistableService<ViewpointFans> implements IViewpointFansService {

	public ViewpointFansService() {
		super();
		this.type = ViewpointFans.class;
	}
}