package org.netsharp.panda.servlet.filters;

import org.netsharp.panda.servlet.invoke.PandaContext;

public interface IPandaFilter {
	
	void invoking(PandaContext ctx);
    void invoked(PandaContext ctx);
}
