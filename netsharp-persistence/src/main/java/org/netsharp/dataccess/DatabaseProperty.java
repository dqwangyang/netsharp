package org.netsharp.dataccess;

import org.netsharp.application.ApplicationConfiguration;
import org.netsharp.application.Configuration;
import org.netsharp.application.ConfigItem;
import org.netsharp.dic.DatabaseType;

@Configuration(file = "conf/database.properties")
public class DatabaseProperty extends ApplicationConfiguration {

	@ConfigItem(key="jdbc.driverClass",defaultValue="com.mysql.jdbc.Driver")
	private String driverClass;
	@ConfigItem(key="jdbc.jdbcUrl",defaultValue="")
	private String jdbcUrl;
	@ConfigItem(key="jdbc.username",defaultValue="")
	private String username;
	@ConfigItem(key="jdbc.password",defaultValue="")
	private String password;
	@ConfigItem(key="jdbc.database",defaultValue="")
	private String database;
	
	private DatabaseType type = DatabaseType.MySql;
	


	private static DatabaseProperty dbp;
	public static DatabaseProperty defaultDatabaseProperty() {

		if (dbp == null) {
			dbp = new DatabaseProperty();
			dbp.deserialize();
		}

		return dbp;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDatabase() {
		return database;
	}

	public DatabaseType getType() {
		return type;
	}
}
