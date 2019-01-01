package org.netsharp.organization.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @ClassName:PostType
 * @Description:岗位类型
 * @author:hanwei
 * @date:2017年8月16日 上午11:06:37
 */
public enum PostType implements IEnum{

	MAINTIME(1, "主岗"), PARTTIME(2, "兼职"), TEMP(3, "临时");
	private int value;
	private String text;

	PostType(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static PostType getItem(int value){
    	
        for(PostType item : values()){

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
