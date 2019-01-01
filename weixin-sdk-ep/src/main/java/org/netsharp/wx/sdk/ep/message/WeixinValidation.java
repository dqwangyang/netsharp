package org.netsharp.wx.sdk.ep.message;


public class WeixinValidation{
	
	public boolean Succeed;
	public String Message;
	
	public static WeixinValidation create(boolean succeed,String message){
		
		WeixinValidation t=new WeixinValidation();
		{
			t.Succeed = succeed;
			t.Message = message;	
		}
		
		return t;
	}
}