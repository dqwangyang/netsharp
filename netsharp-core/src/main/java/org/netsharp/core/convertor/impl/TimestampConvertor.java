package org.netsharp.core.convertor.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.util.StringManager;

public class TimestampConvertor implements ITypeConvertor {
	
	private static final long serialVersionUID = 6185790004891545933L;
	//
	SimpleDateFormat tf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public Object fromString(String value) {
		if (StringManager.isNullOrEmpty(value)) {
			return null;
		} else {
			Timestamp date = Timestamp.valueOf(value);

			return date;
		}
	}

	public String toString(Object value) {

		if (value == null) {
			return null;
		} else {
			return tf.format((Timestamp) value);
		}
	}

	public boolean isNullOrEmpty(Object value) {
		return value == null;
	}

	public String toDisplay(Object value) {
		if (value == null) {
			return null;
		} else {
			return df.format((Date) value);
		}
	}

	public String toJson(Object value) {
		String s = this.toString(value);

		return "\"" + s + "\"";

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

		if (value == null) {
			return "null";
		}

		Date date = (Date)value;
		String str = "'"+df.format(date)+"'";
		
		return str;
	}
}