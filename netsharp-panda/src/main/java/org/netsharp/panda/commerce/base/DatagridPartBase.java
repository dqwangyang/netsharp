package org.netsharp.panda.commerce.base;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.panda.base.IPDatagridColumnService;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.controls.datagrid.CheckBoxColumn;
import org.netsharp.panda.controls.datagrid.DataGrid;
import org.netsharp.panda.controls.datagrid.DataGridColumn;
import org.netsharp.panda.controls.datagrid.DataGridSelectionColumn;
import org.netsharp.panda.controls.toolbar.Toolbar;
import org.netsharp.panda.controls.toolbar.ToolbarItem;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.JscriptType;
import org.netsharp.panda.core.Part;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.plugin.ToolbarService;
import org.netsharp.panda.session.PermissionHelper;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

public class DatagridPartBase extends Part {

	public DataGrid datagrid = null;
	protected PDatagrid pdatagrid;
	protected final boolean isFieldPermission = false;// 如果此变量为true时，严重影响服务器性能--暂时也没有必要判断每个字段的权限

	@Override
	public void initialize() {

		this.addTip();
		this.render();
		this.addJscript();
		super.initialize();
		if (pdatagrid != null) {

			if (pdatagrid.isShowTitle()) {

				datagrid.title = pdatagrid.getName();
				String icon = pdatagrid.getIconCls();
				if (!StringManager.isNullOrEmpty(icon)) {

					datagrid.iconCls = icon;
				}
			}

			// if(this.toolbar !=null && this.toolbar.getControls().size()>0){
			//
			// datagrid.toolbar = "#" + this.toolbar.getId();
			// }
			datagrid.showFooter = pdatagrid.isShowFooter();

		}
	}

	protected void onRendering() {

		this.datagrid = new DataGrid();
		{
			datagrid.onResizeColumn = "function(field, width){" + getJsInstance() + ".onResizeColumn(" + getJsInstance() + ".context.datagridId,field, width);}";
			datagrid.onLoadSuccess = "function(data){" + getJsInstance() + ".onLoadSuccess(data);}";
			datagrid.onHeaderContextMenu = "function(e,field){" + getJsInstance() + ".onHeaderContextMenu(e,field);}";
		}
	}

	protected void render() {

		this.pdatagrid = this.context.getDatagrid();
		if (datagrid == null) {

			onRendering();
			datagrid.setId("datagrid" + this.context.getCode());
			datagrid.getInnerValues().put("controller", getJsInstance());
			datagrid.onDblClickRow = "function(index,row){" + getJsInstance() + ".doubleClickRow(index,row);}";
			datagrid.onSelect = "function(index,row){" + getJsInstance() + ".onSelect(index,row);}";
			this.getControls().add(datagrid);
		}

		createGrid();
	}

