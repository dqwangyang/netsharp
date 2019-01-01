package org.netsharp.panda.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum DatagridAlign implements IEnum{
	
	LEFT(0,"左对齐"), RIGHT(1,"右对齐"), CENTER(2,"居中");
	private int value;
	private String text;

	DatagridAlign(int value, String text) {
		this.value = value;
		this.text = text;
	}
	
    @JsonCreator  
    public static DatagridAlign getItem(int value){
    	
        for(DatagridAlign item : values()){

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
