package org.netsharp.panda.servlet.invoke.rest.easyui.filter;

import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.servlet.filters.IPandaFilter;
import org.netsharp.panda.servlet.invoke.PandaContext;
import org.netsharp.panda.servlet.invoke.rest.RestContext;
import org.netsharp.util.JsonManage;

public class ResponseWriteFilter implements IPandaFilter {

	@Override
	public void invoking(PandaContext pandaContext) {

	}

	@Override
	public void invoked(PandaContext pandaContext) {
		
		RestContext ctx = (RestContext) pandaContext;
		String json = JsonManage.serialize2(ctx.getResponse().data);
		
		HttpContext.getCurrent().getWriter().write(json);
	}

}