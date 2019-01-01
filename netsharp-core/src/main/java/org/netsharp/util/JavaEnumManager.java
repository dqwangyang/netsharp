package org.netsharp.util;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.base.IEnum;

public class JavaEnumManager {
	
	protected static Log logger = LogFactory.getLog(JavaEnumManager.class);
	
	public static String getText(Enum<?> item){
		
		try
		{
			Class<?> type= item.getClass();
			
			Field textField = type.getDeclaredField("text");
			if (textField != null) {
				textField.setAccessible(true);
				String text = (String) textField.get(item);
				
				return text;
			}
		}
		catch(Exception ex){
			logger.error("读取枚举异常！",ex);
		}
		
		return item.name();
	}
	
	public static Map<String, String> enumAsMapList(Class<? extends Enum<?>> enumClass) {
		
		OrdinalMap<String, String> retMap = new OrdinalMap<String, String>();
		Enum<?>[] enumElems = enumClass.getEnumConstants();
		Field textField = null;
		try {
			textField = enumClass.getDeclaredField("text");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < enumElems.length; i++) {
			Enum<?> enumElem = enumElems[i];
			String name = enumElem.name();
			String text = name;
			if (textField != null) {
				try {
					textField.setAccessible(true);
					text = (String) textField.get(enumElem);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			retMap.put(name, text);
		}
		return retMap;
	}
	
	public static Map<String, String> enumAsMapList2(Class<?> enumClass) {
		
		OrdinalMap<String, String> retMap = new OrdinalMap<String, String>();
		Enum<?>[] enumElems = (Enum<?>[]) enumClass.getEnumConstants();
		Field textField = null;
		try {
			textField = enumClass.getDeclaredField("text");
		} catch (Exception ex) {
			String message = "枚举"+ enumClass.getName()+"没有text属性";
			System.out.println(message);
		}
		for (int i = 0; i < enumElems.length; i++) {
			IEnum enumElem =(IEnum) enumElems[i];
			String name = enumElem.getValue().toString();
			String text = name;
			if (textField != null) {
				try {
					textField.setAccessible(true);
					text = (String) textField.get(enumElem);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			retMap.put(name, text);
		}
		return retMap;
	}
}
