package org.netsharp.core.convertor.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.util.StringManager;

public class StringConvertor implements ITypeConvertor {

	private static final long serialVersionUID = -5705673318620833425L;

	public Object fromString(String value) {
		return value;
	}

	public String toString(Object value) {
		return (String) value;
	}

	public boolean isNullOrEmpty(Object value) {
		if (value == null) {
			return true;
		}

		return StringManager.isNullOrEmpty(value.toString());
	}

	public String toDisplay(Object value) {
		if (value == null) {
			return null;
		} else {
			return value.toString();
		}
	}

	public String toJson(Object value) {
		String returnValue = this.toString(value);

		HashMap<Integer, String> keywords = new HashMap<Integer, String>();

		for (int i = 0; i < returnValue.length(); i++) {
			char c = returnValue.charAt(i);

			if (jsonKeywords.get(String.valueOf(c)) != null) {
				if (i != 0 && returnValue.charAt(i - 1) != '\\') {
					keywords.put(i, String.valueOf(c));
				}
				break;
			}
		}

		if (keywords.size() > 0) {
			StringBuilder builder = new StringBuilder();

			for (int i = 0; i < returnValue.length(); i++) {
				builder.append(String.valueOf(returnValue.charAt(i)));
			}

			returnValue = builder.toString();
		}

		StringBuilder sb = new StringBuilder(returnValue.length() + 20);
		sb.append('\"');

		for (int i = 0; i < returnValue.length(); i++) {
			char c = returnValue.charAt(i);
			switch (c) {
			case '\"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '/':
				sb.append("\\/");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			default:
				sb.append(c);
				break;
			}
		}
		sb.append('\"');

		return sb.toString();
	}

	public Object fromJson(String value) {
		if (value == null) {
			return value;
		}

		value = StringManager.trim(value, '\"');

		Iterator<Entry<String, String>> iter = jsonKeywords.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) iter.next();
			value = value.replace(entry.getValue(), entry.getKey());
		}
		return value;
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

	private static JsonKeywords jsonKeywords = new JsonKeywords();

}
