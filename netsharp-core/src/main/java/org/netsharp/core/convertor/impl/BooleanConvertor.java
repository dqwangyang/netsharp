package org.netsharp.core.convertor.impl;

import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.util.StringManager;

public class BooleanConvertor implements ITypeConvertor {

	private static final long serialVersionUID = 8938132469342828394L;

	public Object fromString(String value) {
		if (StringManager.isNullOrEmpty(value)) {
			return false;
		} else if (StringManager.equals(value, "1", false)) {
			return true;
		} else if (StringManager.equals(value, "true", true)) {
			return true;
		} else {
			return false;
		}
	}

	public String toString(Object value) {
		if (value == null) {
			return "0";
		}
		if (value instanceof Boolean) {
			if ((Boolean) value) {
				return "1";
			} else {
				return "0";
			}
		} else {
			return value.toString();
		}
	}

	public boolean isNullOrEmpty(Object value) {
		return value == null;
	}

	public String toDisplay(Object value) {
		if (value == null) {
			return "否";
		}
		if (value instanceof Boolean) {
			if ((Boolean) value) {
				return "是";
			} else {
				return "否";
			}
		} else {
			return this.toString();
		}
	}

	public String toJson(Object value) {
		if (value == null) {
			return "false";
		}

		return value.toString().toLowerCase();
		// return "\"" + this.toString(value) + "\"";
	}

	public Object fromJson(String value) {
		return this.fromString(value);
	}

	public String toXml(Object value) {
		return this.toString(value);
	}

	public Object fromXml(String xml) {
		return this.fromString(xml);
	}
	
	public String toDbString(Object value) {
		
		if (value == null) {
			return "null";
		}

		if ((Boolean) value) {
			return "1";
		} else {
			return "0";
		}
	}
}
