package org.netsharp.panda.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/*部件停靠类型*/
public enum DockType implements IEnum{
	
	BOTTOM(0, "south"), 
	DOCUMENTHOST(1, "center"), 
	LEFT(2, "west"), 
	RIGHT(3,"east"), 
	TOP(4, "north");
	private int value;
	private String text;

	DockType(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static DockType getItem(int value){
    	
        for(DockType item : values()){

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
