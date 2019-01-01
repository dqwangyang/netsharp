package org.netsharp.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.property.IProperty;
import org.netsharp.entity.IPersistable;
import org.netsharp.entity.Persistable;


public class Row extends Persistable implements IRow {
	
	private static final long serialVersionUID = 6095031734315949893L;

	@Exclusive
	private HashMap<String,Object> changes;
	
	@Exclusive
	protected ITable<?> table;
	
	protected Row(){
		//innerValues=new HashMap<String,Object>();
	}
	
    public void add(String name,Object value){
    	innerValues.put(name,value);
    }
	
	public Object get(String name){
		return innerValues.get(name);
	}
	
    public void set(String name,Object value){
    	innerValues.put(name,value);
    }
        
	@Override
	public String getString(String name) {
		Object obj=innerValues.get(name);
		if(obj!=null){
			return obj.toString();
		}else{
			return null;
		}
	}
	
    public UUID getGuid(String name)
    {
    	Object obj=innerValues.get(name);
		try{
			return (UUID)obj;
		}catch(NumberFormatException e){
			throw new BusinessException("数据类型转化错误:"+e.getMessage());
		}
    }


	@Override
	public Integer getInteger(String name) {
		Object obj=innerValues.get(name);
		try{
			return (Integer)obj;
		}catch(NumberFormatException e){
			throw new BusinessException("数据类型转化错误:"+e.getMessage());
		}
	}
	
	@Override
	public Short getShort(String name) {
		Object obj=innerValues.get(name);
		try{
			return (Short)obj;
		}catch(NumberFormatException e){
			throw new BusinessException("数据类型转化错误:"+e.getMessage());
		}
	}

	@Override
	public Double getDouble(String name) {
		Object obj=innerValues.get(name);
		try{
			return (Double)obj;
		}catch(NumberFormatException e){
			throw new BusinessException("数据类型转化错误:"+e.getMessage());
		}
	}
	
	@Override
	public Long getLong(String name) {
		Object obj=innerValues.get(name);
		try{
			return (Long)obj;
		}catch(NumberFormatException e){
			throw new BusinessException("数据类型转化错误:"+e.getMessage());
		}
	}
	
	public Date getDate(String name){
		Object obj=innerValues.get(name);
		try{
			return (Date)obj;
		}catch(NumberFormatException e){
			throw new BusinessException("数据类型转化错误:"+e.getMessage());
		}
	}

	@Override
	public Boolean getBoolean(String name) {
		Object obj=innerValues.get(name);
		try{
			return (Boolean)obj;
		}catch(NumberFormatException e){
			throw new BusinessException("数据类型转化错误:"+e.getMessage());
		}
	}
	
	public BigDecimal getDecimal(String name){
		
		Object obj=innerValues.get(name);
		try{
			return (BigDecimal)obj;
		}catch(NumberFormatException e){
			throw new BusinessException("数据类型转化错误:"+e.getMessage());
		}
	}
	public Float getFloat(String name){
		Object obj=innerValues.get(name);
		try{
			return (Float)obj;
		}catch(NumberFormatException e){
			throw new BusinessException("数据类型转化错误:"+e.getMessage());
		}
	}

	public ITable<?> getTable() {
		return table;
	}

	void setTable(ITable<?> table) {
		this.table = table;
	}
	
	public Object getUid(){
		
		Column c= this.table.getKeyColumn();

        Object id=c.getProperty().field(this);
        
        return id;
	}
	
	public void setUid(Object id){
		
		Column c= this.table.getKeyColumn();
		
		c.getProperty().field(this, id);
	}
	
	public void clone(IPersistable row){
		
		ArrayList<Column> columns=this.getTable().getColumns();
		for(Column column : columns){
			IProperty p=column.getProperty();
			Object value= p.field(row);
			p.field(this,value);
		}
	}
	
	protected boolean OnPropertyChanging(String propertyName,Object oldValue,Object value){
		return true;
	}
	
	protected void ProtectedPropertyChanged(String propertyName,Object oldValue,Object value){
		
	}
	
	public HashMap<String,Object> getChanges(){
		if(this.changes==null){
			this.changes=new HashMap<String,Object>();
		}
		return this.changes;
	}
	
	public Set getSet(){
		return this.getTable().getSet();
	}
	
	public Map<String,Object> getValueMap(){
		return this.innerValues;
	}

	public static IRow Empty=new Row();
}
