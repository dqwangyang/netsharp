package org.netsharp.dataccess;

import java.sql.Connection;
import java.sql.SQLException;

import org.netsharp.util.PropertyConfigurer;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DataSourcePool {

	private static BoneCP connectionPool = null;

	// private static final Log logger =
	// LogFactory.getLog(DataSourcePool.class.getName());

	/* 从连接池中得到连接 */
	public static Connection getConnection() throws SQLException {

		if (connectionPool == null) {
			initialize();
		}
		Connection con = connectionPool.getConnection();
		// logger.info("----------boneCP连接池监控begin----------");
		// logger.info("已经创建的数据库连接:" +
		// connectionPool.getTotalCreatedConnections());
		// logger.info("已创建未使用:" + connectionPool.getTotalFree());
		// logger.info("正在使用:" + connectionPool.getTotalLeased());
		// logger.info("----------boneCP连接池监控end----------");

		return con;

	}

	private static void initialize() throws SQLException {

		PropertyConfigurer conf = PropertyConfigurer.newInstance("/conf/database.properties");

		String driver = conf.get("jdbc.driverClass");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DataAccessException("未能加载数据库驱动类:" + driver);
		}

		BoneCPConfig config = new BoneCPConfig();
		{
			config.setJdbcUrl(conf.get("jdbc.jdbcUrl"));
			config.setUsername(conf.get("jdbc.username"));
			config.setPassword(conf.get("jdbc.password"));
			config.setIdleConnectionTestPeriodInMinutes(Long.valueOf(conf.get("jdbc.idleConnectionTestPeriod")));
			config.setConnectionTestStatement(conf.get("jdbc.connectionTestStatement"));
			config.setIdleMaxAgeInMinutes(Long.valueOf(conf.get("jdbc.idleMaxAge")));
			config.setMaxConnectionsPerPartition(Integer.valueOf(conf.get("jdbc.maxConnectionsPerPartition")));
			config.setMinConnectionsPerPartition(Integer.valueOf(conf.get("jdbc.minConnectionsPerPartition")));
			config.setPartitionCount(Integer.valueOf(conf.get("jdbc.partitionCount")));
			config.setAcquireIncrement(Integer.valueOf(conf.get("jdbc.acquireIncrement")));
			config.setStatementsCacheSize(Integer.valueOf(conf.get("jdbc.statementsCacheSize")));
			config.setReleaseHelperThreads(Integer.valueOf(conf.get("jdbc.releaseHelperThreads")));
			config.setLazyInit(Boolean.parseBoolean(conf.get("jdbc.lazyInit")));
		}

		connectionPool = new BoneCP(config);
	}
}