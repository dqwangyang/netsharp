package org.netsharp.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.base.IPFormService;
import org.netsharp.panda.entity.PForm;

public class PlatformFormProjectDetailPart extends PlatformToolDetailPart{

	@Override
	protected String getMetaEntity(Integer projectId) {
		
		String metaEntity = "";
		IPFormService formService = ServiceFactory.create(IPFormService.class);
		PForm form = formService.byId(projectId);
		if(form != null){

			metaEntity = form.getResourceNode().getEntityId();
		}
		return metaEntity;
	}

}
