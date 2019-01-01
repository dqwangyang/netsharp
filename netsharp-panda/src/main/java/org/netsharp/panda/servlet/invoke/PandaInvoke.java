package org.netsharp.panda.servlet.invoke;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.panda.servlet.filters.IPandaFilter;

public abstract class PandaInvoke {

	protected static final Log logger = LogFactory.getLog(PandaInvoke.class.getName());
	protected List<IPandaFilter> filters = new ArrayList<IPandaFilter>();

	public void processRequest() {
		
		PandaContext pandaContext = this.createContext();
		
		for (IPandaFilter interceptor : this.filters) {
			interceptor.invoking(pandaContext);
		}
		
		this.doProcessRequest(pandaContext);

		for (int i = this.filters.size() - 1; i >= 0; i--) {
			IPandaFilter interceptor = this.filters.get(i);
			interceptor.invoked(pandaContext);
		}
	}

	protected abstract void doProcessRequest(PandaContext pandaContext);
	
	protected PandaContext createContext() {
		
		PandaContext pandaContext = new PandaContext();
		PandaContext.setCurrent(pandaContext);
		
		return pandaContext;
	}

}
