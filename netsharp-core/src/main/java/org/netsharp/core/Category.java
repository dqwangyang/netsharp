package org.netsharp.core;

import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.core.convertor.TypeConvertorFactory;
import org.netsharp.core.id.IId;
import org.netsharp.core.property.IProperty;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

/*ITable分类实体信息*/
public class Category {
	
	private String propertyName; //分类实体的关联字段，如parentId
	private String pathCode;     // 分类实体的pathCode字段名称
	private String pathName;     // 分类实体的pathName字段名称
	private String code;
	private String name;
	private String leafName;
	private String leafValue;//annotation
	
	private Class<?> idtype;
	//
	private IId id;
	private Mtable mtable;
	private IProperty property;
	private IProperty nameProperty;
	private IProperty leafProperty;
	private Object myLeafValue=null;
	
	public String getNameValue(Object obj){
		
		if(nameProperty==null){
			this.nameProperty = this.mtable.getProperty(this.name).getProperty();
		}
		
		return (String)this.nameProperty.field(obj);
	}
	
	public Object getParentId(Object obj){
		
		Object value= this.getProperty().field(obj);

		return value;
	}
	
	public boolean isValidateCode(){
		
		if( StringManager.isNullOrEmpty(this.code)){
			return false;
		} 
		
		if( StringManager.isNullOrEmpty(this.pathCode)){
			return false;
		} 
		
		return true;
	}
	
	public boolean isValidateName(){
		
		if( StringManager.isNullOrEmpty(this.name)){
			return false;
		} 
		
		if( StringManager.isNullOrEmpty(this.pathName)){
			return false;
		} 
		
		return true;
	}
	
	public boolean isLeaf(Object obj){
		
		if(StringManager.isNullOrEmpty(this.leafName)){
			return false;
		}
		
		if(leafProperty==null){
			this.leafProperty = this.mtable.getProperty(this.leafName).getProperty();
		}
		
		if(this.myLeafValue == null){
			ITypeConvertor t = TypeConvertorFactory.create( this.leafProperty.getType() );
			this.myLeafValue = t.fromString(this.leafValue);
		}
		
		Object value=this.leafProperty.field(obj);
		
		if(this.myLeafValue==null && value ==null){
			return true;
		}
		else if(this.myLeafValue == null){
			return value.equals(this.myLeafValue);
		}
		else{
			return this.myLeafValue.equals(value);
		}
	}
	
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPathCode() {
		return pathCode;
	}
	public void setPathCode(String pathCode) {
		this.pathCode = pathCode;
	}
	public String getPathName() {
		return pathName;
	}
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getIdtype() {
		return idtype;
	}

	public void setIdtype(Class<?> idtype) {
		this.idtype = idtype;
	}

	public IId getId() {
		if(id==null){
			id = (IId)ReflectManager.newInstance(this.idtype);
		}
		return id;
	}

	public Mtable getMtable() {
		return mtable;
	}

	public void setMtable(Mtable mtable) {
		this.mtable = mtable;
	}

	public String getLeafName() {
		return leafName;
	}

	public void setLeafName(String leafName) {
		this.leafName = leafName;
	}

	public String getLeafValue() {
		return leafValue;
	}

	public void setLeafValue(String leafValue) {
		this.leafValue = leafValue;
	}

	public IProperty getProperty() {
		if(this.property==null){
			this.property = this.mtable.getProperty(this.propertyName).getProperty();
		}
		return property;
	}
}
