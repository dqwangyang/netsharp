package org.netsharp.dbm.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.dbm.base.IDbmService;
import org.netsharp.dbm.entity.DbmResult;
import org.netsharp.util.ExceptionUtil;

public class DbmController {
	
	public Result<Object> execute(String cmdText) {
		
		Result<Object> result = new Result<Object>();
		
		try {
			result.setType(Result.Type.info);
			
			Object obj = this.doExecute(cmdText);
			result.setData(obj);
		}
		catch(Exception ex) {
			result.setType(Result.Type.error);
			result.setMessage(ExceptionUtil.extractMsg(ex));
		}
		
		return result;
	}
	
	private Object doExecute(String cmdText) {
		IDbmService dbmService = ServiceFactory.create(IDbmService.class);
		DbmResult list = dbmService.execute(cmdText);
		return list;
	}
	
}
