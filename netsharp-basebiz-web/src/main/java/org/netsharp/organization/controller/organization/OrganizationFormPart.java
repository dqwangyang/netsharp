package org.netsharp.organization.controller.organization;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.panda.commerce.FormPart;

public class OrganizationFormPart extends FormPart{

	
	/**
	 * 停用
	 * @return
	 */
	public Boolean disabled(Long id){
		
		IOrganizationService service = ServiceFactory.create(IOrganizationService.class);
		return service.disabled(id);
	}
}
