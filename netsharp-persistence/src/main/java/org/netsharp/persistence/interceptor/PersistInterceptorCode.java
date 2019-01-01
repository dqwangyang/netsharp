package org.netsharp.persistence.interceptor;

import org.netsharp.core.Mtable;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.entity.IPersistable;

public class PersistInterceptorCode implements IPersistInterceptor {
	
	 public void persistNew(IPersistable entity, Mtable meta, IDataAccess dao, PersistInterceptorArg arg)
     {
         //CodeBiz.PersistNew(entity as BizEntity);
     }

     public void persist(IPersistable entity, Mtable meta, IDataAccess dao, PersistInterceptorArg arg)
     {
     }

     public void delete(IPersistable entity, Mtable meta, IDataAccess dao, PersistInterceptorArg arg)
     {
     }
}
