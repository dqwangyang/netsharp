package org.netsharp.core.convertor.impl;

import java.lang.reflect.Method;

import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.util.StringManager;

public class JavaEnumConvertor implements ITypeConvertor {
	
	private static final long serialVersionUID = 2309450807351034211L;
	private Class<?> enumType;
	
	public JavaEnumConvertor(Class<?> type){
		this.enumType = type;
	}

	@Override
	public Object fromString(String value) {
		
		try {
			Method method = enumType.getMethod("values");
			Object values[] = (Object[]) method.invoke(null);
			 for (Object item : values) {
				 if(StringManager.equals(value, item.toString())) {
					 return item;
				 }
			 }
		}catch(Exception ex) {
			
		}
		
		 
		 return null;
	}

	@Override
	public String toString(Object value) {
		if(value==null){
			return "";
		}

		Enum<?> item = (Enum<?>)value;
		
		return item.name();
	}

	@Override
	public boolean isNullOrEmpty(Object value) {

		return false;
	}

	@Override
	public String toDisplay(Object value) {

		return null;
	}

	@Override
	public String toJson(Object value) {

		String s = this.toString(value);

		return "\"" + s + "\"";
	}

	@Override
	public Object fromJson(String value) {

		return null;
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
