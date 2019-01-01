package org.netsharp.persistence.query;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.Mtable;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.core.QueryParameters;
import org.netsharp.core.property.IProperty;
import org.netsharp.dataccess.DataAccessException;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.entity.IPersistable;
import org.netsharp.persistence.Persister;
import org.netsharp.persistence.oql.parser.SbManager;
import org.netsharp.persistence.oql.parser.set.OqlStruct;
import org.netsharp.persistence.sql.ISqlGenerator;
import org.netsharp.persistence.sql.SqlGeneratorFactory;
import org.netsharp.util.StringManager;

public abstract class AbstractQuery {
	protected static Log logger = LogFactory.getLog(Persister.class);

	protected IDataAccess dao;
	protected Oql oql;
	protected TSet set = new TSet();

	public AbstractQuery(IDataAccess dao) {
		this.dao = dao;
	}

	public TSet query(Oql oql)  {
		
		logger.trace(oql.toString());

		this.oql = oql;

		if (oql.getPaging() == null) {
			return this.queryOnece();
		} else {
			return this.queryPaging();
		}
	}

	protected abstract TSet queryOnece() ;

	protected abstract TSet queryPaging() ;

	public int getTotalCount(OqlStruct os, Oql oql)  {

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(").append("DISTINCT ").append(os.Mtable.getCode())
		.append(".")
		.append(os.Mtable.getKeyColumn().getColumnName())
		.append(") FROM ")
		.append(os.Mtable.getTableName()).append(" AS ").append(os.Mtable.getCode()).append(Oql.WithNolock()).append(StringManager.NewLine);

		if (!StringManager.isNullOrEmpty(os.Joins)) {
			builder.append(os.Joins).append(StringManager.NewLine);
		}

		SbManager.appendLineReqired(builder, " WHERE ", os.Wheres);

		String cmdText = builder.toString();

		int totalCount = 0;

		Object count = dao.executeScalar(cmdText, oql.getParameters());
		totalCount = Integer.parseInt(count.toString());

		return totalCount;
	}

	public ArrayList<String> createPaging(OqlStruct os)  {

		String pagingText = generatePaging(os);
		ArrayList<String> ids = new ArrayList<String>();

		//
		dao.open();
		ResultSet reader = dao.executeResultSet(pagingText, this.oql.getParameters());

		try {
			while (reader.next()) {
				Object cellValue = reader.getObject(1);
				if (cellValue != null) {
					ids.add(cellValue.toString());
				}
			}

			reader.close();
		} catch (SQLException ex) {
			throw new DataAccessException("oql query paging reader", pagingText, this.oql.getParameters(), ex);
		}

		return ids;
	}

	public String generatePaging(OqlStruct os) {
		String tableName = os.Mtable.getTableName() + " AS " + os.Mtable.getCode();

		if (!StringManager.isNullOrEmpty(os.Joins)) {
			tableName += (StringManager.NewLine + os.Joins);
		}

		Paging paging = oql.getPaging();

		ISqlGenerator generator = SqlGeneratorFactory.create(dao.getDatabaseProperty().getType());

		String keyProperty = os.Mtable.getKeyColumn().getColumnName();
		String pagingText = generator.generatePaging(paging.getPageSize(), paging.getPageNo(), tableName, os.Mtable.getCode() + "." + keyProperty, os.Mtable.getCode() + "." + keyProperty, os.Wheres, os.Orderby);

		return pagingText;
	}

	protected void readTable(Mtable mtable, String cmdText, QueryParameters qpc) {
		
		TTable table = new TTable();
		table.Mtable = mtable;
		this.set.Tables.add(table);

		if (this.set.Tables.size() == 1) {
			this.set.Main = table;
		}

		try {
			ResultSet rs = dao.executeResultSet(cmdText, qpc);
			ResultSetMetaData schema = rs.getMetaData();
			IProperty[] properties = TableSchema.populate(table.Mtable, schema);
			int columnsCount = schema.getColumnCount();

			while (rs.next()) {
				IPersistable row = table.newItem();
				for (int i = 0; i < columnsCount; i++) {
					IProperty property = properties[i];
					Object value = rs.getObject(i + 1);
					property.dbField(row, value);
				}

				table.Items.add(row);
			}
			rs.close();
		} catch (SQLException ex) {
			throw new DataAccessException("oql readTable", cmdText, qpc, ex);
		}
	}
}
