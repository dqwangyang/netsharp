package org.netsharp.wx.pa;

import org.netsharp.wx.sdk.mp.message.RequestMessage;
import org.netsharp.wx.sdk.mp.message.ResponseMessage;

public interface IWeixinResponsor
{
	/*如果客户发送了这些关键字，则直接命中*/
	String getKey();
	void setKey(String value);
	
	boolean validate(RequestMessage request);
	ResponseMessage response(RequestMessage request);
	
}