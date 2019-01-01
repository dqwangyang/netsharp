package org.netsharp.organization.controller.organization;

import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;
import org.netsharp.core.Pagination;
import org.netsharp.organization.base.IOrganizationEmployeeService;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.util.StringManager;

//organizationId='2'
public class OrganizationEmployeeListPart extends ListPart {

	public Pagination doQuery(Oql oql) {

		IOrganizationEmployeeService service = ServiceFactory.create(IOrganizationEmployeeService.class);
		String filter = oql.getFilter();
		String orgFilter = "";
		String replaceFilter = "";
		if (!StringManager.isNullOrEmpty(filter) && filter.toLowerCase().contains("organizationid")) {

			// 查找出查询条件organizationId='2'，然后替换
			String[] filters = filter.toLowerCase().split("and");
			for (String f : filters) {
				if (f.toLowerCase().contains("organizationid")) {
					String[] strs = f.trim().split("=");
					if (strs.length == 2) {
						replaceFilter = f;
						String ids = getOrganizationIds(strs[1]);
						orgFilter = "organizationId in (" + ids + ") ";
					}
				}
			}
		}
		if (!StringManager.isNullOrEmpty(orgFilter)) {
			filter = filter.toLowerCase().replace(replaceFilter, orgFilter);
			oql.setFilter(filter);
		}

		Pagination pag = service.queryPaging(oql);
		return pag;
	}

	/**
	 * 获取子部门的部门ID
	 * 
	 * @param parentId
	 * @return
	 */
	private String getOrganizationIds(String id) {
		IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
		if (StringManager.isNullOrEmpty(id)) {
			throw new BusinessException("部门ID为空！");
		}
		Integer parentId = Integer.parseInt(id.replace("'", "").replace("'", ""));
		List<Integer> ids = organizationService.getChildDepartment(parentId);
		ids.add(parentId);
		return StringManager.join(",", ids);
	}
}
