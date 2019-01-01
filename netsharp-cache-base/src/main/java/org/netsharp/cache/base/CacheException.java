package org.netsharp.cache.base;

import org.netsharp.core.NetsharpException;

public class CacheException extends NetsharpException {
	
	private static final long serialVersionUID = 1L;

	public CacheException(){
		super("缓存异常");
	}
	
	public CacheException(String message){
		super(message);
	}
	
	public CacheException(Throwable throwable){
		super(throwable);
	}
	
	public CacheException(String message,Throwable throwable){
		super(message,throwable);
	}
}
