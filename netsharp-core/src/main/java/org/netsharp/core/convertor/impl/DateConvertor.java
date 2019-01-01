package org.netsharp.core.convertor.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.util.StringManager;

public class DateConvertor implements ITypeConvertor {
	
	private static final long serialVersionUID = 8053706625096036757L;
	private final Log logger = LogFactory.getLog(DateConvertor.class);
	//
	SimpleDateFormat tf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public Object fromString(String value) {
		if (StringManager.isNullOrEmpty(value)) {
			return null;
		} else {
			Date date = null;
			try {
				date = tf.parse(value);
			} catch (ParseException e) {

				logger.error("日期序列化错误：" + value + ";" + e.getMessage());
			}

			return date;
		}
	}

	public String toString(Object value) {

		if (value == null) {
			return null;
		} else {
			return tf.format((Date) value);
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
		String str = "'"+tf.format(date)+"'";
		
		return str;
	}
}
