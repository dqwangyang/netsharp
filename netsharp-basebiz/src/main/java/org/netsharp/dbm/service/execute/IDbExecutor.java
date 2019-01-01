package org.netsharp.dbm.service.execute;

import org.netsharp.dbm.entity.DbmResult;

public interface IDbExecutor {
	DbmResult execute(String cmdText);
}
