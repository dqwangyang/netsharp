package org.netsharp.core;

public class BusinessException extends NetsharpException {
	
	// 未登录:0001
	// 无访问权限:0002

	private static final long serialVersionUID = 1L;
	
	private String code;

	public BusinessException(){
		super();
	}
	
	public BusinessException(String message){
		super(message);
	}
	
	public BusinessException(String code, String message){
		super(message);
		this.code=code;
	}
	
	public BusinessException(Throwable throwable){
		super(throwable);
	}
	
	public BusinessException(String message,Throwable throwable){
		super(message,throwable);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
