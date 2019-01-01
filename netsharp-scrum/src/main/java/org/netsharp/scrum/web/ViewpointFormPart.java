package org.netsharp.scrum.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormNavigation;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.scrum.base.IViewpointService;
import org.netsharp.scrum.entity.Viewpoint;

public class ViewpointFormPart extends FormPart {
	
	@Override
	public FormNavigation byId(Object id) {

		FormNavigation navigation =  super.byId(id);
		Viewpoint entity = (Viewpoint)navigation.Entity;
		
		IViewpointService viewpointService = ServiceFactory.create(IViewpointService.class);
		navigation.Entity = viewpointService.attachFans(entity);
		
		return navigation;
	}
}
