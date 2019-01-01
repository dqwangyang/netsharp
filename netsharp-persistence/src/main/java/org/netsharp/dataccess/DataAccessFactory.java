package org.netsharp.dataccess;

import org.netsharp.application.Application;

public class DataAccessFactory {
	
	public static IDataAccess create(){
		
		DatabaseProperty dbp=DatabaseProperty.defaultDatabaseProperty();
		
		return create(dbp);
	}
	
	public static IDataAccess create(DatabaseProperty dbp){
		
		DbSession session=DbSession.getSession();
		
		if(session==null){
			session = DbSession.start();
		}
		
		if(Application.getInstance().getPerformance()) {
			return new DataAccessProxy(dbp);
		}else {
			return new DataAccess(dbp);
		}
	}
}
