package org.netsharp.meta.basebiz.data;

import org.junit.Test;
import org.netsharp.base.ICatEntityService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.dic.OrganizationType;
import org.netsharp.organization.entity.Organization;

public class OrganizationDataTest {

	@Test
	public void initialize() {

		IOrganizationService service = ServiceFactory.create(IOrganizationService.class);
		
		Oql oql = new Oql();
		int count = service.queryCount(oql);
		if(count>0) {
			return;
		}

		Organization o = new Organization();
		{
			o.toNew();
			o.setName("xxx集团");
			o.setOrganizationType(OrganizationType.SYSTEM);
		}
		service.save(o);

		Organization post = new Organization();
		{
			post.toNew();
			post.setName("CEO");
			post.setOrganizationType(OrganizationType.POST);
			post.setParentId(o.getId());
		}
		
		service.save(post);
		
		
		ICatEntityService catservice = ServiceFactory.create(ICatEntityService.class);
		catservice.generatePathCode(Organization.class.getName());

	}
}
