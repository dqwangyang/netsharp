package org.netsharp.core.property;

import org.netsharp.core.IRow;

public class ColumnProperty implements IProperty {
	
	private static final long serialVersionUID = 4725725303489733067L;

	private String propertyName;
	
	private Class<?> type;
	
	@Override
	public Object field(Object owner) {
		
		IRow row=(IRow)owner;
        return row.get(this.propertyName);
	}

	@Override
	public void field(Object owner, Object value) {
		
		IRow row=(IRow)owner;
	     row.set(this.propertyName, value);
	}
	
	@Override
	public Object dbField(Object owner) {
		
		IRow row=(IRow)owner;
        return row.get(this.propertyName);
	}

	@Override
	public void dbField(Object owner, Object value) {
		
		IRow row=(IRow)owner;
	     row.set(this.propertyName, value);
	}

	@Override
	public Object get(Object owner) {
		
        return this.field(owner);
	}

	@Override
	public void set(Object owner, Object value) {
		
		this.field(owner, value);
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
}
