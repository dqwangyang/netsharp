package org.netsharp.core;

import java.io.Serializable;

import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.core.convertor.TypeConvertorFactory;
import org.netsharp.core.property.IProperty;
import org.netsharp.entity.DataType;

public class Column implements Serializable{
	
	private static final long serialVersionUID = 3453591365468957980L;
	
	private String propertyName;
	private boolean isPrimaryKey;
    private String columnName;
    private String fieldName;
    private String groupName;
    private Class<?> type;
    private Integer size;
    private Integer precistion;
    private IProperty property;
    private boolean isNameEquals;
    private boolean isAuto;
    private DataType dataType;
    private boolean isRequired;
    private boolean isUnique;
    private String columnTypeName;   //jdbc
    private int columnType=-1;       //jdbc
    private ITypeConvertor convertor;
    private String header;
    private String memoto;
    private boolean mobiles;//
    private boolean sensitive;
    
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public Class<?> getType() {
		return type;
	}
	public void setType(Class<?> dataType) {
		this.type = dataType;
	}
	
	public IProperty getProperty() {
		return property;
	}
	public void setProperty(IProperty property) {
		this.property = property;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	@Override
	public String toString(){
		return this.columnName;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public boolean getIsPrimaryKey() {
		return isPrimaryKey;
	}
	public void setIsPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	public boolean getIsNameEquals() {
		return isNameEquals;
	}
	void setIsNameEquals(boolean isNameEquals) {
		this.isNameEquals = isNameEquals;
	}
	
	public String getSelectName(){
		if(this.isNameEquals){
			return this.propertyName;
		}
		else{
			
			return this.columnName+" AS "+this.propertyName;
		}
	}
	
	public boolean isAuto() {
		return isAuto;
	}
	public void setAuto(boolean isAuto) {
		this.isAuto = isAuto;
	}
	public DataType getDataType() {
		return dataType;
	}
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getPrecistion() {
		return precistion;
	}
	public void setPrecistion(Integer precistion) {
		this.precistion = precistion;
	}
	public boolean isRequired() {
		return isRequired;
	}
	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}
	public boolean isUnique() {
		return isUnique;
	}
	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}
	public ITypeConvertor getConvertor() {
		
		if(this.convertor==null){
			this.convertor=TypeConvertorFactory.create(this.type);
		}
		return convertor;
	}
	public String getColumnTypeName() {
		return columnTypeName;
	}
	public void setColumnTypeName(String columnTypeName) {
		this.columnTypeName = columnTypeName;
	}
	public int getColumnType() {
		return columnType;
	}
	public void setColumnType(int columnType) {
		this.columnType = columnType;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getMemoto() {
		return memoto;
	}
	public void setMemoto(String memoto) {
		this.memoto = memoto;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public boolean isMobiles() {
		return mobiles;
	}
	public void setMobiles(boolean mobiles) {
		this.mobiles = mobiles;
	}
	public boolean isSensitive() {
		return sensitive;
	}
	public void setSensitive(boolean sensitive) {
		this.sensitive = sensitive;
	}
}
