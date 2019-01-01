package org.netsharp.dbm.service;

import org.netsharp.communication.Service;
import org.netsharp.dbm.base.IDbmLogService;
import org.netsharp.dbm.entity.DbmLog;
import org.netsharp.service.PersistableService;

@Service
public class DbmLogService extends PersistableService<DbmLog> implements IDbmLogService {
	
	public DbmLogService() {
		super();
		
		this.type = DbmLog.class;
		
	}
}
