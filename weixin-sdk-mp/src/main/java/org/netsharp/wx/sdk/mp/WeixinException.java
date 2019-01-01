package org.netsharp.wx.sdk.mp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.NetsharpException;


/*微信公众号异常*/
public class WeixinException extends NetsharpException
{
	private static final long serialVersionUID = 1L;
	protected static Log logger = LogFactory.getLog(WeixinException.class.getSimpleName());
	
	private String errorCode;
	private String errmsg;
	
	public WeixinException(){
		super();
	}
	
	public WeixinException(String errorCode,String errmsg,String message){
		super(message);
		
		this.errorCode=errorCode;
		logger.error("errcode："+this.errorCode+",errmsg:"+this.errmsg);
		logger.error("错误信息："+message);
	}
	
	public WeixinException(String message){
		super(message);
		
		logger.error(message);
	}
	
	public WeixinException(Throwable throwable){
		super(throwable);
		
		logger.error(throwable);
	}
	
	public WeixinException(String message,Throwable throwable){
		super(message,throwable);
		
		logger.error(message,throwable);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}