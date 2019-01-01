package org.netsharp.wx.pa.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/*微信公众号类型*/
public enum PublicAccountType implements IEnum{
	subscribe(0,"订阅号"), service(1,"服务号"), personal(2,"个人号");

	private int value;
	private String text;

	PublicAccountType(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static PublicAccountType getItem(int value){
    	
        for(PublicAccountType item : values()){

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
