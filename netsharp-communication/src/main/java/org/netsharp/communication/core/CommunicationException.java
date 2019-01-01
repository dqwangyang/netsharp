package org.netsharp.communication.core;

import org.netsharp.core.NetsharpException;

public class CommunicationException extends NetsharpException {

	private static final long serialVersionUID = 1L;

	public CommunicationException(){
		super();
	}
	
	public CommunicationException(String message){
		super(message);
	}
	
	public CommunicationException(Throwable throwable){
		super(throwable);
	}
	
	public CommunicationException(String message,Throwable throwable){
		super(message,throwable);
	}
}
