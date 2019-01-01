package org.netsharp.wx.pa.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/*粉丝订阅状态*/
public enum FansStatus  implements IEnum{

	subscribe(1,"已关注"), unsubscribe(0,"已取消关注");

	private int value;
	private String text;

	FansStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static FansStatus getItem(int value){
    	
        for(FansStatus item : values()){

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
