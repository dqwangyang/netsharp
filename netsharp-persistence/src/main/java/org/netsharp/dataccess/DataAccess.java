package org.netsharp.dataccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.Column;
import org.netsharp.core.DataTable;
import org.netsharp.core.DatatypeManager;
import org.netsharp.core.QueryParameter;
import org.netsharp.core.QueryParameters;
import org.netsharp.core.Row;
import org.netsharp.core.property.ColumnProperty;
import org.netsharp.core.property.IProperty;
import org.netsharp.dataccess.replace.ReplaceManage;
import org.netsharp.entity.DataType;
import org.netsharp.util.DES;
import org.netsharp.util.StringManager;

public class DataAccess implements IDataAccess {

	protected static final Log logger = LogFactory.getLog(IDataAccess.class.getName());
	protected Connection con;
	DatabaseProperty databaseProperty;
	
	DataAccess(DatabaseProperty dbp){
		this.databaseProperty = dbp;
	}

	public void open() {

		DbSession session = DbSession.getSession();
		if (session == null) {
			session = DbSession.start();
		}

		this.con = session.getConnection();
	}

	public void close() throws DataAccessException {
		DbSession session = DbSession.getSession();
		if (session != null) {
			session.close();
		}
	}

	public ResultSet executeResultSet(String cmdText, QueryParameters qps) throws DataAccessException {
		
		this.open();
		try {
			if (!cmdText.contains("?")) {
				qps = null;
			}
			PreparedStatement cmd = this.prepareStatement(cmdText, qps);
			ResultSet rs = cmd.executeQuery();
			
			return rs;
		} catch (SQLException e) {
			throw new DataAccessException("executeResultSet", cmdText, qps, e);
		}
	}

	protected PreparedStatement prepareStatement(String cmdText, QueryParameters qps) throws DataAccessException {

		this.open();

		cmdText = ReplaceManage.replace(cmdText);

		logger.trace(StringManager.NewLine + cmdText);

		try {
			PreparedStatement cmd = con.prepareStatement(cmdText);

			if (qps != null && qps.size() > 0) {
				for (int i = 0; i < qps.size(); i++) {

					QueryParameter qp = qps.get(i);
					Object value = qp.getValue();

					if (qp.isEncryption() && value != null) {
						value = DES.encrypt(value.toString(), DES.getKey());
					}

					if (value instanceof UUID) {
						cmd.setObject(i + 1, value, Types.CHAR);
					} else {
						cmd.setObject(i + 1, value);
					}
				}
			}

			return cmd;

		} catch (Exception e) {

			throw new DataAccessException("prepareStatement", cmdText, qps, e);
		}
	}

	protected PreparedStatement prepareStatement(String cmdText, QueryParameters qps, int autoGeneratedKeys)
			throws DataAccessException {

		this.open();

		cmdText = ReplaceManage.replace(cmdText);
		logger.trace(cmdText);

		try {
			PreparedStatement cmd = con.prepareStatement(cmdText, autoGeneratedKeys);

			if (qps != null && qps.size() > 0) {
				for (int i = 0; i < qps.size(); i++) {

					QueryParameter qp = qps.get(i);
					Object value = qp.getValue();

					if (qp.isEncryption() && value != null) {
						value = DES.encrypt(value.toString(), DES.getKey());
					}

					if (value instanceof UUID) {
						cmd.setObject(i + 1, value, Types.CHAR);
					} else {
						cmd.setObject(i + 1, value);
					}
				}
			}

			return cmd;

		} catch (Exception e) {

			throw new DataAccessException("prepareStatement(autoGeneratedKeys)", cmdText, qps, e);
		}
	}

	public DataTable executeTable(String cmdText, QueryParameters qps) throws DataAccessException {

		DataTable table = this.executeTable(cmdText, qps, null);
		return table;

	}

