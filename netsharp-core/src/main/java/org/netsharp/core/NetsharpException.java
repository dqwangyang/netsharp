package org.netsharp.core;

public class NetsharpException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NetsharpException(){
		super();
	}
	
	public NetsharpException(String message){
		super(message);
	}
	
	public NetsharpException(Throwable throwable){
		super(throwable);
	}
	
	public NetsharpException(String message,Throwable throwable){
		super(message,throwable);
	}
}
