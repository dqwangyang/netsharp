package org.netsharp.panda.utils.enums;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.base.IEnum;
import org.netsharp.util.JsonManage;
import org.netsharp.util.OrdinalMap;

public class EnumController {
	
	private static final Log logger = LogFactory.getLog(EnumController.class.getName());

	public String getEnumItems(String enumName) throws ClassNotFoundException{
		
		Class<?> type = Class.forName(enumName);
		OrdinalMap<String, String> maps = new OrdinalMap<String, String>();
		Enum<?>[] enumElems = (Enum<?>[]) type.getEnumConstants();
		Field textField = null;
		try {
			textField = type.getDeclaredField("text");
		} catch (Exception ex) {
			String message = "枚举" + type.getName() + "没有text属性";
			logger.debug(message);
		}
		for (int i = 0; i < enumElems.length; i++) {
			IEnum enumElem = (IEnum) enumElems[i];
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
			maps.put(name, text);
		}
		
		String json = JsonManage.serialize2(maps);
		return json;
	}
}
