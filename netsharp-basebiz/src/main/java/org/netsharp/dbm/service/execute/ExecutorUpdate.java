package org.netsharp.dbm.service.execute;

import org.netsharp.dataccess.DataAccessFactory;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.dbm.entity.DbmResult;

public class ExecutorUpdate implements IDbExecutor {

	public DbmResult execute(String cmdText) {
		
		IDataAccess dao = DataAccessFactory.create();
		int count = dao.executeUpdate(cmdText, null);

		DbmResult result = new DbmResult();
		{
			result.setCount(count);
		}

		return result;
	}

}
