package org.netsharp.organization.service.action.organization;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

/*组织机构保存后置条件：同步微信企业号组织机构信息*/
public class ActionOrganizationSaveWeixin implements IAction {

	@Override
	public void execute(ActionContext ctx) {
//
//		CatEntity entity = (CatEntity) ctx.getItem();
//		EntityState state = ctx.getState();
//
//		// 同步组织机构到微信企业号
//		if (state == EntityState.Deleted) {
//			return;
//		}
//
//		// 如果是最高级，则不处理
//		if (entity.getId() == 1) {
//			return;
//		}
//		Log logger = LogFactory.getLog(OrganizationService.class);
//		logger.info("同步组织机构到微信企业号!" + entity.getName());
//		if (!Environment.Product.equals(Application.getContext().getEnvironment())) {
//			return;
//		}
//
//		try {
//			IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
//			Organization org = organizationService.byId(entity.getId());
//			Department dep = new Department();
//			{
//				Integer id = org.getQyWeiXinId() * 10;
//				dep.setId(Integer.parseInt(id.toString()));
//				dep.setName(entity.getName());
//				Integer parentId = organizationService.byId(entity.getParentId()).getQyWeiXinId();
//				if (parentId != 1) {// 最高级的id为1，其它为*10
//					parentId = parentId * 10;
//				}
//				dep.setParentid(Integer.parseInt(parentId.toString()));
//			}
//
//			DepartmentCreateRequest departmentCreateRequest = new DepartmentCreateRequest();
//			{
//				IWxeaAppService service = ServiceFactory.create(IWxeaAppService.class);
//				WxeaApp wxpa = service.byCode("WeChat");
//				AccessToken token = AccessTokenManage.get(wxpa.getCorpid(), wxpa.getCorpsecret());
//				departmentCreateRequest.setToken(token);
//				departmentCreateRequest.setDepartment(dep);
//			}
//			departmentCreateRequest.getResponse();
//
//			logger.info("同步组织机构到微信企业号成功!" + entity.getName());
//
//		} catch (Exception e) {
//
//			logger.info("同步组织机构到微信企业号失败!" + e.getMessage());
//		}
	}
}
