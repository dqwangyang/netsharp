package org.netsharp.log.service;

import org.netsharp.communication.Service;
import org.netsharp.log.base.INLogService;
import org.netsharp.log.entity.NLog;
import org.netsharp.service.PersistableService;

@Service
public class NLogService extends PersistableService<NLog> implements INLogService {
	
    public NLogService(){
    	super();
    	this.type=NLog.class;
    }
    
}
