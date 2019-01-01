package org.netsharp.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.application.Application;
import org.netsharp.core.Column;
import org.netsharp.core.DataTable;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.NetsharpException;
import org.netsharp.core.Row;
import org.netsharp.core.annotations.Table;
import org.netsharp.dataccess.DataAccessException;
import org.netsharp.dataccess.DataAccessFactory;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.dataccess.PagingObject;
import org.netsharp.dic.DatabaseType;
import org.netsharp.util.PakcageManage;
import org.netsharp.util.SAXWriter;
import org.netsharp.util.StopWatch;
import org.netsharp.util.StringManager;

public class Db implements IDb {

	protected static final Log logger = LogFactory.getLog(Db.class);
	private IDataAccess dao;

	public Db() {
		this.dao = DataAccessFactory.create();
	}

	public void createTable(Mtable meta) {

		if (meta.getIsView()) {
			return;
		}

		StringBuilder builder = new StringBuilder();
		builder.append("create table ").append(meta.getTableName()).append(StringManager.NewLine);
		builder.append("(").append(StringManager.NewLine);
		ArrayList<String> columnNames = new ArrayList<String>();
		ArrayList<Column> cs = meta.getColumns();

		System.out.println("创建表名：" + meta.getTableName());
		// 主键单独处理
		Column keyColumn = meta.getKeyColumn();
		if (keyColumn == null) {

			System.err.println(meta.getTableName() + "没有主键！");
		}

		String keyColumnDeclare = this.getColumnDeclare(keyColumn);
		builder.append("    ");
		builder.append(keyColumnDeclare);
		builder.append(",");
		builder.append(StringManager.NewLine);

		for (int i = 0; i < cs.size(); i++) {

			Column c = cs.get(i);
			if (c.getIsPrimaryKey()) {
				continue;
			}

			if (columnNames.contains(c.getColumnName())) {
				continue;
			} else {
				columnNames.add(c.getColumnName());
			}
			String columnDeclare = this.getColumnDeclare(c);
			builder.append("    ");
			builder.append(columnDeclare);

			if (i < cs.size() - 1) {
				builder.append(",");
			}

			builder.append(StringManager.NewLine);
		}

		builder.append(")").append(StringManager.NewLine);

		String cmdText = builder.toString();

		logger.trace(cmdText);

		this.dao.executeUpdate(cmdText, null);
	}

	public void reCreateTable(Mtable meta) {
		if (this.isTableExsist(meta.getTableName())) {
			this.dropTable(meta.getTableName());
		}

		this.createTable(meta);
	}

	public void reCreateTable(List<Class<?>> clss) {

		for (Class<?> cls : clss) {
			Mtable meta = MtableManager.getMtable(cls);
			this.reCreateTable(meta);
		}
	}

	public void updateTable(Mtable meta, boolean isdropColumn) {

		try {

			ArrayList<String> columns = new ArrayList<String>();
			String tableName = meta.getTableName();
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT * FROM ").append(tableName).append(" Limit 1");

			String cmdText = builder.toString();
			ResultSet rs = this.dao.executeResultSet(cmdText, null);
			ResultSetMetaData schema = rs.getMetaData();

			int count = schema.getColumnCount();
			for (Column column : meta.getColumns()) {

				// 主键直接跳出,临时解决 hw 2017-12-05
				if (column.getIsPrimaryKey()) {

					continue;
				}
				boolean isNew = true;
				for (int i = 0; i < count; i++) {

					String columnName = schema.getColumnName(i + 1);
					if (column.getColumnName().equalsIgnoreCase(columnName)) {
						isNew = false;
						break;
					}
				}

				if (isNew) {
					String sql = "alter table " + tableName + " add column " + this.getColumnDeclare(column);
					columns.add(sql);
				}
			}

			// for (int i = 0; i < count; i++) {
			//
			// String columnName = schema.getColumnName(i + 1);
			// Column column = meta.getColumn(columnName);
			//
			// if (column == null && isdropColumn) {
			// String sql = "alter table " + tableName + " drop column " +
			// columnName;
			// columns.add(sql);
			// continue;
			// } else {
			// // update
			// }
			// }

			rs.close();
			if (columns.size() > 0) {
				System.out.println("更新表名：" + tableName);
			}
			for (String columnSql : columns) {
				logger.info(columnSql);
				this.dao.executeUpdate(columnSql, null);
			}
		} catch (SQLException ex) {
			throw new DataAccessException(null, null, null, ex);
		}
	}

	public boolean isTableExsist(String tableName) {

		String database = this.dao.getDatabaseProperty().getDatabase();

		String cmdText = "SELECT count(0) FROM `INFORMATION_SCHEMA`.`TABLES`  WHERE table_schema='" + database
				+ "' AND table_name='" + tableName + "'";

		int count = this.dao.executeInt(cmdText, null);

		return count > 0;
	}

	public void dropTable(String tableName) {

		String cmdText = "drop table " + tableName;
		this.dao.executeUpdate(cmdText, null);
	}

	public String getColumnDeclare(Column column) {

		DatabaseType dbt = this.dao.getDatabaseProperty().getType();
		String columnDeclare = column.getColumnName() + " " + column.getDataType().getDbType(dbt);
		if (column.getDataType().isSize() && column.getSize() != null && column.getSize() > 0) {

			columnDeclare += "(" + column.getSize();
			if (column.getPrecistion() != null && column.getPrecistion() > 0) {
				columnDeclare += "," + column.getPrecistion();
			}
			columnDeclare += ")";
		}

		if (column.isAuto()) {
			columnDeclare += " AUTO_INCREMENT";
		}

		if (column.getIsPrimaryKey()) {
			columnDeclare += " PRIMARY KEY";
		}

		if (column.isRequired()) {
			columnDeclare += " NOT NULL";
		}

		if (column.isUnique()) {
			columnDeclare += " UNIQUE";
		}

		return columnDeclare;
	}

