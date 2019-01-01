package org.netsharp.organization.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**   
 * @ClassName:Gender   
 * @Description:性别
 * @author:hanwei
 * @date:2017年8月16日 上午11:00:31
 */  
public enum Gender implements IEnum{
	
	MALE(1,"男性"), FEMALE(2,"女性"), UNKNOWN(0,"保密");
	private int value;
	private String text;

	Gender(int value,String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static Gender getItem(int value){
    	
        for(Gender item : values()){

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
