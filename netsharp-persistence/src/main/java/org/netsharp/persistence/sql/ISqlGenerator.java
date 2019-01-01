package org.netsharp.persistence.sql;

import org.netsharp.core.Mtable;

public interface ISqlGenerator {

	SqlStruct generate(Mtable meta, SqlGeneratorType operationType, Object... pars);

	String generatePaging(int pageSize, int pageIndex, String tableName, String queryFields, String primaryKey, String whereClause, String orderByCondition);

}
