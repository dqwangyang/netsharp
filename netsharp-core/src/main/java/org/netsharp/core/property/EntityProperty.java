package org.netsharp.core.property;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.NetsharpException;

public class EntityProperty implements IProperty {

	
	private static final long serialVersionUID = -3341675795489453121L;
	protected final Log logger = LogFactory.getLog(this.getClass());
	//
	protected String propertyName;
	protected Field field;
	protected Method getMethod;
	protected Method setMethod;
	protected Class<?> type;
	protected static Object[] getpars = new Object[0];

	public Object field(Object owner) {
		try {
			return field.get(owner);
		} catch (Exception e) {

			logger.error(e);
		}

		return null;
	}

	public void field(Object owner, Object value) {
		try {
			field.set(owner, value);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public Object dbField(Object owner) {
		try {
			return field.get(owner);
		} catch (Exception e) {

			logger.error(e);
		}

		return null;
	}

	public void dbField(Object owner, Object value) {
		try {
			field.set(owner, value);
		} catch (Exception e) {
			throw new NetsharpException("",e);
//			logger.error( "数据库字段读取到实体字段("+owner.getClass().getName() + "." + field.getName()+")错误："+StringManager.NewLine+e );
		}
	}

	public Object get(Object owner) {
		try {
			this.getMethod.invoke(owner, getpars);
		} catch (Exception e) {

			logger.error(this.propertyName+ "  "+e);
		}

		return null;
	}

	public void set(Object owner, Object value) {
		try {
			this.setMethod.invoke(owner, value);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
}
