package org.netsharp.persistence.sql;

import org.netsharp.dic.DatabaseType;
import org.netsharp.util.StringManager;

public class MySqlGenerator extends AbstractSqlGenerator {
	public MySqlGenerator() {
		dbType = DatabaseType.MySql;
		parameterPrefix = prefix = "@";

		// parameterPrefix = prefix = "?";
	}

	@Override
	public String generatePaging(int pageSize, int pageIndex, String tableName, String queryFields, String primaryKey, String whereClause, String orderByCondition) {
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT DISTINCT ").append(queryFields).append(" FROM ").append(tableName).append(" ");
		if (!StringManager.isNullOrEmpty(whereClause) && whereClause != "NULL")
			sb.append("WHERE").append(" ").append(whereClause);

		// End of if

		if (!StringManager.isNullOrEmpty(orderByCondition))
			sb.append(" ORDER BY ").append(orderByCondition);
		else
			sb.append(" ORDER BY ").append(primaryKey).append(" DESC");

		// End of if

		int startIndex = pageSize * (pageIndex - 1);

		sb.append(" LIMIT ").append(startIndex).append(",").append(pageSize);

		return sb.toString();
	}
}