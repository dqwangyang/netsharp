package org.netsharp.dataccess;

import org.netsharp.core.QueryParameter;
import org.netsharp.entity.IPersistable;

public interface IInsqlGenerator
{
    String inSql(Iterable<IPersistable> rows, String propertyName, QueryParameter qp);
    String inSql(Iterable<String> ids, QueryParameter qp);
    String inSql(String[] ids, QueryParameter qp);
    String xmlId(Iterable<String> ids);
    boolean isParameter();
}
