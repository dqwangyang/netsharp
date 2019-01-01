package org.netsharp.panda.authorization;

import org.netsharp.core.NetsharpException;

/**
 * 用户如果未登录访问系统异常
 * **/
public class LoginException extends NetsharpException {

	private static final long serialVersionUID = 189459294479950660L;

	public LoginException(){
		
		super("登录后才能访问此功能");
	}
	
	public LoginException(String message){
		super(message);
	}
	
	public LoginException(Throwable throwable){
		super(throwable);
	}
	
	public LoginException(String message,Throwable throwable){
		super(message,throwable);
	}
}
