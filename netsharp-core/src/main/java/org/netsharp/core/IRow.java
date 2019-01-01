package org.netsharp.core;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.netsharp.entity.IPersistable;

public interface IRow extends IPersistable {

	void add(String name, Object value);
	Object get(String name);
	void set(String name, Object value);
	String getString(String name);
	Short getShort(String name);
	Long getLong(String name);
	Date getDate(String name);
	Integer getInteger(String name);
	Double getDouble(String name);
	Boolean getBoolean(String name);
	BigDecimal getDecimal(String name);
	Float getFloat(String name);
	UUID getGuid(String name);
	ITable<?> getTable();
	Object getUid();
	void setUid(Object id);
	
	HashMap<String,Object> getChanges();
	Set getSet();
}
