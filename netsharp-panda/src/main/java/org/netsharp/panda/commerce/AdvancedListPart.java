package org.netsharp.panda.commerce;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.core.Oql;
import org.netsharp.core.Pagination;
import org.netsharp.core.Paging;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.util.JsonManage;
import org.netsharp.util.StringManager;

public class AdvancedListPart extends ListPart {

	protected Map<String, FilterParameter> filterMap = new HashMap<String, FilterParameter>();

	@Override
	public Object query() throws IOException {

		this.pdatagrid = this.context.getDatagrid();

		Paging paging = getPaging();
		String sort = getRequest("sort");
		String order = getRequest("order");

		// 处理查询条件
		getFilterMap();

		String filters = getFilters();

		Oql oql = new Oql();
		{
			PDatagrid dgp = this.context.getDatagrid();
			String entityId = dgp.getEntityId();
			if (StringManager.isNullOrEmpty(entityId)) {

				entityId = this.getContext().getEntityId();
			}
			oql.setSelects(dgp.getSelectedFields());
			oql.setEntityId(entityId);
			oql.setFilter(filters);
			oql.setPaging(paging);
			oql.setOrderby(order);
		}

		if (StringManager.isNullOrEmpty(sort)) {

			String defaultSort = this.pdatagrid.getSortName();
			if (!StringManager.isNullOrEmpty(defaultSort)) {

				oql.setOrderby(defaultSort);
			} else {

				oql.setOrderby(this.context.getDatagrid().getOrderby());
			}

		} else {

			oql.setOrderby(sort + " " + order);
		}

		Pagination pag = this.doQuery(oql);
		oql.setPaging(pag.getPaging());
		Object json = this.serialize(pag.getRows(), oql);
		return json;
	}
	
	public String getFilterByParameter(FilterParameter parameter){
		
		return parameter.getFilter();
	}

	public String getFilters() throws UnsupportedEncodingException {

		ArrayList<String> filters = new ArrayList<String>();
		if (filterMap.size() > 0) {

			for (String key : filterMap.keySet()) {

				FilterParameter fp = filterMap.get(key);
				if (fp != null) {

					String fString = getFilterByParameter(fp);
					filters.add(fString);
				}
			}
		}

		// 的默认查询条件
		String defaultFilter = this.getDefaultFilter();
		if (!StringManager.isNullOrEmpty(defaultFilter)) {

			filters.add(defaultFilter);
		}

		// 扩展条件
		String extraFilter = this.getExtraFilter();
		if (!StringManager.isNullOrEmpty(extraFilter)) {

			filters.add(extraFilter);
		}

		// 表格设置的条件
		String datagridFilter = this.context.getDatagrid().getFilter();
		logger.debug("query datagridFilter:" + datagridFilter);
		if (!StringManager.isNullOrEmpty(filter) && !StringManager.isNullOrEmpty(datagridFilter)) {

			datagridFilter = datagridFilter.replace("1=0", "1=1");
		}

		if (!StringManager.isNullOrEmpty(datagridFilter)) {

			filters.add(datagridFilter);
		}

		filter = StringManager.join(" and ", filters);

		logger.debug("query filter:" + filter);
		return filter;
	}

	private Paging getPaging() {

		Paging paging = null;
		String strPageIndex = getRequest("page");
		String rows = getRequest("rows");
		if (!StringManager.isNullOrEmpty(strPageIndex)) {

			int pageIndex = StringManager.isNullOrEmpty(strPageIndex) ? 1 : Integer.valueOf(strPageIndex);
			if (pageIndex == 0) {

				pageIndex = 1;
			}

			int pageSize = StringManager.isNullOrEmpty(rows) ? this.pdatagrid.getPageSize() : Integer.valueOf(rows);
			paging = new Paging();
			{
				paging.setPageNo(pageIndex);
				paging.setPageSize(pageSize);
			}
		}
		return paging;
	}

	/**   
	 * @Title: getFilterMap   
	 * @Description: TODO(将查询条件存入至hashMap中)   
	 * @param: @throws UnsupportedEncodingException      
	 * @return: void      
	 * @throws   
	 */
	protected void getFilterMap() throws UnsupportedEncodingException {

		if(filterMap.size() == 0){
			
			String qps = getRequest("qps");
			if (!StringManager.isNullOrEmpty(qps)) {

				String qpsJson = URLDecoder.decode(getRequest("qps"), "UTF-8");
				List<FilterParameter> fpList = JsonManage.deSerializeList(FilterParameter.class, qpsJson);
				for (FilterParameter fp : fpList) {

					filterMap.put(fp.getKey(), fp);
				}
			}
		}
	}
}
