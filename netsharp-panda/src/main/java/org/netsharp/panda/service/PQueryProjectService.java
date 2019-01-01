package org.netsharp.panda.service;

import org.netsharp.communication.Service;
import org.netsharp.panda.base.IPQueryProjectService;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.service.PersistableService;

@Service
public class PQueryProjectService extends PersistableService<PQueryProject> implements IPQueryProjectService {
	
    public PQueryProjectService(){
    	
    	super();
    	this.type=PQueryProject.class;
    }
    
}
