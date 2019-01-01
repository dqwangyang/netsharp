package org.netsharp.pcc.web;

import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.pcc.base.IProvinceCityCountyService;
import org.netsharp.pcc.entity.ProvinceCityCounty;

public class ProvinceCityCountyController {

	IProvinceCityCountyService service = ServiceFactory.create(IProvinceCityCountyService.class);
	
	public List<ProvinceCityCounty> queryPcc(Integer parentId) {

		return service.queryPcc(parentId);
	}
}
