package org.netsharp.panda.authorization;

import org.netsharp.core.BusinessException;

/**
 * 用户无某个具体的功能权限，比如不能删除订单
 * **/
public class AuthorizationException extends BusinessException {
	
	private static final long serialVersionUID = 1L;

	public AuthorizationException(){
		
		super("您没有权限访问此功能!");
	}
	
	public AuthorizationException(String message){
		super(message);
	}
	
	public AuthorizationException(Throwable throwable){
		super(throwable);
	}
	
	public AuthorizationException(String message,Throwable throwable){
		super(message,throwable);
	}
}
