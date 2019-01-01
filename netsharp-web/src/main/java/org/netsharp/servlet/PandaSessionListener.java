package org.netsharp.servlet;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.netsharp.panda.session.PandaSessionPersister;

@WebListener
public class PandaSessionListener implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener,
		HttpSessionAttributeListener, HttpSessionActivationListener, HttpSessionBindingListener {

	public PandaSessionListener() {
	}
	
	public void sessionDestroyed(HttpSessionEvent arg0) {
		PandaSessionPersister.remove();
	}

	public void attributeAdded(HttpSessionBindingEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
	}

	public void sessionDidActivate(HttpSessionEvent arg0) {
	}

	public void valueBound(HttpSessionBindingEvent arg0) {
	}

	public void attributeAdded(ServletContextAttributeEvent arg0) {
	}

	public void attributeRemoved(ServletContextAttributeEvent arg0) {
	}

	public void attributeRemoved(HttpSessionBindingEvent arg0) {
	}

	public void valueUnbound(HttpSessionBindingEvent arg0) {
	}

	public void sessionWillPassivate(HttpSessionEvent arg0) {
	}

	public void sessionCreated(HttpSessionEvent arg0) {
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
	}

	public void attributeReplaced(ServletContextAttributeEvent arg0) {
	}

	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
