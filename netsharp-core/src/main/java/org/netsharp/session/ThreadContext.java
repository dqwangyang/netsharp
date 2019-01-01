package org.netsharp.session;

import java.lang.reflect.Method;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ThreadContext {
	
	private static final Log logger = LogFactory.getLog(ThreadContext.class);
	
	protected UUID requestId;

	public UUID getRequestId() {
		return requestId;
	}

	public void setRequestId(UUID requestId) {
		this.requestId = requestId;
	}

	public static ThreadContext get() {
		
		Object obj = invokeStaticMethod("org.netsharp.panda.servlet.invoke.PandaContext", "getCurrent", null);
		if(obj!=null) {
			return (ThreadContext)obj;
		}
		
		obj = invokeStaticMethod("org.netsharp.communication.provider.SiContext", "getCurrent", null);
		if(obj!=null) {
			return (ThreadContext)obj;
		}
		
		return null;
	}
	
	public static Object invokeStaticMethod(String typeName,String methodName,Class<?>[] parameterTypes, Object... args) {
		try {
			
			Class<?> type = Class.forName(typeName);

			Method method = type.getMethod(methodName, parameterTypes);
			if (method != null) {
				Object ret = method.invoke(null, args);
				return ret;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.debug("can't load context object : " + typeName);
		}

		return null;
	}
}
