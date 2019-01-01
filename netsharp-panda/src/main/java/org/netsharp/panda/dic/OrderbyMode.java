package org.netsharp.panda.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OrderbyMode implements IEnum{

	ASC(0,"升序"), DESC(1,"降序");
	private int value;
	private String text;

	OrderbyMode(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static OrderbyMode getItem(int value){
    	
        for(OrderbyMode item : values()){

            if(item.getValue() == value){  
                return item;  
            }  
        }  
        return null;  
    } 
	public Integer getValue() {
		return value;
	}
	public String getText() {
		return this.text;
	}
}
