package org.netsharp.dbm.base;

import org.netsharp.dbm.entity.DbmResult;

public interface IDbmService {
	
	DbmResult execute(String cmdText);
	
}
