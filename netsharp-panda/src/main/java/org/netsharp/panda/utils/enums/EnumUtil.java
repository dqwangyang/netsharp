package org.netsharp.panda.utils.enums;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.base.IEnum;
import org.netsharp.util.JsonManage;
import org.netsharp.util.OrdinalMap;

public class EnumUtil {
	
	private static final Log logger = LogFactory.getLog(EnumUtil.class.getName());

	/**
	 * 生成DataGrid列的格式化代码
	 * @param enumClass
	 * @return
	 */
	public static String getColumnFormatter(Class<? extends Enum<?>> enumClass) {

		String enumName = enumClass.getSimpleName();
		Map<String, String> map = EnumUtil.enumAsMapList(enumClass);
		String json = JsonManage.serialize2(map).replaceAll("\"", "'");
		StringBuilder builder = new StringBuilder();{

			builder.append(" if(value != null&&value != undefined){");
			builder.append(String.format(" var %s = %s;", enumName, json));
			builder.append(String.format("var propertyName='%s_'+value;", enumName));
			builder.append(String.format("return %s[propertyName];", enumName));
			builder.append("}");
		}
		return builder.toString();
	}

	public static Map<String, String> enumAsMapList(Class<?> enumClass) {

		String enumName = enumClass.getSimpleName();
		OrdinalMap<String, String> retMap = new OrdinalMap<String, String>();
		Enum<?>[] enumElems = (Enum<?>[]) enumClass.getEnumConstants();
		Field textField = null;
		try {
			textField = enumClass.getDeclaredField("text");
		} catch (Exception ex) {
			String message = "枚举" + enumClass.getName() + "没有text属性";
			logger.debug(message);
		}
		for (int i = 0; i < enumElems.length; i++) {
			IEnum enumElem = (IEnum) enumElems[i];
			String name = enumName + "_" + enumElem.getValue().toString();
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
