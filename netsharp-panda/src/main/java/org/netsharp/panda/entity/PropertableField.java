package org.netsharp.panda.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.entity.Entity;

//属性字段相关实体基类
public abstract class PropertableField extends Entity{

	private static final long serialVersionUID = 4863423235976052744L;
	
	@Column(name = "property_name",header="绑定属性")
	protected String propertyName;

    protected PropertableField(){
        super();
    }

    public String getPropertyName(){
        return this.propertyName;
    }
    public PropertableField setPropertyName(String propertyName){
        this.propertyName=propertyName;
        return this;
    }
}