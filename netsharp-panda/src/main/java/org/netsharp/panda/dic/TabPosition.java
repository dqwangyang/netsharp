package org.netsharp.panda.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;


/**
 * @ClassName:TabPosition   
 * @Description:tab控件标题头的位置
 * @author:hanwei
 * @date:2017年8月16日 上午11:45:17
 */  
public enum TabPosition implements IEnum{
	
	bottom(0,"bottom","底部"),
	top(1,"top","顶部"),
	left(2,"left","左侧"),
	right(3,"right","右则");
	private int value;
	private String code;
	private String text;
	TabPosition(int value,String code, String text) {
		this.value = value;
		this.code = code;
		this.text = text;
	}
    @JsonCreator  
    public static TabPosition getItem(int value){
    	
        for(TabPosition item : values()){

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
	public String getCode() {
		return code;
	}
}
