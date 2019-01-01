package org.netsharp.panda.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum QueryProjectType implements IEnum{
	
	SIMPLE(0,"简单"), ADVANCED(1,"高级");
	private int value;
	private String text;
	QueryProjectType(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static QueryProjectType getItem(int value){
    	
        for(QueryProjectType item : values()){

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
