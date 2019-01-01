package org.netsharp.panda.plugin.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum ToolbarType implements IEnum{
	
	BASE(0,"基础"), BIZ(1,"业务"), PLATFORM(2,"平台工具");
	private int value;
	private String text;

	ToolbarType(int value,String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static ToolbarType getItem(int value){
    	
        for(ToolbarType item : values()){

            if(item.getValue() == value){  
                return item;  
            }  
        }  
        return null;  
    } 
	public String getText() {
		return this.text;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}
}
