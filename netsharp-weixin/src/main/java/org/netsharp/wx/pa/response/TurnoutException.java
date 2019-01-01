package org.netsharp.wx.pa.response;

import org.netsharp.core.NetsharpException;
import org.netsharp.wx.pa.entity.PublicAccount;

/** 
 执行跳转异常
*/
public class TurnoutException extends NetsharpException
{
	private static final long serialVersionUID = 1L;
	
	private PublicAccount publicAccount;
	
	public TurnoutException(String msg)
	{
		super(msg);
	}
	
	public final PublicAccount getPublicAccount()
	{
		return publicAccount;
	}
	
	public final void setPublicAccount(PublicAccount value)
	{
		publicAccount = value;
	}
}