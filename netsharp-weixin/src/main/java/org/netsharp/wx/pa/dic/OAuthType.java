package org.netsharp.wx.pa.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

//微信菜单oauth模式，服务号才有作用
public enum OAuthType  implements IEnum{
	
	none(0,"不OAuth认证"), 
	//用来获取进入页面的用户的openid的，并且是静默授权并自动跳转到回调页的。用户感知的就是直接进入了回调页（往往是业务页面）
	snsapi_base(1,"snsapi_base"),
	//是用来获取用户的基本信息的。但这种授权需要用户手动同意，并且由于用户同意过，所以无须关注，就可在授权后获取该用户的基本信息。
	//对于已关注公众号的用户，如果用户从公众号的会话或者自定义菜单进入本公众号的网页授权页，即使是scope为snsapi_userinfo，也是静默授权，用户无感知。 
	snsapi_userinfo(2,"snsapi_userinfo");

	private int value;
	private String text;

	OAuthType(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static OAuthType getItem(int value){
    	
        for(OAuthType item : values()){

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
