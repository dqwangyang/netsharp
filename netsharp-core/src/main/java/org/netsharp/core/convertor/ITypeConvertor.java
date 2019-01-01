package org.netsharp.core.convertor;

import java.io.Serializable;

public interface ITypeConvertor extends Serializable{

	Object fromString(String value);

	String toString(Object value);

	boolean isNullOrEmpty(Object value);

	String toDisplay(Object value);

	String toJson(Object value);

	Object fromJson(String value);

	String toXml(Object value);

	Object fromXml(String xml);

	// 生成SQL语句时，值对应的SQL值，如int的2对应2，String的hello对应'hello',Date的2015-09-06对应'2015-09-06'
	// 可能不同的数据库处理方式不同
	String toDbString(Object value);
}
