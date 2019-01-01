package org.netsharp.util;

public class EnumUtil {
	public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name) {
		T retValue = null;
		try {
			retValue = Enum.valueOf(enumType, name);
		} catch (Exception ex) {
			//
		}
		return retValue;
	}
}
