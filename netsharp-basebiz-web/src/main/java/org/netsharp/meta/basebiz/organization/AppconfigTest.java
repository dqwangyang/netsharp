package org.netsharp.meta.basebiz.organization;

import org.junit.Test;
import org.netsharp.appconfig.Appconfig;
import org.netsharp.appconfig.IAppconfigService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.NetsharpException;
import org.netsharp.organization.entity.Employee;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

public class AppconfigTest {
	
	IAppconfigService configService = ServiceFactory.create(IAppconfigService.class);
	IResourceNodeService resourceNodeService = ServiceFactory.create( IResourceNodeService.class );
	
	@Test
	public void create(){
		
		String resourceNodeCode = Employee.class.getSimpleName();
		
		this.attatch("email.suffix", "邮箱后缀","@netsharp.com", "system",resourceNodeCode);
		this.attatch("email.url", "邮箱URL","http://conf.p.tt.yikuaixiu.com/email/account", "system",resourceNodeCode);
	}
	
	private void attatch(String code,String name,String value,String groupName,String resourceNodeCode){
		

		if(StringManager.isNullOrEmpty(resourceNodeCode)) {
			throw new NetsharpException("资源节点不能为空");
		}

		ResourceNode node = resourceNodeService.byCode(resourceNodeCode);
		if(node==null) {
			throw new NetsharpException("没有查询到资源节点："+resourceNodeCode);
		}
		
		Appconfig appconfig = configService.byCode(code);
		if(appconfig!=null){
			return;
		}
		
		appconfig = new Appconfig();
		{
			appconfig.toNew();
			appconfig.setCode(code);
			appconfig.setName(name);
			appconfig.setValue(value);
			appconfig.setGroupName(groupName);
			appconfig.setResourceNode(node);
			
		}
		configService.save(appconfig);
	}
}
