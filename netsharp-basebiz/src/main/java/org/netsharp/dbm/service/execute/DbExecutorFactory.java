package org.netsharp.dbm.service.execute;

import org.netsharp.core.BusinessException;
import org.netsharp.dbm.service.parse.DbmParser;
import org.netsharp.dbm.service.parse.DbmQL;
import org.netsharp.util.StringManager;

public class DbExecutorFactory {

	public static IDbExecutor crate(String cmdText) {

		cmdText = StringManager.trim(cmdText, ' ');

		if (cmdText.startsWith("select")) {

			DbmParser parser = new DbmParser();
			DbmQL dbmQL = parser.parse(cmdText);
			
			if(dbmQL.isOql()) {
				return new ExecutorOql(dbmQL);
			}else {
				return new ExecutorSelect(dbmQL);
			}
		} else {
			
			throw new BusinessException("只允许执行查询语句");
			
			//return new ExecutorUpdate();
		}
	}
}
