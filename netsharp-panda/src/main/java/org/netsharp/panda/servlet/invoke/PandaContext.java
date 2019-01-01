package org.netsharp.panda.servlet.invoke;

import java.util.UUID;

import org.netsharp.communication.core.CommunicationException;
import org.netsharp.session.ThreadContext;

public class PandaContext extends ThreadContext {

	private static final ThreadLocal<PandaContext> threadLocal = new ThreadLocal<PandaContext>();

	private Boolean isAuthorization = true; // 需要进行权限验证
	private Exception exception;

	public PandaContext() {
		this.requestId = UUID.randomUUID();
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public Boolean getIsAuthorization() {
		return isAuthorization;
	}

	public void setIsAuthorization(Boolean isAuthorization) {
		this.isAuthorization = isAuthorization;
	}

	public static PandaContext getCurrent() {

		return threadLocal.get();
	}

	public static void setCurrent(PandaContext ctx) {

		if (ctx == null) {
			throw new CommunicationException("SiContext不能设置为空");
		}

		threadLocal.set(ctx);
	}

	public static void clean() {
		threadLocal.remove();
	}
}
