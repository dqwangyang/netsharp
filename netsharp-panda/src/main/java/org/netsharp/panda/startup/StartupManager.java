package org.netsharp.panda.startup;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.cache.service.CacheStartup;
import org.netsharp.panda.session.startup.PandaSessionStartup;
import org.netsharp.startup.IStartup;
import org.netsharp.util.StopWatch;

public class StartupManager {

	private Log logger = LogFactory.getLog(StartupManager.class.getSimpleName());
	private List<IStartup> startups = new ArrayList<IStartup>();
	
	public StartupManager() {
		
//		startups.add(new LogStartup());
//		startups.add(new StartupWeixin());
		startups.add(new CacheStartup());
//		startups.add(new JobStartup());
		startups.add(new PandaSessionStartup());
//		startups.add(new StartupRest());
		
	}

	public void startup() {
		
		StopWatch sw = new StopWatch();

		for (IStartup startup : startups) {

			if (startup.startCondition()) {
				
				logger.debug("startup:" + startup.getName());
				
				sw.start();
				
				startup.startup();
				
				sw.stop();
				
				logger.debug("startup:" + startup.getName() +"  耗时："+sw.getEclipsed());
			}
		}
	}

	public void shutdown() {

		for (IStartup startup : startups) {
			startup.shutdown();

			logger.warn("shutdown:" + startup.getName());
		}
	}
	
	public void add(IStartup startup) {
		this.startups.add(startup);
	}
}