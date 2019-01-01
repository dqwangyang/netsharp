package org.netsharp.core;

import java.lang.reflect.Field;

import org.netsharp.util.ObjectManager;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

public abstract class TableRelation {

    protected String fromEntityId;
    protected String toEntityId;
    protected String foreignProperty; //实体外键
    protected String foreignkey;      //数据库外键
    protected String priamyKey;
    protected boolean isNocopy;
    protected ITable<?> fromTable;
    protected ITable<?> toTable;
    protected Column fkColumn;
    protected String referenceCode;
    protected String referenceName;
    protected String groupName;
    protected Field field;

	public ITable<?> getToTable() {
		
		 if (toTable != null)
         {
             return toTable;
         }
		 
		 toTable=fromTable.getSet().getTable(toEntityId);

		 if (toTable != null)
         {
             return toTable;
         }

		 toTable = MtableManager.getMtable(toEntityId);

         return toTable;
	}

	public void setToTable(ITable<?> toTable) {
		this.toTable = toTable;
	}
	
	public boolean equals(IRow pkrow,IRow fkrow){
		
		Object pk=pkrow.getUid();
		
		return this.equals(pk,fkrow);
	}
	
	public boolean equals(Object pk,IRow fkrow){
		
		Object fk=getForignKeyValue(fkrow);
		
		return ObjectManager.equals(pk, fk);
	}
	
	public boolean equals(IRow pkrow,Object fk){
		
		Object pk=pkrow.getUid();
		
		return ObjectManager.equals(pk, fk);
	}
	
	public Object getForignKeyValue(IRow fkrow){
		
        Object id=fkColumn.getProperty().field(fkrow);
        
        return id;
	}
	
	public Object getPropertyValue(Object obj){

		Object value= ReflectManager.get(this.getField(), obj);
		
		return value;
	}

	public abstract Column getPkColumn();

	public abstract Column getFkColumn();
	
	public TableRelation clone(TableRelation newObject){
		
	    newObject.fromEntityId    = this.fromEntityId;
        newObject.toEntityId      = this.toEntityId;
        newObject.foreignProperty = this.foreignProperty;
        newObject.foreignkey      = this.foreignkey;
        newObject.priamyKey       = this.priamyKey;
        
        newObject.referenceCode   = this.referenceCode;
        newObject.referenceName   = this.referenceName;
        
        newObject.isNocopy = this.isNocopy;
        newObject.field = this.field;
        
        return newObject;
	} 
	
	public String getFromEntityId() {
		return fromEntityId;
	}

	public void setFromEntityId(String fromEntityId) {
		this.fromEntityId = fromEntityId;
	}

	public String getToEntityId() {
		return toEntityId;
	}

	public void setToEntityId(String toEntityId) {
		this.toEntityId = toEntityId;
	}

	public String getForeignProperty() {
		return foreignProperty;
	}

	public void setForeignProperty(String foreignProperty) {
		this.foreignProperty = foreignProperty;
	}

	public String getPriamyKey() {
		return priamyKey;
	}

	public void setPriamyKey(String priamyKey) {
		this.priamyKey = priamyKey;
	}

	public boolean getIsNocopy() {
		return isNocopy;
	}

	public void setIsNocopy(boolean isNocopy) {
		this.isNocopy = isNocopy;
	}

	public ITable<?> getFromTable() {
		return fromTable;
	}

	public void setFromTable(ITable<?> fromTable) {
		this.fromTable = fromTable;
	}
	
	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	public Field getField() {
		return field;
	}

	void setField(Field field) {
		this.field = field;
	}

	public String getForeignkey() {
		if(StringManager.isNullOrEmpty(this.foreignkey)){
			this.foreignkey = this.getFkColumn().getColumnName();
		}
		
		return foreignkey;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
