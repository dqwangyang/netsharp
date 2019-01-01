package org.netsharp.persistence.interceptor;

import org.netsharp.core.Mtable;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.entity.IPersistable;

public class PersistInterceptorCategoryEntity implements IPersistInterceptor {
	
	 public void persistNew(IPersistable entity, Mtable meta, IDataAccess dao, PersistInterceptorArg arg)
     {
         //CategoryBiz.PersistNew(entity as CategorySysEntity);
     }

     public void persist(IPersistable entity, Mtable meta, IDataAccess dao, PersistInterceptorArg arg)
     {
     }

     public void delete(IPersistable entity, Mtable meta, IDataAccess dao, PersistInterceptorArg arg)
     {
     }
}