	private DataTable executeTable(String cmdText, QueryParameters qps, DataTable table) throws DataAccessException {

		this.open();

		try {
			ResultSet rs = this.executeResultSet(cmdText, qps);
			ResultSetMetaData schema = rs.getMetaData();
			int columnsCount = schema.getColumnCount();

			if (table == null) {
				table = new DataTable();
				table.setTableName(schema.getTableName(1));

				for (int i = 1; i <= columnsCount; i++) {
					Column column = new Column();
					table.getColumns().add(column);

					column.setColumnName(schema.getColumnLabel(i));
					column.setPropertyName(column.getColumnName());

					Class<?> javaType = Class.forName(schema.getColumnClassName(i));
					DataType dt = DatatypeManager.ByJavaType(javaType);
					column.setDataType(dt);
					column.setType(javaType);

					column.setColumnTypeName(schema.getColumnTypeName(i));
					column.setColumnType(schema.getColumnType(i));

					column.setSize(schema.getPrecision(i));
					column.setPrecistion(schema.getScale(i));

					IProperty property = new ColumnProperty();
					property.setPropertyName(schema.getColumnLabel(i));
					property.setType(javaType);

					column.setProperty(property);

					//
					column.setAuto(schema.isAutoIncrement(i));
					column.setRequired(schema.isNullable(i) <= 0);
				}
			}

			ArrayList<Column> columns = table.getColumns();

			while (rs.next()) {

				Row row = (Row) table.newLine();

				for (int i = 1; i <= columnsCount; i++) {
					Column column = columns.get(i - 1);
					Object value = rs.getObject(i);

					row.add(column.getColumnName(), value);
				}
			}

			rs.close();

			return table;

		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	public DataTable executePaging(PagingObject paging, QueryParameters qps) {

		IPagingGenerator g = PagingGeneratorFactory.create(this.getDatabaseProperty().getType());
		String cmdText = g.generatePaging(paging);
		return this.executeTable(cmdText, qps);
	}

	public void executePaging(PagingObject paging, QueryParameters qps, DataTable table) {
		
		IPagingGenerator g = PagingGeneratorFactory.create(this.getDatabaseProperty().getType());
		String cmdText = g.generatePaging(paging);

		this.executeTable(cmdText, qps, table);
	}

	public int executeUpdate(String cmdText, QueryParameters qps) throws DataAccessException {

		this.open();

		try {
			PreparedStatement cmd = this.prepareStatement(cmdText, qps);

			return cmd.executeUpdate();

		} catch (SQLException e) {

			throw new DataAccessException("executeUpdate", cmdText, qps, e);
		}
	}

	/**
	 * 
	 * @param cmdText
	 * @param qps
	 * @param autoIncreacement 是否包含自增列
	 * @return 自增列的值
	 * @throws DataAccessException
	 */
	public Object[] executeInsert(String cmdText, QueryParameters qps) throws DataAccessException {

		this.open();

		try {
			PreparedStatement cmd = this.prepareStatement(cmdText, qps, Statement.RETURN_GENERATED_KEYS);
			cmd.executeUpdate();

			ResultSet rs = cmd.getGeneratedKeys();

			ArrayList<Object> numbers = new ArrayList<Object>();
			while (rs.next()) {

//				Object autoNumber = rs.getObject(1);
				Object autoNumber = Integer.parseInt(rs.getObject(1).toString());// 临时解决，hw 2017-10-14
				numbers.add(autoNumber);
			}
			rs.close();

			return numbers.toArray();

		} catch (SQLException e) {

			throw new DataAccessException("executeInsert", cmdText, qps, e);
		}
	}

	public Object executeScalar(String cmdText, QueryParameters qps) throws DataAccessException {

		this.open();

		try {
			Object value = null;
			ResultSet rs = this.executeResultSet(cmdText, qps);

			if (rs.next()) {
				value = rs.getObject(1);
			}

			return value;

		} catch (SQLException e) {

			throw new DataAccessException("executeInsert", cmdText, qps, e);
		}
	}

	public Integer executeInt(String cmdText, QueryParameters qps) {

		this.open();

		Object value = this.executeScalar(cmdText, qps);
		if (value == null) {
			return null;
		}
		return Integer.valueOf(value.toString());
	}

	// ----------------------------------
	// 事务相关
	// ----------------------------------

	public void beginTransaction() {

		DbSession.getSession().beginTransaction();
	}

	public void commit() {

		DbSession.getSession().commit();

	}

	public void rollback() {

		DbSession.getSession().rollback();

	}

	public void rollback(Savepoint savepoint) {

		DbSession.getSession().rollback(savepoint);

	}

	public DatabaseProperty getDatabaseProperty() {
		return databaseProperty;
	}
}
