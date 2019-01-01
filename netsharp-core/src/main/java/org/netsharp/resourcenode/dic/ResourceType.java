package org.netsharp.resourcenode.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum ResourceType implements IEnum{
	
    ADDIN(1,"插件"), CAT(2,"分类"),VOUCHER(3,"单据"),ENTITY(4,"实体");
    
    private int value;
    private String text;
    
    ResourceType(int value,String text){
    	this.value = value;
    	this.text=text;
    }
    @JsonCreator  
    public static ResourceType getItem(int value){
    	
        for(ResourceType item : values()){

            if(item.getValue() == value){  
                return item;  
            }  
        }  
        return null;  
    } 

	public String getText(){
    	return this.text;
    }


	@Override
	public Integer getValue() {

		return value;
	}
}
