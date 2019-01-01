package org.netsharp.panda.annotation;

import java.lang.reflect.Field;

import org.netsharp.core.convertor.ITypeConvertor;

public class HtmlPropertyAnnotation {
	
	public String html;
	public String name;
	public boolean mustWrite = false;
	public String defaultValue;

	public Field Field;
	public ITypeConvertor TypeConvertor;
	public Class<?> serializeType;
}
