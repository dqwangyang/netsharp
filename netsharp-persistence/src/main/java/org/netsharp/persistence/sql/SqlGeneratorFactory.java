package org.netsharp.persistence.sql;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.dic.DatabaseType;

public class SqlGeneratorFactory {
	private static final Log logger = LogFactory.getLog(SqlGeneratorFactory.class);
	//
	private static HashMap<DatabaseType, ISqlGenerator> sqlGenerators = new HashMap<DatabaseType, ISqlGenerator>();

	public static ISqlGenerator create(DatabaseType dbType) {
		ISqlGenerator generator = sqlGenerators.get(dbType);

		if (generator != null) {
			return generator;
		}

		if (dbType == DatabaseType.MySql) {
			generator = new MySqlGenerator();
		} else if (dbType == DatabaseType.Oracle) {
			generator = new MySqlGenerator();
		} else if (dbType == DatabaseType.SqlServer) {
			generator = new SqlServerGenerator();
		} else {
			logger.error("不支持的数据库类型:" + dbType);
		}

		sqlGenerators.put(dbType, generator);

		return generator;
	}
}
