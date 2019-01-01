package org.netsharp.persistence.sql;

import java.util.ArrayList;

import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.Oql;
import org.netsharp.dic.DatabaseType;
import org.netsharp.persistence.oql.parser.table.TableParser;
import org.netsharp.persistence.sql.full.FullSelectGenerator;
import org.netsharp.util.StringManager;

public abstract class AbstractSqlGenerator implements ISqlGenerator {

	protected DatabaseType dbType;
	protected String prefix;
	protected String parameterPrefix;

	public SqlStruct generate(Mtable meta, SqlGeneratorType operationType, Object... pars) {
		SqlStruct ss = null;

		if (operationType == SqlGeneratorType.Insert) {
			ss = generateInsertSql(meta);
		} else if (operationType == SqlGeneratorType.Delete) {
			ss = generateDeleteSql(meta);
		} else if (operationType == SqlGeneratorType.Update) {
			ss = generateUpdateSql(meta);
		} else if (operationType == SqlGeneratorType.IsExsit) {
			ss = generateIsExsitsSql(meta);
		} else if (operationType == SqlGeneratorType.FullSelect) {
			String selects = new FullSelectGenerator(meta, "*").generateSql();
			ss = new SqlStruct();
			ss.setSqlString(selects);
		} else if (operationType == SqlGeneratorType.CompositeSelect) {
			FullSelectGenerator generator = new FullSelectGenerator(meta, "*");
			generator.setIsCompositeOnly(true);

			String selects = generator.generateSql();
			ss = new SqlStruct();
			ss.setSqlString(selects);
		} else if (operationType == SqlGeneratorType.Ts) {

			String selects = new FullSelectGenerator(meta, "{" + meta.getKeyColumn().getPropertyName() + ",Ts}").generateSql();
			ss = new SqlStruct();
			ss.setSqlString(selects);
		} else if (operationType == SqlGeneratorType.SimpleSelect) {
			ss = generateSimpleSelectSql(meta);
		}

		return ss;
	}

	protected SqlStruct generateSimpleSelectSql(Mtable meta) {
		String tableName = meta.getTableName();

		ArrayList<String> columns = new ArrayList<String>();

		for (Column column : meta.getColumns()) {
			if(columns.contains(column.getColumnName())){
				//避免重复的列
				continue;
			}
			columns.add(column.getColumnName());
		}

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ").append(StringManager.join(",", columns)).append(StringManager.NewLine).append(" FROM ").append(tableName).append(Oql.WithNolock()).append(StringManager.NewLine);

		SqlStruct ss = new SqlStruct(builder.toString(), null);

		return ss;
	}

	protected SqlStruct generateInsertSql(Mtable meta) {
		String tableName = meta.getTableName();

		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<String> parameters = new ArrayList<String>();
		ArrayList<ParamField> paramField = new ArrayList<ParamField>();

		for (Column column : meta.getColumns()) {
			if(columns.contains(column.getColumnName())){
				//避免重复的列
				continue;
			}
			if (column.getPropertyName() == "Ts") {
				continue;
			}
			if (column.isAuto()) {
				continue;
			}

			String parName = this.parameterPrefix + column.getPropertyName();
			columns.add(column.getColumnName());
			parameters.add("?");

			ParamField pf = new ParamField(parName, column.getPropertyName());
			paramField.add(pf);
		}

		StringBuilder strSqlBuilder = new StringBuilder("INSERT INTO ").append(tableName).append("(").append(StringManager.join(",", columns)).append(") VALUES(").append(StringManager.join(",", parameters)).append(") ");

		SqlStruct ss = new SqlStruct(strSqlBuilder.toString(), paramField.toArray(new ParamField[] {}));

		return ss;
	}

	protected SqlStruct generateDeleteSql(Mtable meta) {

		ArrayList<ParamField> paramField = new ArrayList<ParamField>();
		StringBuilder strSqlBuilder = new StringBuilder();

		String tableName = meta.getTableName();
		ArrayList<String> whereString = new ArrayList<String>();

		Column column = meta.getKeyColumn();
		whereString.add(column.getColumnName() + "=?");
		String parName = parameterPrefix + column.getColumnName();
		ParamField pf = new ParamField(parName, column.getPropertyName());
		paramField.add(pf);

		strSqlBuilder.append(" DELETE FROM ").append(tableName).append(StringManager.NewLine).append(" WHERE ").append(StringManager.join(StringManager.NewLine + " AND ", whereString)).append(StringManager.NewLine);

		return new SqlStruct(strSqlBuilder.toString(), paramField.toArray(new ParamField[] {}));
	}

	protected SqlStruct generateUpdateSql(Mtable meta) {
		return generateUpdateSql(meta, null);
	}

	private SqlStruct generateUpdateSql(Mtable meta, String[] properties) {
		if (properties == null) {
			ArrayList<String> propertyList = new ArrayList<String>();
			for (Column property : meta.getColumns()) {
				if (property.getColumnName() == "Ts") {
					continue;
				}

				if (property.getIsPrimaryKey()) {
					continue;
				}

				propertyList.add(property.getPropertyName());
			}

			properties = propertyList.toArray(new String[] {});
		}

		ArrayList<ParamField> paramField = new ArrayList<ParamField>();
		ArrayList<String> sets = new ArrayList<String>();
		ArrayList<String> keys = new ArrayList<String>();
		StringBuilder strSqlBuilder = new StringBuilder();

		// seters Property
		for (Column column : meta.getColumns()) {
			if (column.getPropertyName() == "Ts") {
				continue;
			}

			if (column.getIsPrimaryKey()) {
				continue;
			}

			String parName = this.prefix + column.getColumnName();
			sets.add(column.getColumnName() + "=?");

			ParamField pf = new ParamField(parName, column.getPropertyName());
			paramField.add(pf);
		}

		// key where
		Column pk = meta.getKeyColumn();
		String parName = this.prefix + pk.getColumnName();
		keys.add(pk.getColumnName() + "=?");
		ParamField pf = new ParamField(parName, pk.getPropertyName());
		paramField.add(pf);

		strSqlBuilder.append("UPDATE ").append(meta.getTableName()).append(StringManager.NewLine).append(" SET ").append(StringManager.join("," + StringManager.NewLine, sets)).append(StringManager.NewLine).append(" WHERE ").append(StringManager.join(StringManager.NewLine + " AND ", keys));

		return new SqlStruct(strSqlBuilder.toString(), paramField.toArray(new ParamField[] {}));
	}

	public SqlStruct generateIsExsitsSql(Mtable meta) {
		SqlStruct ss = new SqlStruct();

		Column keyColumn = meta.getKeyColumn();
		ParamField[] paramFields = new ParamField[1];
		ss.setParamFields(paramFields);
		ArrayList<String> ids = new ArrayList<String>();
		ParamField par = new ParamField(this.parameterPrefix + keyColumn.getPropertyName(), keyColumn.getPropertyName());
		paramFields[0] = par;
		ids.add(keyColumn.getColumnName() + "=?");

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(0) FROM ").append(meta.getTableName()).append(Oql.WithNolock()).append(" WHERE ").append(StringManager.join(",", ids));

		ss.setSqlString(builder.toString());

		return ss;
	}

	public String generateOql(Oql oql) {
		TableParser parser = new TableParser();
		String cmdText = parser.parseTable(oql);
		return cmdText;
	}

	public abstract String generatePaging(int pageSize, int pageIndex, String tableName, String queryFields, String primaryKey, String whereClause, String orderByCondition);
}
