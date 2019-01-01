package org.netsharp.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.base.IPQueryProjectService;
import org.netsharp.panda.entity.PQueryProject;

public class PlatformQueryProjectDetailPart  extends PlatformToolDetailPart{

	@Override
	protected String getMetaEntity(Integer projectId) {
		
		String metaEntity = "";
		IPQueryProjectService queryService = ServiceFactory.create(IPQueryProjectService.class);
		PQueryProject queryProject = queryService.byId(projectId);
		if(queryProject != null){

			metaEntity = queryProject.getResourceNode().getEntityId();
		}
		return metaEntity;
	}

}
