package org.netsharp.dbm.service;

import org.netsharp.communication.Service;
import org.netsharp.dbm.base.IDbmService;
import org.netsharp.dbm.entity.DbmLog;
import org.netsharp.dbm.entity.DbmResult;
import org.netsharp.dbm.service.execute.DbExecutorFactory;
import org.netsharp.dbm.service.execute.IDbExecutor;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

@Service
public class DbmService implements IDbmService {
	
	public DbmResult execute(String cmdText) {
		
		// 添加日志
		DbmLog log = new DbmLog();
		{
			log.toNew();
			log.setContent(cmdText);
		}
		IPersister<DbmLog> logpm = PersisterFactory.create();
		logpm.save(log);
		
		// 执行语句
		IDbExecutor executor = DbExecutorFactory.crate(cmdText);
		DbmResult result = executor.execute(cmdText);
		
		return result ;
	}
}
