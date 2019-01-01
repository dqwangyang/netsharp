package org.netsharp.wx.pa.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/*微信认证生成url时的参数state类型*/
public enum WeixinIndentityType  implements IEnum{

	openidOnly(1,"openidOnly"), openidFirst(2,"openidFirst"), userinfo(3,"userinfo");

	private int value;
	private String text;

	WeixinIndentityType(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static WeixinIndentityType getItem(int value){
    	
        for(WeixinIndentityType item : values()){

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
