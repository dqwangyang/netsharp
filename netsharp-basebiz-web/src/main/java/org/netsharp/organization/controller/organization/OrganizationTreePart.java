package org.netsharp.organization.controller.organization;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Category;
import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.entity.Organization;
import org.netsharp.panda.commerce.TreePart;
import org.netsharp.panda.controls.tree.TreeNode;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

public class OrganizationTreePart extends TreePart {

	IOrganizationService orgService = ServiceFactory.create(IOrganizationService.class);

	@Override
	protected void importJs(IHtmlWriter writer) {
		super.importJs(writer);

		writer.write(UrlHelper.getVersionScript("/panda-bizbase/organization/org-organization-tree-part.js"));
	}

	/* 查询 */
	public List<TreeNode> query() throws UnsupportedEncodingException {
		
		String entityId = this.context.getEntityId();
		Mtable meta = MtableManager.getMtable(entityId);

		Oql oql = new Oql();
		{
			oql.setEntityId(meta.getEntityId());
			oql.setSelects("*");
			oql.setOrderby(meta.getOrderby());
		}

		String filter = getRequest("filter");
		if (!StringManager.isNullOrEmpty(filter)) {
			filter = URLDecoder.decode(filter, "UTF-8");
			filter = filter.replace('|', '=');
		}

		ArrayList<String> filters = new ArrayList<String>();
		if (!StringManager.isNullOrEmpty(filter)) {
			filters.add(filter);
		}

		String extraFilter = this.getExtraFilter();
		if (!StringManager.isNullOrEmpty(extraFilter)) {
			filters.add(extraFilter);
		}
		
		Category category = meta.getCategory();
		if(category != null){
			Column column = meta.getProperty(category.getPropertyName());

			String id = this.getRequest("id");
			
			if (StringManager.isNullOrEmpty(id)) {
				// 顶级查询
				String keyFilter = meta.getId().getEmptyFilter(meta.getCode() + "." + column.getColumnName());
				filters.add(keyFilter);
			} else {
				// 非顶级查询
				filters.add(meta.getCode() + "." + column.getColumnName() + "= ?");

				Object parentId = column.getConvertor().fromString(id);
				oql.getParameters().add("@" + column.getPropertyName(), parentId, column.getDataType().getJdbcType());
			}
		}

		filters.add("(disabled=0 or disabled is null)");
		oql.setFilter(StringManager.join(" and ", filters));

		Class<?> serviceType = ReflectManager.getType(this.context.getService());
		IPersistableService<?> service = (IPersistableService<?>) ServiceFactory.create(serviceType);
		List<?> rows = service.queryList(oql);

		List<TreeNode> nodes = this.serialize(rows);
				
		return nodes;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<TreeNode> serialize(List<?> rows) {

		List<Organization> items = (List<Organization>) rows;

		OrganizationSerializer json = new OrganizationSerializer(items);
		List<TreeNode> nodes = json.parse();

		return nodes;
	}

	public String doCreateWeiXin() {
		
		String ret = "同步完成!";
		//Environment env = Application.getContext().getEnvironment();
		
//		if ( env != Environment.Product) {
//			ret = "只有正式环境才可同步!";
//		} else {
//			try {
//				DepartmentHelper.update();
//				EmployeeHelper.run();
//			} catch (Exception e) {
//				ret = e.getMessage();
//			}
//		}
		
		try {
//			DepartmentHelper.update();
			//EmployeeHelper.run();
		} catch (Exception e) {
			ret = e.getMessage();
		}
		return ret;
	}

	public boolean changeParent(Long nodeId, Long newParentId) {
		orgService.changeParent(nodeId, newParentId);
		pathCode();
		return true;
	}
}
