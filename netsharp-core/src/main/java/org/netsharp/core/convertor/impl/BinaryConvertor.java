package org.netsharp.core.convertor.impl;

import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.util.HexHelper;
import org.netsharp.util.StringManager;

public class BinaryConvertor implements ITypeConvertor {

	private static final long serialVersionUID = -2026084763294054156L;

	public Object fromString(String value) {
		return HexHelper.HexStringToBinary(value);
	}

	public String toString(Object value) {
		if (value == null) {
			return null;
		} else {
			return HexHelper.BinaryToHexString((byte[]) value);
		}
	}

	public boolean isNullOrEmpty(Object value) {
		return value == null;
	}

	public String toDisplay(Object value) {
		return value.toString();
	}

	public String toJson(Object value) {
		return "\"" + this.toString(value) + "\"";
	}

	public Object fromJson(String value) {
		value = StringManager.trim(value, '\"');

		return this.fromString(value);
	}

	public String toXml(Object value) {
		return this.toString(value);
	}

	public Object fromXml(String xml) {
		return this.fromString(xml);
	}
	
	public String toDbString(Object value) {
		return this.toString(value);
	}
}
