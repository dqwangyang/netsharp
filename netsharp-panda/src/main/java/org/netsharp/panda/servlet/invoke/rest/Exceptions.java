package org.netsharp.panda.servlet.invoke.rest;

public class Exceptions {

	@SuppressWarnings("unchecked")
	public static <T> T causedby(Throwable exception, Class<T> type) {

		while (exception != null) {
			boolean is = type.isInstance(exception);
			if (is) {
				return (T) exception;
			}
			exception = exception.getCause();
		}

		return null;
	}
}