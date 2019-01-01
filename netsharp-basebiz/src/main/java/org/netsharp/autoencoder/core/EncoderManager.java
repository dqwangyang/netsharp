package org.netsharp.autoencoder.core;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

import org.netsharp.autoencoder.base.IEncoderService;
import org.netsharp.autoencoder.base.IExpandEncodeRule;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.NetsharpException;
import org.netsharp.core.annotations.BizCode;
import org.netsharp.core.annotations.BizTransaction;
import org.netsharp.entity.IPersistable;

public class EncoderManager {

	public static void main(String[] args) {

	}

	protected static IEncoderService codeService = ServiceFactory.create(IEncoderService.class);

	/**
	 * 方法名称：createBillCode 方法描述：向entity生成编码
	 * 
	 * @param entity
	 *            entity必须需配置@BizCode注解或者配置@BillTransaction注解
	 * @author gaomeng
	 * @since 2016年3月24日 history 2016年3月24日 gaomeng 创建
	 */
	public static <T extends IPersistable> void createBillCode(T entity) {
		
		BizCode annotation = entity.getClass().getAnnotation(BizCode.class);
		if (annotation != null) {
			
			String field = annotation.field();
			Object code = entity.get(field);
			String bizType = getbizType(entity);
			if (code == null || code.equals("")) {
				
				if (bizType != null && !bizType.equals("")) {
					
					String billCode = codeService.getNextCode(bizType, entity.getClass().getName());
					entity.set(field, billCode);
				}
			}
			
			if (!annotation.isExpand().equals("No")) {
				
				String expandClass = codeService.getExpandClassName(bizType, entity.getClass().getName());
				if (expandClass != null && expandClass.length() > 0) {
					try {
						IExpandEncodeRule expandRule = (IExpandEncodeRule) Class.forName(expandClass).newInstance();
						expandRule.process(entity);
					}
					catch(Exception e) {
						String message = String.format("创建编码失败,entityId:", entity.getClass().getName());
						throw new NetsharpException(message,e);
					}
					
				}
			}
		}
	}

	/**
	 * 方法名称：createEncodeRule
	 * 方法描述：生成编码规则，此方法将根据参数直接生成编码规则，不生成编码对象，所以前台不能进行编辑规则，生成的规则只支持
	 * fixCode+dateformat+serialLength位序号,
	 * 生成的编码唯一主键为type.getSimpleName()_briefCode
	 * 
	 * @param type
	 *            实体类型
	 * @param briefCode
	 *            门店简码
	 * @param fixCode
	 *            固定编码
	 * @param dateformat
	 *            日期格式，能通过SimpleDateFormat校验
	 * @param serialLength
	 *            序列长度
	 * @return
	 * @author gaomeng
	 * @since 2016年3月24日 history 2016年3月24日 gaomeng 创建
	 */
	public static String createEncodeRule(Class<?> type, String briefCode, String fixCode, String dateformat, Integer serialLength) {
		if (dateformat != null)
			new SimpleDateFormat(dateformat);
		return codeService.createEncodeRule(type, briefCode, fixCode, dateformat, serialLength);

	}

	private static <T extends IPersistable> String getbizType(T entity) {
		BizCode annotation = entity.getClass().getAnnotation(BizCode.class);
		if (annotation != null) {
			String bizType = annotation.bizType();
			if (bizType != null && !bizType.equals("")) {
				return bizType;
			} else {
				return getbizTypeByFiled(entity);
			}
		}
		return null;
	}

	private static <T> String getbizTypeByFiled(T entity) {
		Class<?> clazz = entity.getClass();
		Field[] fields = null;
		String bizType = null;
		while (!clazz.getSimpleName().equals("Object")) {
			fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(BizTransaction.class)) {
					field.setAccessible(true);
					try {
						bizType = (String) field.get(entity);
					} catch (IllegalArgumentException e) {
					} catch (IllegalAccessException e) {
					}
					return bizType;
				}
			}
			clazz = clazz.getSuperclass();
		}
		return null;
	}

}
