package org.netsharp.meta.basebiz.organization;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.service.action.login.ActionEmployeeLoginLoger;
import org.netsharp.organization.service.action.login.ActionEmployeeLoginQuery;
import org.netsharp.organization.service.action.save.ActionEmployeeSavePassword;
import org.netsharp.organization.service.action.save.ActionEmployeeSavePersist;
import org.netsharp.organization.service.action.save.ActionEmployeeSavePost;
import org.netsharp.organization.service.action.save.ActionEmployeeSaveValidation;
import org.netsharp.organization.service.action.save.ActionEmployeeSaveWeixin;
import org.netsharp.plugin.bean.Bean;
import org.netsharp.plugin.bean.BeanPath;
import org.netsharp.plugin.bean.IBeanPathService;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

public class EmployeeActionTest {
	
	private ResourceNode resourceNode = null;
	private IBeanPathService beanPathService = ServiceFactory.create(IBeanPathService.class);
	
	@Before
	public void setup(){
		IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);
		this.resourceNode = resourceNodeService.byCode(Employee.class.getSimpleName());
	}
	
	@Test
	public void save(){
		
		String pathName =  "org/netsharp/basebiz/employee/save";
		
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("员工保存Action");
		}
		
		Bean bean = new Bean();
		{
			bean.toNew();
			bean.setName("前置验证");
			bean.setType(ActionEmployeeSaveValidation.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(100);
			
			beanPath.getCodons().add(bean);
		}
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("设置密码(新增)");
			bean.setType(ActionEmployeeSavePassword.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(200);
			
			beanPath.getItems().add(bean);
		}
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("同步岗位");
			bean.setType(ActionEmployeeSavePost.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(200);
			
			beanPath.getCodons().add(bean);
		}
		
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("持久化");
			bean.setType(ActionEmployeeSavePersist.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(300);
			
			beanPath.getCodons().add(bean);
		}
		
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("同步微信企业号");
			bean.setType(ActionEmployeeSaveWeixin.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(400);
			
			beanPath.getCodons().add(bean);
		}
		
		beanPathService.save(beanPath);
		
	}
	
	@Test
	public void login(){
		
		String pathName =  "org/netsharp/basebiz/employee/login";
		
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("员工登录Action");
		}
		
		Bean bean = new Bean();
		{
			bean.toNew();
			bean.setName("员工查询");
			bean.setType(ActionEmployeeLoginQuery.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(100);
			
			beanPath.getCodons().add(bean);
		}
		
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("记录日志");
			bean.setType(ActionEmployeeLoginLoger.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(200);
			
			beanPath.getCodons().add(bean);
		}
		
		beanPathService.save(beanPath);
	}
}
