package org.netsharp.wx.sdk.mp.message;

import org.netsharp.wx.sdk.mp.api.accesstoken.AccessToken;

public interface IMessageListener{
	
	/*处理请求，得到回复*/
	ResponseMessage processRequest(RequestMessage request) ;
	
	/*得到公众号的Token*/
	String getToken(String oid);
	
	AccessToken getAccessToken(String oid);
}