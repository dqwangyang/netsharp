package org.netsharp.panda.core;

import org.netsharp.core.NetsharpException;

public class PandaException extends NetsharpException {

	private static final long serialVersionUID = 1L;

	public PandaException() {
		super();
	}

	public PandaException(String message) {
		super(message);
	}

	public PandaException(Throwable throwable) {
		super(throwable);
	}

	public PandaException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
