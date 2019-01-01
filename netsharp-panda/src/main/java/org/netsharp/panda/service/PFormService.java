package org.netsharp.panda.service;

import org.netsharp.communication.Service;
import org.netsharp.panda.base.IPFormService;
import org.netsharp.panda.entity.PForm;
import org.netsharp.service.PersistableService;

@Service
public class PFormService extends PersistableService<PForm> implements IPFormService {
	
    public PFormService(){
    	super();
    	this.type=PForm.class;
    }
    
}
