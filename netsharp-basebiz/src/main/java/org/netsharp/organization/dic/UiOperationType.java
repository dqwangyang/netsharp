package org.netsharp.organization.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @ClassName:UiOperationType
 * @Description:授权操作类型
 * @author:hanwei
 * @date:2017年8月16日 上午10:39:39
 */
public enum UiOperationType implements IEnum{
	
	ADD(1, "新增"), REMOVE(0, "删除");

	UiOperationType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	private int value;
	private String text;
	@Override
	public Integer getValue() {
		return this.value;
	}
    @JsonCreator  
    public static UiOperationType getItem(int value){
    	
        for(UiOperationType item : values()){

            if(item.getValue() == value){  
                return item;  
            }  
        }  
        return null;  
    } 
	@Override
	public String getText() {
		return this.text;
	}
}
