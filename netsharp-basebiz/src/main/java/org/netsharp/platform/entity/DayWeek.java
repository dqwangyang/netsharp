package org.netsharp.platform.entity;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum DayWeek implements IEnum{

	Monday(2,"一"),Tuesday(3,"二"),Wednesday(4,"三"),Thursday(5,"四"),Friday(6,"五"),Saturday(7,"六"),Sunday(1,"日");

	private int value;
	private String text;

	DayWeek(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static DayWeek getItem(int value){
    	
        for(DayWeek item : values()){

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
	public String getText() {
		return this.text;
	}
}
