package org.netsharp.organization.service.action.organization;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.service.OrganizationService;
import org.netsharp.util.ReflectManager;

/*组织机构持久化
 *需要考虑服务父类支持的通用功能，比如编码机制等*/
public class ActionOrganizationSavePersist implements IAction {

	@Override
	public void execute(ActionContext ctx) {
		
		Class<?> superType = OrganizationService.class.getSuperclass();
		@SuppressWarnings("unchecked")
		IPersistableService<Organization> service = (IPersistableService<Organization>)ReflectManager.newInstance(superType);		
		
		Organization entity = (Organization)ctx.getItem();
		
		//校验是否存在下级
		this.verify(entity);
		
		entity = service.save(entity);
		
		ctx.setItem(entity);
	}
	
	private void verify(Organization entity){
		
		if (entity.getEntityState() == EntityState.Deleted) {
			
			IOrganizationService service = ServiceFactory.create(IOrganizationService.class);
			Boolean isHasChild = service.hasChild(entity.getId());
			if(isHasChild){
				
				throw new BusinessException("存在下级不能删除，请先删除下级");
			}
		}
	}
}
