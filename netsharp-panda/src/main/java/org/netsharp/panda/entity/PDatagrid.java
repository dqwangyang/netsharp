package org.netsharp.panda.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.CompositeOne;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.panda.dic.AggregateType;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

@JsonIgnoreProperties(value = { "orderby", "selectedFields" })
@Table(name = "ui_pa_datagrid", header = "列表项目")
public class PDatagrid extends UiDescription {

	private static final long serialVersionUID = -113902698860724892L;

	@Column(header = "图标")
	private String iconCls;

	@Column(name = "page_size", header = "每页显示的条数")
	private Integer pageSize = 10;

	@Column(name = "fit", header = "自适应列宽")
	private boolean fit = false;

	@Column(name = "pagination", header = "分页显示")
	private boolean pagination = true;

	@Column(name = "show_title", header = "显示标题")
	private boolean showTitle = false;

	@Column(name = "single_select", header = "单选")
	private boolean singleSelect = true;

	@Column(name = "show_checkbox", header = "显示checkbox")
	private boolean showCheckbox = true;

	@Column(name = "nowrap", header = "禁止换行")
	private boolean nowrap = true;

	@Column(name = "show_footer", header = "显示底部")
	private boolean showFooter = false;

	@Column(name = "remember", header = "记忆列宽")
	private boolean remember = false;
	
	@Column(name = "rownumbers", header = "显示序列")
	private boolean rownumbers = false;

	@Column(name = "auto_query", header = "自动查询")
	private boolean autoQuery = true;

	@Column(name = "detailed", header = "明细表格")
	private boolean detailed = true;

	@Column(name = "query_project_id", header = "查询方案")
	private Integer queryProjectId;

	@CompositeOne(foreignKey = "queryProjectId", header = "查询项目")
	private PQueryProject queryProject;

	@Column(name = "advanced_query_project_id", header = "高级查询方案")
	private Integer advancedQueryProjectId;

	@CompositeOne(foreignKey = "advancedQueryProjectId", header = "查询项目")
	private PQueryProject advancedQueryProject;

	@Column(size = 1000, header = "默认的过滤条件")
	private String filter;

	@Column(size = 100, name = "sort_name", header = "默认排序（如：createTime DESC）")
	private String sortName;

	@Column(size = 50, name = "group_field", header = "row分组字段")
	private String groupField;

	@Column(size = 100, name = "group_formatter", header = "row分组格式化")
	private String groupFormatter;

	@Column(name = "toolbar", header = "工具栏路径")
	private String toolbar;

	@Column(name = "tree_field", header = "树节点字段")
	public String treeField;

	@Column(name = "lazy", header = "懒加载")
	public Boolean lazy = false;
	
	@Column(name = "detail_url", header = "明细路径")
	public String detailUrl;

	@Subs(subType = PDatagridColumn.class, foreignKey = "datagridId", header = "列表栏目")
	private List<PDatagridColumn> columns = new ArrayList<PDatagridColumn>();

	@Subs(subType = PRowStyler.class, foreignKey = "datagridId")
	private List<PRowStyler> stylers;

	@Exclusive
	private String orderby;

	@Exclusive
	private String selectedFields;

