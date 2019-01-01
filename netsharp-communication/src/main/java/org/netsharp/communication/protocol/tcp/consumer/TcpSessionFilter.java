package org.netsharp.communication.protocol.tcp.consumer;

import org.netsharp.communication.consumer.CiContext;
import org.netsharp.communication.consumer.IClientFilter;
import org.netsharp.session.SessionManager;

public class TcpSessionFilter implements IClientFilter {

	@Override
	public void invoking(CiContext ctx) {

		ctx.getRequest().setSession(SessionManager.getSession());
	}

	@Override
	public void invoked(CiContext ctx) {

	}

}