	private void createGrid() {

		if (!pdatagrid.isSingleSelect()) {

			datagrid.singleSelect = false;
		}
		
		datagrid.nowrap = pdatagrid.isNowrap();
		
		datagrid.rownumbers = pdatagrid.isRownumbers();

		if (!StringManager.isNullOrEmpty(pdatagrid.getGroupField())) {

			datagrid.groupField = pdatagrid.getGroupField();
		}

		if (!StringManager.isNullOrEmpty(pdatagrid.getGroupFormatter())) {

			datagrid.groupFormatter = pdatagrid.getGroupFormatter();
		}

		if (pdatagrid.isShowCheckbox()) {

			DataGridSelectionColumn selectColumn = new DataGridSelectionColumn();
			{
				selectColumn.frozened = true;
			}

			datagrid.columns.add(selectColumn);
		}

		for (PDatagridColumn dcolumn : this.pdatagrid.getColumns()) {

			if (!dcolumn.isVisible()) {

				continue;
			}

			if (isFieldPermission) {

				String propertyName = dcolumn.getPropertyName();
				boolean isPermission = PermissionHelper.isFieldGetway(pdatagrid.getEntityId(), propertyName);
				if (!isPermission) {

					continue;
				}
			}

			if (!StringManager.isNullOrEmpty(dcolumn.getGroupName()) && !datagrid.grouped) {

				datagrid.grouped = true;
			}

			DataGridColumn column = null;
			if (dcolumn.getControlType() == ControlTypes.CHECK_BOX) {

				column = new CheckBoxColumn();
				{
					column.field = dcolumn.getPropertyName();
					column.width = dcolumn.getWidth();
					column.value = dcolumn.getHeader();
					column.visibleed = dcolumn.isVisible();
					column.sortable = dcolumn.getOrderbyMode() != null;
					column.groupName = dcolumn.getGroupName();
				}
			} else if (dcolumn.getControlType() == ControlTypes.OPERATION_COLUMN) {

				// 需要重构
				String toolbar = this.pdatagrid.getToolbar();
				if (!StringManager.isNullOrEmpty(toolbar)) {

					ResourceNode node = this.context.getWorkspace().getResourceNode();
					Toolbar rowToolbar = ToolbarService.create(node, toolbar, this.getJsInstance());
					if (rowToolbar != null && rowToolbar.getControls().size() > 0) {

						List<String> ss = new ArrayList<String>();
						for (Control control : rowToolbar.controls) {

							ToolbarItem toolBarItem = (ToolbarItem) control;
							if (toolBarItem.getCode().equals("platform")) {

								continue;
							}

							String className = toolBarItem.getClassName() != null ? toolBarItem.getClassName() : "";
							String str = toolBarItem.getCode() + "," + className + "," + toolBarItem.value;
							ss.add(str);
						}
						String toolBarItemConfig = StringManager.join(";", ss);
						String formatter = this.getJsInstance() + ".getOptionToolbarItem(value,row,index,'" + this.getJsInstance() + "','" + toolBarItemConfig + "')";

						int width = rowToolbar.controls.size() * 40;
						column = new DataGridColumn();
						{
							column.field = dcolumn.getPropertyName();
							column.width = width;
							column.value = dcolumn.getHeader();
							column.frozened = dcolumn.isFrozen();
							column.formatter = "function(value,row,index){ return " + formatter + "}";
						}
					}
				}

			} else {

				column = new DataGridColumn();
				{
					column.field = dcolumn.getPropertyName();
					column.width = dcolumn.getWidth();
					column.value = dcolumn.getHeader();
					column.visibleed = dcolumn.isVisible();
					column.sortable = dcolumn.getOrderbyMode() != null;
					column.frozened = dcolumn.isFrozen();
					if(!StringManager.isNullOrEmpty(dcolumn.getGroupName())){

						column.groupName = dcolumn.getGroupName();
					}
					column.aggregateType = dcolumn.getAggregateType() != null ? dcolumn.getAggregateType().name() : null;
				}
			}

			if (column != null) {

				String formatter = dcolumn.getFormatter();
				if (!StringManager.isNullOrEmpty(formatter)) {

					column.formatter = "function(value,row,index){" + formatter + "}";
				}

				if (!StringManager.isNullOrEmpty(dcolumn.getStyler())) {

					column.styler = "function(value,row,index){" + dcolumn.getStyler() + "}";
				}

				if (dcolumn.getAlign() != null && dcolumn.getAlign() != DatagridAlign.LEFT) {

					column.align = dcolumn.getAlign().name();
				}

				String propertyName = dcolumn.getPropertyName();
				column.field = propertyName;
				if (propertyName.indexOf(".") > 0) {

					column.field = propertyName.replaceAll("\\.", "_");
				}
				column.value = dcolumn.getHeader();
				column.visibleed = dcolumn.isVisible();
				column.sortable = dcolumn.getOrderbyMode() != null;
				column.frozened = dcolumn.isFrozen();
				column.aggregateType = dcolumn.getOrderbyMode() != null ? dcolumn.getOrderbyMode().name() : null;
				datagrid.columns.add(column);

			}
		}
	}

