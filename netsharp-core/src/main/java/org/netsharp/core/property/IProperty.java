package org.netsharp.core.property;

import java.io.Serializable;

public interface IProperty extends Serializable{
	
	Object get(Object owner);
    void set(Object owner,Object value);
    
	Object field(Object owner);
    void field(Object owner,Object value);
    
    Object dbField(Object owner);
    void dbField(Object owner,Object value);
    
    String getPropertyName();
	void setPropertyName(String propertyName);
	
	Class<?> getType();
	
	void setType(Class<?> type);
}
