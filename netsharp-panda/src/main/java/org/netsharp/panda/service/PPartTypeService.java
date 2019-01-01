package org.netsharp.panda.service;

import org.netsharp.communication.Service;
import org.netsharp.panda.base.IPPartTypeService;
import org.netsharp.panda.entity.PPartType;
import org.netsharp.service.PersistableService;

@Service
public class PPartTypeService extends PersistableService<PPartType> implements IPPartTypeService {
	
    public PPartTypeService(){
    	
    	super();
    	this.type=PPartType.class;
    	
    }
}
