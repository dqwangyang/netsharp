package org.netsharp.core;

import java.io.Serializable;

public class QueryParameter implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7881149247087311017L;
	private String name;
    private Object value;
    private int dbType;
    private boolean encryption=false;
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	public int getDbType() {
		return dbType;
	}
	public void setDbType(int dbType) {
		this.dbType = dbType;
	}
	
	public boolean isEncryption() {
		return encryption;
	}
	public void setEncryption(boolean encryption) {
		this.encryption = encryption;
	}
    
	@Override
	public String toString() {
		return this.name;
	}
}
