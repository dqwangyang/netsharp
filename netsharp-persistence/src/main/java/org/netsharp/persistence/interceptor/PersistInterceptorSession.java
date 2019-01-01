package org.netsharp.persistence.interceptor;

import org.netsharp.core.Mtable;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.entity.IPersistable;

public class PersistInterceptorSession implements IPersistInterceptor {
	
	public void persistNew(IPersistable entity, Mtable meta, IDataAccess dao, PersistInterceptorArg arg)
    {
//        Session session = getSession();
//        if (session != null)
//        {
//            entity.IdCreator = session.UserId;
//            entity.Creator = session.UserName;
//        }
//        entity.CreateTime = DateTime.Now;
    }

    public void persist(IPersistable entity, Mtable meta, IDataAccess dao, PersistInterceptorArg arg)
    {
//        Session session = getSession();
//        entity.IdUpdator = session.UserId;
//        entity.Updator = session.UserName;
//        entity.UpdateTime = DateTime.Now;
    }

    public void delete(IPersistable entity, Mtable meta, IDataAccess dao, PersistInterceptorArg arg)
    {
    }

//    private Session getSession()
//    {
//    	return  null;
////        if (ServiceContext.Current == null || ServiceContext.Current.Session == null)
////        {
////            return SessionManager.Current;
////        }
////        else
////        {
////            return ServiceContext.Current.Session;
////        }
//    }
}
