package org.netsharp.persistence.sql;

//[Serializable]
public class SqlStruct {
	public SqlStruct(String strSql, ParamField[] paramField) {
		this.sqlString = strSql;
		this.paramFields = paramField;
	}

	public SqlStruct() {
	}

	private String sqlString;
	private ParamField[] paramFields;

	public String getSqlString() {
		return sqlString;
	}

	public void setSqlString(String sqlString) {
		this.sqlString = sqlString;
	}

	public ParamField[] getParamFields() {
		return paramFields;
	}

	public void setParamFields(ParamField[] paramFields) {
		this.paramFields = paramFields;
	}

}