package org.netsharp.core;

import java.lang.reflect.Field;

import org.netsharp.util.ReflectManager;

public class TableReference extends TableRelation {
	
    public boolean isAuditEdit;
    public boolean isQuery;
    public String filter;
    private String constraint;
    
    @Override
 	public Column getPkColumn() {
 		
 		if(this.fkColumn==null){
 			this.fkColumn=this.getToTable().getKeyColumn();
 		}

 		return this.fkColumn;
 	}

     @Override
 	public Column getFkColumn() {
 		
 		if(fkColumn==null){
 			
 			ITable<?> table=this.fromTable;
 			fkColumn=table.getPropertyOrColumn(this.foreignProperty);
 			
 			if(fkColumn==null){
				System.out.println("外键不存在："+table.getEntityId()+"["+this.foreignProperty+"]");
			}
 		}
 		
 		return fkColumn;
 	}
     
	public Object getReference(Object obj){
		
		Field field= this.getField();
		Object ref= ReflectManager.get(field, obj);
		
		return ref;
	}

    public TableReference clone()
    {
        TableReference newObject = new TableReference();

        super.clone(newObject);
        
        newObject.isQuery = this.isQuery;
        newObject.filter = this.filter;
        newObject.isAuditEdit = this.isAuditEdit;

        return newObject;
    }
    
    @Override
    public String toString()
    {
        return this.fromEntityId + " : " + this.referenceCode;
    }

	public boolean isAuditEdit() {
		return isAuditEdit;
	}

	public void setAuditEdit(boolean isAuditEdit) {
		this.isAuditEdit = isAuditEdit;
	}

	public boolean getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(boolean isQuery) {
		this.isQuery = isQuery;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}
}
