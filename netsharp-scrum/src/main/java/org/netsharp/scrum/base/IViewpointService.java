package org.netsharp.scrum.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.scrum.entity.Viewpoint;

public interface IViewpointService extends IPersistableService<Viewpoint> {
	Viewpoint attachFans(Viewpoint entity);
}
