package org.netsharp.meta.basebiz.organization;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.service.action.organization.ActionOrganizationSavePersist;
import org.netsharp.organization.service.action.organization.ActionOrganizationSavePost;
import org.netsharp.organization.service.action.organization.ActionOrganizationSaveWeixin;
import org.netsharp.plugin.bean.Bean;
import org.netsharp.plugin.bean.BeanPath;
import org.netsharp.plugin.bean.IBeanPathService;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

public class OrganizationActionTest {
	
	private ResourceNode resourceNode = null;
	private IBeanPathService beanPathService = ServiceFactory.create(IBeanPathService.class);
	
	@Test
	public void run(){
		
		IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);
		resourceNode = resourceNodeService.byCode(Organization.class.getSimpleName());
		
		String pathName =  "org/netsharp/basebiz/organization/save";
		
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("组织机构保存Action");
		}
		
		Bean bean = new Bean();
		{
			bean.toNew();
			bean.setName("持久化");
			bean.setType(ActionOrganizationSavePersist.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(100);
			
			beanPath.getCodons().add(bean);
		}
		
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("同步岗位");
			bean.setType(ActionOrganizationSavePost.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(200);
			
			beanPath.getCodons().add(bean);
		}
		
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("同步微信企业号");
			bean.setType(ActionOrganizationSaveWeixin.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(300);
			
			beanPath.getCodons().add(bean);
		}
		
		beanPathService.save(beanPath);
		
	}
}
