package org.netsharp.panda.service;

import org.netsharp.communication.Service;
import org.netsharp.panda.base.IPPartService;
import org.netsharp.panda.entity.PPart;
import org.netsharp.service.PersistableService;

@Service
public class PPartService extends PersistableService<PPart> implements IPPartService {
	
	public PPartService(){
		super();
		this.type=PPart.class;
	}
	
}
