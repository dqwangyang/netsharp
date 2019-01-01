package org.netsharp.dataccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*数据源管理*/
public class DataSource {

	private static final Log logger = LogFactory.getLog(DataSource.class.getName());
	private static final boolean connectionPool = false;

	public static Connection getConnection() {
		DatabaseProperty dbp = DatabaseProperty.defaultDatabaseProperty();
		try {
			Class.forName(dbp.getDriverClass());
		} catch (ClassNotFoundException e) {
			throw new DataAccessException("未能加载数据库驱动类:" + dbp.getDriverClass());
		}
		
		try {

			Connection con = null;
			if (connectionPool) {
				con = DataSourcePool.getConnection();
				logger.trace("使用数据库连接池!");
			} else {
				con = DriverManager.getConnection(dbp.getJdbcUrl(), dbp.getUsername(), dbp.getPassword());
				logger.trace("未使用数据库连接池!");
			}
			logger.trace("{ 连接创建");

			return con;
		} catch (SQLException e) {
			throw new DataAccessException("连接数据库失败！", e);
		}
	}
}
