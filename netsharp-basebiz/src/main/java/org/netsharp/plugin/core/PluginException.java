package org.netsharp.plugin.core;

import org.netsharp.core.NetsharpException;

public class PluginException extends NetsharpException {

	private static final long serialVersionUID = 1L;

	public PluginException(){
		super();
	}
	
	public PluginException(String message){
		super(message);
	}
	
	public PluginException(Throwable throwable){
		super(throwable);
	}
	
	public PluginException(String message,Throwable throwable){
		super(message,throwable);
	}
}
