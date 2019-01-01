package org.netsharp.persistence.interceptor;

import org.netsharp.core.Mtable;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.entity.IPersistable;

public interface IPersistInterceptor
{
    void persistNew(IPersistable entity,Mtable meta, IDataAccess dao,PersistInterceptorArg arg);

    void persist(IPersistable entity, Mtable meta, IDataAccess dao, PersistInterceptorArg arg);

    void delete(IPersistable entity, Mtable meta, IDataAccess dao, PersistInterceptorArg arg);
}
