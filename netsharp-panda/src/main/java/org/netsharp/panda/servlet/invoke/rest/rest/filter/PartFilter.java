package org.netsharp.panda.servlet.invoke.rest.rest.filter;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.base.IPPartService;
import org.netsharp.panda.core.View;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.servlet.filters.IPandaFilter;
import org.netsharp.panda.servlet.invoke.PandaContext;
import org.netsharp.panda.servlet.invoke.rest.RequestBase;
import org.netsharp.panda.servlet.invoke.rest.RestContext;

public class PartFilter implements IPandaFilter {

	@Override
	public void invoking(PandaContext pandaContext) {
		
		RestContext ctx = (RestContext) pandaContext;
		RequestBase request = ctx.getRequest();
		Object serviceObject = ctx.getServiceObject();
		
		IPPartService service = ServiceFactory.create(IPPartService.class);
		Integer vid = request.getvid();
		if(vid !=null && vid>0) {
			PPart ppart = service.byId(vid);
			request.setPpart(ppart); 
		}
		
		if (serviceObject instanceof View) {
			View view = (View) serviceObject;
			view.setContext(request.getPpart());
		}
		
	}

	@Override
	public void invoked(PandaContext ctx) {
	}

}
