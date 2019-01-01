package org.netsharp.persistence;

import org.netsharp.application.Application;
import org.netsharp.dataccess.DatabaseProperty;
import org.netsharp.entity.IPersistable;

public class PersisterFactory {

	public static <T extends IPersistable>  IPersister<T> create(){
		
		return create(DatabaseProperty.defaultDatabaseProperty());
		
	}
	
	public static <T extends IPersistable>  IPersister<T> create(DatabaseProperty dbp){
		
		if(Application.getInstance().getPerformance()) {
			return new PersisterProxy<T>(dbp);
		}else {
			return new Persister<T>(dbp);
		}
	}
}