	@Override
	public List<String> queryTables() {

		String cmdText = "show tables;";

		DataTable table = this.dao.executeTable(cmdText, null);
		String columnName = table.getColumns().get(0).getColumnName();

		List<String> tables = new ArrayList<String>();

		for (Row row : table) {
			String tableName = (String) row.get(columnName);
			tables.add(tableName);
		}

		return tables;
	}

	public void backup(BackupOption option) {

		StopWatch watch = new StopWatch();
		watch.start();
		List<String> tables = this.queryTables();
		SAXWriter writer = new SAXWriter(option.fileName);
		writer.startDocument();
		writer.startElement("set");
		for (String tableName : tables) {
			this.backup(option, tableName, writer);
		}

		writer.endElement("set");
		writer.endDocument();
		watch.stop();
		System.out.println("备份结束，总耗时：" + watch.getEclipsed());
	}

	private void backup(BackupOption option, String tableName, SAXWriter writer) {

		StopWatch watch = new StopWatch();
		watch.start();

		if (option.pageSize == null) {
			String cmdText = "select * from " + tableName;
			DataTable table = this.dao.executeTable(cmdText, null);
			table.write(writer);
		} else {

			String cmdText = "select count(0) from " + tableName;
			int totalCount = this.dao.executeInt(cmdText, null);
			int pageCount = (totalCount / option.pageSize);
			if (pageCount * option.pageSize < totalCount) {
				pageCount++;
			}

			PagingObject paging = new PagingObject();
			{
				paging.PageSize = option.pageSize;
				paging.PageIndex = 1;
				paging.TableName = tableName;
				paging.Selects = "*";
				// paging.PrimaryKey = "id";
				paging.Filter = null;
				paging.Orderby = null;
			}

			DataTable table = null;
			StopWatch st = new StopWatch();

			for (int i = 0; i < pageCount; i++) {

				st.start();
				paging.PageIndex = i + 1;
				if (i == 0) {
					table = this.dao.executePaging(paging, null);
				} else {
					this.dao.executePaging(paging, null, table);
				}
				st.stop();

				System.out.println(StringManager.padRight(tableName + "    " + i, 40, ' ') + st.getEclipsed());
			}

			if (table != null) {
				table.write(writer);
			}
		}

		watch.stop();

		System.out.println(StringManager.padRight(tableName, 40, ' ') + watch.getEclipsed());
	}

	public void restore(RestoreOption option) {
		throw new NetsharpException("未实现该方法...");
	}

	public DataTable getTableSchema(String tableName) {

		String cmdText = "SELECT * FROM " + tableName + " Limit 1";
		DataTable table = dao.executeTable(cmdText, null);
		return table;
	}

	/* 得到实体未管理的列 */
	public List<String> getUnmanagedColumns(Mtable meta) {

		List<String> ss = new ArrayList<String>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ").append(meta.getTableName()).append(" Limit 1");

		String cmdText = builder.toString();

		try {
			ResultSet rs = this.dao.executeResultSet(cmdText, null);
			ResultSetMetaData schema = rs.getMetaData();

			int count = schema.getColumnCount();

			for (int i = 0; i < count; i++) {

				String columnName = schema.getColumnName(i + 1);
				Column column = meta.getColumn(columnName);
				if (column == null) {
					ss.add(columnName);
				}
			}
		} catch (SQLException ex) {
			throw new NetsharpException(ex.getMessage(), ex);
		}

		return ss;
	}

	/* 删除所有不受实体控制的数据库表，返回所有要删除的表名 */
	public List<String> cleanTable() {

		// 得到实体控制的表
		List<String> metaTables = new ArrayList<String>();
		for (Class<?> cls : this.getAllPersistableEntities()) {
			Mtable meta = MtableManager.getMtable(cls);
			metaTables.add(meta.getTableName().toLowerCase());
		}

		// 得到数据库中的表
		List<String> dbTables = new ArrayList<String>();
		String database = this.dao.getDatabaseProperty().getDatabase();
		String cmd = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '" + database + "'";
		DataTable executeTable = dao.executeTable(cmd, null);

		for (Row r : executeTable) {
			String tableName = r.get("TABLE_NAME").toString().toLowerCase();
			dbTables.add(tableName);
		}

		// 比较
		for (String metaTable : metaTables) {
			dbTables.remove(metaTable);
		}

		return dbTables;
	}

	/* 得到所有可持久化实体 */
	public List<Class<?>> getAllPersistableEntities() {
		// 得到实体类型
		List<Class<?>> clss = new ArrayList<Class<?>>();
		List<Class<?>> persistableEntities = new ArrayList<Class<?>>();

		for (String pack : Application.getInstance().getPackagesToScan().split(",")) {
			clss.addAll(PakcageManage.getClasses(pack, ""));
		}

		for (Class<?> cls : clss) {

			Table annotation = (Table) cls.getAnnotation(Table.class);

			if (annotation == null || annotation.isView()) {
				continue;
			}

			persistableEntities.add(cls);
		}

		return persistableEntities;
	}
}
