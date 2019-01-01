package org.netsharp.wx.sdk.ep;

import org.netsharp.core.NetsharpException;

/*微信企业号异常*/
public class WeixinEnterpriseAccountException extends NetsharpException {
	private static final long serialVersionUID = 1L;
	private String errorCode;

	public WeixinEnterpriseAccountException(){
		super();
	}
	
	public WeixinEnterpriseAccountException(String message){
		super(message);
	}
	
	public WeixinEnterpriseAccountException(String message,String errorCode){
		super(message);
		this.errorCode=errorCode;
	}
	
	public WeixinEnterpriseAccountException(Throwable throwable){
		super(throwable);
	}
	
	public WeixinEnterpriseAccountException(String message,Throwable throwable){
		super(message,throwable);
	}

	public String getErrorCode() {
		return errorCode;
	}
}
