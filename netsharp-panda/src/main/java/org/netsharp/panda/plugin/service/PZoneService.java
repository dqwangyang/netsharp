package org.netsharp.panda.plugin.service;

import org.netsharp.communication.Service;
import org.netsharp.panda.plugin.base.IPZoneService;
import org.netsharp.panda.plugin.entity.PZone;
import org.netsharp.service.PersistableService;

@Service
public class PZoneService extends PersistableService<PZone> implements IPZoneService{
	public PZoneService(){
		super();
		this.type = PZone.class;
	}
}
