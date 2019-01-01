package org.netsharp.persistence.sql;

import org.netsharp.core.Oql;
import org.netsharp.dic.DatabaseType;
import org.netsharp.util.StringManager;

public class SqlServerGenerator extends AbstractSqlGenerator {
	public SqlServerGenerator() {
		dbType = DatabaseType.SqlServer;
		parameterPrefix = prefix = "@";
	}

	@Override
	public String generatePaging(int pageSize, int pageIndex, String tableName, String queryFields, String primaryKey, String whereClause, String orderByCondition) {
		StringBuilder sb = new StringBuilder();

		// 如果pageSize 或者 pageIndex小于等于0，查询所有记录
		if (pageSize <= 0 || pageIndex <= 0) {
			sb.append("SELECT ").append(queryFields).append(" FROM ").append(tableName).append(Oql.WithNolock());

			if (null != whereClause && 0 != whereClause.length()) {
				sb.append(" WHERE ").append(whereClause);
			}

			if (null != orderByCondition && 0 != orderByCondition.length()) {
				sb.append(" ORDER BY ").append(orderByCondition);
			}
		} else {
			String sqlTemplate = "" + StringManager.NewLine + "SELECT Id FROM (" + StringManager.NewLine + "    SELECT ROW_NUMBER() OVER(order by {0}) AS RowNumber,{1} FROM {2} {3}" + StringManager.NewLine + ") AS PagedTable" + StringManager.NewLine + "WHERE RowNumber BETWEEN {4} AND {5} ";

			if (pageIndex <= 0) {
				pageIndex = 1;
			}
			int startNum = (pageIndex - 1) * pageSize + 1;
			int endNum = pageIndex * pageSize;

			if (StringManager.isNullOrEmpty(orderByCondition)) {
				orderByCondition = primaryKey;
			}

			if (!StringManager.isNullOrEmpty(whereClause)) {
				whereClause = " WHERE " + whereClause;
			}

			sb.append(String.format(sqlTemplate, orderByCondition, queryFields, tableName, whereClause, startNum, endNum));
		}

		return sb.toString();
	}
}