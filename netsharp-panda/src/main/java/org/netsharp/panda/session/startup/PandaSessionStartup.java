package org.netsharp.panda.session.startup;

import org.netsharp.session.ISessionManager;
import org.netsharp.session.SessionManager;
import org.netsharp.startup.IStartup;

public class PandaSessionStartup implements IStartup {

	public boolean startCondition() {
		return true;
	}

	public void startup() {
		
		ISessionManager sm = new PandaSessionManager();
		
		SessionManager.initialize(sm);
	}

	public void shutdown() {
	}

	public String getName() {
		return "panda session";
	}

}
