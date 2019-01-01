package org.netsharp.persistence.sql;

public class ParamField {
	
	public ParamField(String strParamName, String strFieldName) {
		this.paramName = strParamName;
		this.fieldName = strFieldName;
	}

	private String paramName;
	private String fieldName;
	
	public String getParamName() {
		return paramName;
	}
	public String getFieldName() {
		return fieldName;
	}
}
