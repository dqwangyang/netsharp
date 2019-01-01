package org.netsharp.core;

import java.lang.reflect.Field;
import java.util.List;

import org.netsharp.util.ReflectManager;

public class TableSubs extends TableRelation {
	
	private String subCode;
    private String subName;

    @Override
	public Column getPkColumn() {
		
		if(this.fkColumn==null){
			this.fkColumn=this.fromTable.getKeyColumn();
		}

		return this.fkColumn;
	}

    @Override
	public Column getFkColumn() {
		
		if(fkColumn==null){
			fkColumn=this.getToTable().getPropertyOrColumn(this.foreignProperty);
		}
		
		return fkColumn;
	}

    public TableSubs clone()
    {
        TableSubs newObject = new TableSubs();

        super.clone(newObject);

        newObject.subCode = this.subCode;
        newObject.subName = this.subName;
        

        return newObject;
    }

    @Override
    public String toString()
    {
        return this.fromEntityId + " : " + this.subCode;
    }

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}
	
	public Iterable<?> getSubs(Object obj){
		
		Field field= this.getField();
		@SuppressWarnings("unchecked")
		List<Object> subs= (List<Object>) ReflectManager.get(field, obj);
		
		return subs;
	}
}
