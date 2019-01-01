package org.netsharp.wx.ea.organization;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.Oql;
import org.netsharp.core.Row;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.entity.Organization;
import org.netsharp.wx.ea.base.IWxeaAppService;
import org.netsharp.wx.ea.entity.WxeaApp;
import org.netsharp.wx.sdk.ep.accesstoken.AccessToken;
import org.netsharp.wx.sdk.ep.accesstoken.AccessTokenManage;
import org.netsharp.wx.sdk.ep.department.Department;
import org.netsharp.wx.sdk.ep.department.DepartmentCreateRequest;
import org.netsharp.wx.sdk.ep.department.DepartmentDeleteRequest;
import org.netsharp.wx.sdk.ep.department.DepartmentListRequest;
import org.netsharp.wx.sdk.ep.department.DepartmentListResponse;
import org.netsharp.wx.sdk.ep.department.DepartmentUpdateRequest;

/**
 * 同步组织结构到微信企业号
 */
public class DepartmentHelper {

	private static IOrganizationService service = ServiceFactory.create(IOrganizationService.class);
	private static WxeaApp wxpa = null;
	private static Log logger = LogFactory.getLog(DepartmentHelper.class);

	/**
	 * 同步指定部门下所属的部门
	 *
	 * @param parentId
	 */
	public static void update(Integer parentId) {

		initWxpaConfiguration();
		String filter = " disabled=0 and parentId=" + parentId;

		Oql oql = new Oql();
		{
			oql.setFilter(filter);
			oql.setSelects("*");
			oql.setType(Organization.class);
		}
		List<Organization> list = service.queryList(oql);

		for (Organization org : list) {
			update(org);
		}
	}

	/**
	 * 同步所有部门
	 */
	public static void update() {
		update(1);
	}

	public static void runDelete() {

		initWxpaConfiguration();
		delete();

	}

	/**
	 * 判断微信企业号是否存在该部门
	 *
	 * @param wxDepId
	 * @return
	 */
	private static boolean hasDepartment(Integer wxDepId) {
		DepartmentListRequest listRequest = new DepartmentListRequest();
		{
			listRequest.setId(Integer.valueOf(wxDepId.toString()));
			AccessToken token = AccessTokenManage.get(wxpa.getCorpid(), wxpa.getCorpsecret());
			listRequest.setToken(token);

		}
		try {
			DepartmentListResponse response = listRequest.getResponse();
			if (response.getDepartment() == null || response.getDepartment().size() == 0) {
				return false;
			}
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return false;
		}

		return true;
	}

	private static void update(Organization organization) {
		Integer parentId = 1;
		Organization parentOrganization = service.byId(organization.getParentId());
		if (parentOrganization.getQyWeiXinId() != 1) {
			parentId = parentOrganization.getQyWeiXinId() * 10;
		} else {
			parentId = parentOrganization.getQyWeiXinId();
		}

		try {
			Integer organizationIdInWeixin = organization.getQyWeiXinId() * 10;
			AccessToken token = AccessTokenManage.get(wxpa.getCorpid(), wxpa.getCorpsecret());

			if (!hasDepartment(organizationIdInWeixin)) {
				// update organization
				Department department = new Department();
				{
					department.setId(Integer.parseInt(organizationIdInWeixin.toString()));
					department.setName(organization.getName());
					department.setParentid(Integer.parseInt(parentId.toString()));
				}
				DepartmentCreateRequest createRequest = new DepartmentCreateRequest();
				{

					createRequest.setDepartment(department);
					createRequest.setToken(token);
				}
				logger.info("add---------------------" + organization.getName() + "-------" + parentId);
				createRequest.getResponse();
			} else {
				// update organization
				DepartmentUpdateRequest updateRequest = new DepartmentUpdateRequest();
				{
					updateRequest.setId(organizationIdInWeixin);
					updateRequest.setName(organization.getName());
					updateRequest.setParentid(parentId);
					updateRequest.setToken(token);
				}
				logger.info("update---------------------" + organization.getName() + " parentId-------" + parentId);
				updateRequest.getResponse();
			}

		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		logger.warn(organization.getName() + "---" + parentId);
		update(organization.getId());
	}

	private static void delete() {
		String sql = "SELECT id FROM sys_permission_organization WHERE parent_id IS NOT NULL ORDER BY id DESC";

		DataTable table = service.executeTable(sql, null);
		for (Row row : table) {
			DepartmentDeleteRequest deleteRequest = new DepartmentDeleteRequest();
			{
				deleteRequest.setId(Integer.valueOf(row.get("id").toString()) * 10);
				AccessToken token = AccessTokenManage.get(wxpa.getCorpid(), wxpa.getCorpsecret());
				deleteRequest.setToken(token);
			}
			try {
				deleteRequest.getResponse();
			} catch (Exception e) {
				logger.warn(e.getMessage());
			}

		}

	}

	private static void initWxpaConfiguration() {
		if (wxpa == null) {
			IWxeaAppService service = ServiceFactory.create(IWxeaAppService.class);
			wxpa = service.byCode("WeChat");
		}
	}
}
