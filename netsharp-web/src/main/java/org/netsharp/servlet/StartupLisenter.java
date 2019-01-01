package org.netsharp.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.netsharp.panda.startup.StartupManager;

@WebListener
public class StartupLisenter implements ServletContextListener {
	
	private StartupManager startup = new StartupManager();

	@Override
	public void contextInitialized(ServletContextEvent evnt) {
		
		startup.startup();
	}

	@Override
	public void contextDestroyed(ServletContextEvent evnt) {

		startup.shutdown();
	}
}
