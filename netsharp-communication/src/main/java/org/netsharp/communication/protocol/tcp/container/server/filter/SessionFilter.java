package org.netsharp.communication.protocol.tcp.container.server.filter;

import org.netsharp.session.ISessionManager;
import org.netsharp.session.SessionManager;

public class SessionFilter implements IStartupFilter {

	public void startup() {
		
		ISessionManager sm = new RpcSessionManager();
		
		SessionManager.initialize(sm);
	}

}
