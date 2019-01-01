package org.netsharp.panda.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OpenMode implements IEnum{
	
	REDIRECT(0,"跳转"), WINDOW(1,"弹出窗口"), OPEN(2,"浏览器页签"), TABS(3,"页签打开");
	private int value;
	private String text;

	OpenMode(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static OpenMode getItem(int value){
    	
        for(OpenMode item : values()){

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
