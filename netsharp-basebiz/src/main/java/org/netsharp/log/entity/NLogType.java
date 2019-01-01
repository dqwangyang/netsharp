package org.netsharp.log.entity;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum NLogType  implements IEnum{
	info(1,"信息"), warn(2,"警告"), error(3,"错误");

	NLogType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	private int value;
	private String text;
	
    @JsonCreator  
    public static NLogType getItem(int value){
    	
        for(NLogType item : values()){

            if(item.getValue() == value){  
                return item;  
            }  
        }  
        return null;  
    } 
    
	@Override
	public Integer getValue() {
		return this.value;
	}

	@Override
	public String getText() {
		return this.text;
	}
}
