package org.netsharp.core.convertor.impl;

import java.util.UUID;

import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.util.StringManager;

public class GuidConvertor implements ITypeConvertor {

	private static final long serialVersionUID = 575949705417345454L;

	public Object fromString(String value) {
		if (StringManager.isNullOrEmpty(value)) {
			return null;
		} else {
			return UUID.fromString(value);
		}
	}

	public String toString(Object value) {
		if (value == null) {
			return null;
		} else {
			return value.toString();
		}
	}

	public boolean isNullOrEmpty(Object value) {
		return value == null;
	}

	public String toDisplay(Object value) {
		if (value == null) {
			return null;
		} else {
			return value.toString();
		}
	}

	public String toJson(Object value) {
		return "\"" + this.toString(value) + "\"";
	}

	public Object fromJson(String value) {
		return this.fromString(StringManager.trim(value, '\"'));
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

		return "'"+value.toString()+"'";
	}
}
