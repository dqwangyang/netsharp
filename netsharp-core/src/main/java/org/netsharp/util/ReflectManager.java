package org.netsharp.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.ExcelImportException;
import org.netsharp.core.NetsharpException;

/**   
 * @ClassName:  ReflectManager   
 * @Description:反射帮助类
 * @author: 韩伟
 * @date:   2017年9月12日 上午10:00:38   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class ReflectManager {
	
	private static final Log logger = LogFactory.getLog(ReflectManager.class);

	public static Field[] getDeclaredFields(Class<?> type) {

		ArrayList<Field> fields = new ArrayList<Field>();
		while (type != null) {

			Field[] array = type.getDeclaredFields();
			for (Field f : array) {
				fields.add(f);
			}

			type = type.getSuperclass();
		}
		
		return fields.toArray(new Field[fields.size()]);
	}

	public static Field getField(Class<?> type, String name) {

		Class<?> top = type;
		while (type != null) {

			Field f = null;
			try {
				f = type.getDeclaredField(name);
			} catch (Exception e) {
			}

			if (f == null) {
				type = type.getSuperclass();
			} else {
				return f;
			}
		}

		logger.error("can't find field " + top.getName() + "[\"" + name + "\"]");

		return null;
	}

	public static String getPropertyName(String fieldName) {

		String start = fieldName.substring(0, 1).toUpperCase();
		String end = fieldName.substring(1);

		return start + end;
	}

	public static String getFieldName(String propertyName) {
		String start = propertyName.substring(0, 1).toLowerCase();
		String end = propertyName.substring(1);

		return start + end;
	}

	public static Object invoke(Object obj, String methodName, Class<?>[] parameterTypes, Object... args) {

		try {

			Method method = obj.getClass().getMethod(methodName, parameterTypes);
			if (method != null) {
				Object ret = method.invoke(obj, args);
				return ret;
			} else {
				return null;
			}
		} catch (Exception e) {

			if (e.getCause() != null && (e.getCause() instanceof ExcelImportException)) {
				throw new ExcelImportException(ExceptionUtil.extractMsg(e));
			}
			logger.error(e.getCause().getMessage(), e.getCause());

		}

		return null;
	}
	
	public static Object invokeStaticMethod(String typeName,String methodName,Class<?>[] parameterTypes, Object... args) {
		try {
			
			Class<?> type = ReflectManager.getType(typeName);

			Method method = type.getMethod(methodName, parameterTypes);
			if (method != null) {
				Object ret = method.invoke(null, args);
				return ret;
			} else {
				return null;
			}
		} catch (Exception e) {

			logger.error(e);

		}

		return null;
	}

	public static Object invokeWithException(Object obj, String methodName, Class<?>[] parameterTypes, Object... args){

		Method method = null;
		try {
			method = obj.getClass().getMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException | SecurityException e1) {
			throw new NetsharpException(e1);
		}
		
		if (method != null) {
			Object ret = null;
			try {
				ret = method.invoke(obj, args);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
	            Throwable cause = e.getCause();
				throw new NetsharpException(cause.getMessage(),cause);
			}
			return ret;
		} else {
			throw new NetsharpException("未能发现方法：" + obj.getClass().getName() + "." + methodName);
		}
	}

	public static boolean isInterface(Class<?> objType, Class<?> interfaceType) {

		if (objType == interfaceType) {
			return true;
		}

		Class<?>[] types = objType.getInterfaces();

		for (Class<?> type : types) {
			if (interfaceType == type) {
				return true;
			}
		}

		for (Class<?> type : types) {
			if (isInterface(type, interfaceType)) {
				return true;
			}
		}

		Class<?> baseType = objType.getSuperclass();

		if (baseType == null || baseType == Object.class) {
			return false;
		} else {
			return isInterface(baseType, interfaceType);
		}
	}

	public static Class<?> getType(String typeName) {
		
		if (typeName.equals("int")) {
			return int.class;
		} else if (typeName.equals("boolean")) {
			return boolean.class;
		} else if (typeName.equals("long")) {
			return long.class;
		} else if (typeName.equals("byte")) {
			return byte.class;
		} else if (typeName.equals("double")) {
			return double.class;
		} else if (typeName.equals("float")) {
			return float.class;
		} else if (typeName.equals("short")) {
			return short.class;
		} else if (typeName.equals("char")) {
			return char.class;
		}

		try {
			return Class.forName(typeName);
		} catch (ClassNotFoundException e) {

			logger.error("不能得到类型" + typeName, e);
		}

		return null;
	}

	public static Object newInstance(Class<?> type) {
		try {
			return type.newInstance();
		} catch (Exception e) {
			logger.error("不能创建类型：" + type.getName(), e);
		}

		return null;
	}

	public static Object get(Field field, Object obj) {
		try {
			return field.get(obj);
		} catch (Exception e) {
			logger.error("field.get异常：" + e.getMessage(), e);
		}

		return null;
	}

	public static void set(Field field, Object obj, Object propertyValue) {
		try {
			field.set(obj, propertyValue);
		} catch (Exception e) {
			logger.error("field.set异常：" + e.getMessage(), e);
		}
	}

	public static Object newInstance(Class<?> type, Object... pars) {
		Class<?>[] parTypes = new Class<?>[pars.length];
		for (int i = 0; i < pars.length; i++) {
			parTypes[i] = pars[i].getClass();
		}
		Constructor<?> constructor;
		try {
			constructor = type.getConstructor(parTypes);
			return constructor.newInstance(pars);
		} catch (Exception e1) {
			logger.error("创建对象出错：" + type.getName() + "," + e1.getMessage(), e1);
		}

		return null;
	}

	public static Object newInstance(String className, Object... pars) {

		try {
			Class<?> type = Class.forName(className);
			return newInstance(type, pars);
		} catch (ClassNotFoundException e) {
			logger.error("获取类型错误：" + className, e);
		}
		return null;
	}

	public static Method getMethods(Class<?> type, String name) {
		try {
			// return type.getDeclaredMethod( name );
			Method[] methods = type.getMethods();
			for (Method method : methods) {
				if (method.getName().equals(name)) {
					return method;
				}
			}
		} catch (Exception e) {
			logger.error("获取方法错误：" + type.getName() + "." + name, e);
		}

		return null;
	}

	public static Method getMethod(Class<?> type, String name, Class<?>... parameterTypes) {
		try {

			Method method = type.getMethod(name, parameterTypes);

			return method;
		} catch (Exception e) {
			logger.error("获取方法错误：" + type.getName() + "." + name, e);
		}

		return null;
	}
}