	public List<PDatagridColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<PDatagridColumn> columns) {
		this.columns = columns;
	}

	/* 自定义方法 */
	public String getSelectedFields() {

		List<String> ss = new ArrayList<String>();

		String entityId = this.getEntityId();
		Mtable meta = MtableManager.getMtable(entityId);
		ss.add(meta.getKeyColumn().getPropertyName());

		for (PDatagridColumn c : this.columns) {

			if (!c.isSystem() && !c.isVisible()) {
				continue;
			}
			if (c.isNotPersist()) {
				continue;
			}
			ss.add(c.getPropertyName());
		}
		this.selectedFields = StringManager.join(",", ss);
		return selectedFields;
	}

	public String getOrderby() {

		List<String> ss = new ArrayList<String>();

		for (PDatagridColumn c : this.columns) {

			if (!c.isSystem() && !c.isVisible()) {
				continue;
			}

			OrderbyMode orderbyMode = c.getOrderbyMode();
			if (orderbyMode == null) {
				continue;
			}

			ss.add(c.getPropertyName() + " " + orderbyMode.toString());
		}
		this.orderby = StringManager.join(",", ss);
		return this.orderby;
	}

	/**
	 * 获取合计字段
	 * 
	 * @return
	 */
	public List<PDatagridColumn> getSumFields() {

		List<PDatagridColumn> columns = new ArrayList<PDatagridColumn>();
		for (PDatagridColumn c : this.columns) {

			if (!c.isSystem() && !c.isVisible()) {
				continue;
			}

			AggregateType aggregateType = c.getAggregateType();
			if (aggregateType == null) {
				continue;
			}

			if (aggregateType != AggregateType.SUM) {
				continue;
			}
			columns.add(c);
		}
		return columns;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getQueryProjectId() {
		return this.queryProjectId;
	}

	public void setQueryProjectId(Integer queryProjectId) {
		this.queryProjectId = queryProjectId;
	}

	public PQueryProject getQueryProject() {
		return queryProject;
	}

	public void setQueryProject(PQueryProject queryProject) {
		this.queryProject = queryProject;
		if (this.queryProject == null) {
			this.queryProjectId = null;
		} else {
			this.queryProjectId = this.queryProject.getId();
		}
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public PDatagrid() {
	}

	public PDatagrid(ResourceNode node, String name) {
		this.toNew();
		this.setResourceNode(node);
		this.setName(name);
	}

	public PDatagrid(ResourceNode node, String name, boolean isReadOnly) {
		this.toNew();
		this.setResourceNode(node);
		this.setName(name);
		this.setReadOnly(isReadOnly);
	}

	public boolean isNowrap() {
		return nowrap;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	/**
	 * @param nowrap
	 * the nowrap to set
	 */
	public void setNowrap(boolean nowrap) {
		this.nowrap = nowrap;
	}

	public boolean isShowFooter() {
		return showFooter;
	}

	/**
	 * @param showFooter
	 *            the showFooter to set
	 */
	public void setShowFooter(boolean showFooter) {
		this.showFooter = showFooter;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public void setSelectedFields(String selectedFields) {
		this.selectedFields = selectedFields;
	}

	public String getGroupField() {
		return groupField;
	}

	public void setGroupField(String groupField) {
		this.groupField = groupField;
	}

	public String getGroupFormatter() {
		return groupFormatter;
	}

	public void setGroupFormatter(String groupFormatter) {
		this.groupFormatter = groupFormatter;
	}

	public boolean isFit() {
		return fit;
	}

	public void setFit(boolean fit) {
		this.fit = fit;
	}

	public boolean isPagination() {
		return pagination;
	}

	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}

	public boolean isShowTitle() {
		return showTitle;
	}

	public void setShowTitle(boolean showTitle) {
		this.showTitle = showTitle;
	}

	public boolean isSingleSelect() {
		return singleSelect;
	}

	public void setSingleSelect(boolean singleSelect) {
		this.singleSelect = singleSelect;
	}

	public boolean isShowCheckbox() {
		return showCheckbox;
	}

	public void setShowCheckbox(boolean showCheckbox) {
		this.showCheckbox = showCheckbox;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public boolean isAutoQuery() {
		return autoQuery;
	}

	public void setAutoQuery(boolean autoQuery) {
		this.autoQuery = autoQuery;
	}

	public Integer getAdvancedQueryProjectId() {
		return advancedQueryProjectId;
	}

	public void setAdvancedQueryProjectId(Integer advancedQueryProjectId) {
		this.advancedQueryProjectId = advancedQueryProjectId;
	}

	public PQueryProject getAdvancedQueryProject() {
		return advancedQueryProject;
	}

	public void setAdvancedQueryProject(PQueryProject advancedQueryProject) {

		this.advancedQueryProject = advancedQueryProject;
		if (this.advancedQueryProject == null) {
			this.advancedQueryProjectId = null;
		} else {
			this.advancedQueryProjectId = this.advancedQueryProject.getId();
		}
	}

	public List<PRowStyler> getStylers() {
		if (this.stylers == null) {
			this.stylers = new ArrayList<PRowStyler>();
		}
		return stylers;
	}

	public void setStylers(List<PRowStyler> stylers) {
		this.stylers = stylers;
	}

	public boolean isDetailed() {
		return detailed;
	}

	public void setDetailed(boolean detailed) {
		this.detailed = detailed;
	}

	public String getToolbar() {
		return toolbar;
	}

	public void setToolbar(String toolbar) {
		this.toolbar = toolbar;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getTreeField() {
		return treeField;
	}

	public void setTreeField(String treeField) {
		this.treeField = treeField;
	}

	public Boolean getLazy() {
		return lazy;
	}

	public void setLazy(Boolean lazy) {
		this.lazy = lazy;
	}

	public boolean isRownumbers() {
		return rownumbers;
	}

	public void setRownumbers(boolean rownumbers) {
		this.rownumbers = rownumbers;
	}
}