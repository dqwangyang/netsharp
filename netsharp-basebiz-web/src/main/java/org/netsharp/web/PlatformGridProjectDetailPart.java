package org.netsharp.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.base.IPDatagridService;
import org.netsharp.panda.entity.PDatagrid;

public class PlatformGridProjectDetailPart  extends PlatformToolDetailPart{

	@Override
	protected String getMetaEntity(Integer projectId) {
		
		String metaEntity = "";
		IPDatagridService gridService = ServiceFactory.create(IPDatagridService.class);
		PDatagrid pDatagrid = gridService.byId(projectId);
		if(pDatagrid != null){
			
			metaEntity = pDatagrid.getResourceNode().getEntityId();
		}
		return metaEntity;
	}
}