	// private void createOptionColum(){
	//
	// }

	public static void render(DataGridColumn column, PDatagridColumn dcolumn) {

		column.width = dcolumn.getWidth();
		column.value = dcolumn.getHeader();
		column.visibleed = dcolumn.isVisible();
		column.sortable = dcolumn.getOrderbyMode() != null;
		column.frozened = dcolumn.isFrozen();
		column.aggregateType = dcolumn.getOrderbyMode() != null ? dcolumn.getOrderbyMode().name() : null;
		if (dcolumn.getAlign() != null) {

			column.align = dcolumn.getAlign().name();
		}

		// String formatter = dcolumn.getFormatter();
		// if (!StringManager.isNullOrEmpty(formatter)) {
		//
		// column.formatter = String.format("function(value,row,index){%s}",
		// formatter);
		// }

		// String styler = dcolumn.getStyler();
		// if (!StringManager.isNullOrEmpty(styler)) {
		//
		// column.styler = String.format("function(value,row,index){%s}",
		// styler);
		// }

		// String propertyName = dcolumn.getPropertyName();
		// column.field = propertyName;
		// if (propertyName.indexOf(".") > 0) {
		//
		// column.field = propertyName.replaceAll("\\.", "_");
		// }
		//
		// if (dcolumn.getControlType() != ControlTypes.REFERENCE_BOX) {
		//
		// String propertyName = dcolumn.getPropertyName();
		// column.field = propertyName;
		// if (!StringManager.isNullOrEmpty(propertyName) &&
		// propertyName.indexOf(".") > 0) {
		//
		// column.field = propertyName.replaceAll("\\.", "_");
		// }
		// }
	}

	protected void addJscript() {

		String entityId = this.context.getEntityId();
		this.addJscript("        ", JscriptType.Header);
		this.addJscript("        //管理引用类型表格中显示的问题", JscriptType.Header);
		this.addJscript("        var ReferenceDictionary = new System.Dictionary();", JscriptType.Header);
		this.addJscript("        //", JscriptType.Header);
		this.addJscript("        var " + getJsInstance() + " = new " + getJsController() + "();", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.instanceName=\"" + getJsInstance() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.name=\"" + this.context.getName() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.code=\"" + this.context.getCode() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.datagridId=\"" + this.pdatagrid.getId() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".workspaceId=\"" + this.context.getWorkspaceId() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.vid=\"" + this.context.getId() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.isTip=" + (!StringManager.isNullOrEmpty(this.context.getMemoto())) + ";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.entityId=\"" + entityId + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.service=\"" + this.getClass().getName() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.id=\"" + datagrid.getId() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".datagrid=$('#" + datagrid.getId() + "');", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".datagrid.controller=" + getJsInstance() + ";", JscriptType.Header);
	}

	@Override
	protected void importJs(IHtmlWriter writer) {
		super.importJs(writer);
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.query.js"));
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.list.js"));
	}

	public void resizeColumn(Long datagridId, String propertyName, int width) {

		IPDatagridColumnService service = ServiceFactory.create(IPDatagridColumnService.class);
		Oql oql = new Oql();
		{
			oql.setEntityId(PDatagridColumn.class.getName());
			oql.setSelects("PDatagridColumn.*");
			oql.setFilter("datagridId=? AND PropertyName=?");

			QueryParameters qps = new QueryParameters();
			qps.add("@datagridId", datagridId, Types.BIGINT);
			qps.add("@PropertyName", propertyName.replaceAll("_", "."), Types.VARCHAR);
			oql.setParameters(qps);
		}
		List<PDatagridColumn> columns = service.queryList(oql);
		if (columns.size() > 0) {

			PDatagridColumn column = columns.get(0);
			column.setWidth(width);
			service.save(column);
		}
	}
}
