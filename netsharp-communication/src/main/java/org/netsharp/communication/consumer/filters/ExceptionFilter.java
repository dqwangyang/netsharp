package org.netsharp.communication.consumer.filters;

import org.netsharp.communication.consumer.CiContext;
import org.netsharp.communication.consumer.IClientFilter;
import org.netsharp.communication.core.CommunicationException;
import org.netsharp.communication.core.InvokeResponse;
import org.netsharp.core.NetsharpException;

public class ExceptionFilter implements IClientFilter {

	public void invoking(CiContext ctx) {
		
	}

	public void invoked(CiContext ctx) {
		
		InvokeResponse response = ctx.getResponse();
		if(response.getSucceed()) {
			return;
		}
		
		Throwable exception = response.getException();
		
		NetsharpException ne = Exceptions.causeby(exception, NetsharpException.class);
		if(ne!=null) {
			throw ne;
		}
		
		throw new CommunicationException(exception.getMessage(),exception);
	}
	
	public static class Exceptions{
		
		@SuppressWarnings("unchecked")
		public static <T> T causeby(Throwable exception,Class<T> type) {

			while(exception != null) {
				boolean is = type.isInstance(exception);
				if(is) {
					return (T)exception;
				}
				exception = exception.getCause();
			}
			
			return null;
		}
	}

}
