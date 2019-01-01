package org.netsharp.panda.controls.input;

import java.util.ArrayList;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.base.IPDatagridService;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PReference;
import org.netsharp.panda.session.PermissionHelper;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

public class ReferenceBox extends Input {
	
	@HtmlAttr(html = "foreignkey")
	public String foreignkey;

	@HtmlAttr(html = "foreignName")
	public String foreignName;

	@HtmlAttr(html = "foreignId")
	public String foreignId;

	@DataOption(html = "idField")
	public String idField;

	@DataOption(html = "textField")
	public String textField;

	@DataOption(html = "width")
	public Integer width;

	@DataOption(html = "fitColumns")
	public boolean fitColumns;

	@DataOption(html = "panelWidth")
	public Integer panelWidth;

	@DataOption(html = "panelHeight")
	public Integer panelHeight;

	@DataOption(html = "url")
	public String url;

	@DataOption(html = "pagination")
	public boolean pagination;

	@DataOption(html = "pageSize")
	public Integer pageSize;

	@DataOption(html = "mode")
	public String mode;

	@DataOption(html = "windowTitle")
	public String windowTitle;

	@DataOption(html = "windowWidth")
	public Integer windowWidth;

	@DataOption(html = "windowHeight")
	public Integer windowHeight;

	@DataOption(html = "multiple")
	public boolean multiple;

	@DataOption(html = "onHidePanel", isEvent = true)
	public String onHidePanel;

	@DataOption(html = "onShowPanel", isEvent = true)
	public String onShowPanel;
	
	@DataOption(html = "onChange", isEvent = true)
	public String onChange;
	
    @DataOption(html="validateOnCreate")
    public boolean validateOnCreate = false;
    
    @DataOption(html="validateOnBlur")
    public boolean validateOnBlur = true;
	
	public boolean isPopup = false;

	public String filter;

	public Object reference;
	
	public String code;

	@Override
	public void initialize() {

		super.initialize();
		if (this.isPopup) {

			this.setClassName("easyui-referencebox");
		} else {

			this.setClassName ("easyui-combogrid");
			this.fitColumns = true;
			this.pagination = true;
			this.mode = "remote";
			this.DataBind();
		}
	}

	public void DataBind() {

		PReference rd = (PReference) this.reference;
		String[] paths = this.getId().split("_");
		String property = paths[paths.length - 1];
		StringBuilder builder = new StringBuilder();
		int width = 0;
		builder.append("columns: [[").append(StringManager.NewLine);
		if (rd.getDataGrid() == null) {
			if (rd.getDatagridId() == null) {
				throw new PandaException("参照" + rd.getName() + "必须配置网格");
			} else {
				IPDatagridService datagridService = ServiceFactory.create(IPDatagridService.class);

				PDatagrid datagrid = datagridService.byId(rd.getDatagridId());
				rd.setDataGrid(datagrid);
			}
		}

		ArrayList<String> ss = new ArrayList<String>();
		for (PDatagridColumn dc : rd.getDataGrid().getColumns()) {
			if (dc.isSystem()) {
				continue;
			}

			String field = dc.getPropertyName().replaceAll("\\.", "_");
			String dataOption = "{field:'" + field + "',title:'" + dc.getHeader() + "',width:" + dc.getWidth();

			if (dc.getOrderbyMode() != null) {
				dataOption += ",sortable:true";
			}

			String formatter = dc.getFormatter();
			if (!StringManager.isNullOrEmpty(formatter)) {
				dataOption += ",formatter:function(val,row,index){" + formatter + "}";
			}

			dataOption += "}";
			ss.add(dataOption);

			width += dc.getWidth();
		}

		builder.append(StringManager.join("," + StringManager.NewLine, ss)).append(StringManager.NewLine);
		builder.append("]]").append(StringManager.NewLine);
		this.getDataOptions().add(builder.toString());

		this.textField = property;
		this.url = "/panda/rest/reference?id=" + rd.getId() + "&filter=" + (this.filter == null?"":this.filter);
		// this.queryUrl = "/panda/rest/reference?id=" + rd.getId() + "&filter="
		// + this.Filter;
		// this.OnShowPanel="function(){PandaHelper.ResetCombogridUrl(this);}";

		this.pageSize = rd.getDataGrid().getPageSize();
		if (rd.getPanelWidth() != null) {

			this.panelWidth = rd.getPanelWidth();
		} else {

			this.panelWidth = width >= 465 ? width : 465;
		}

		if (rd.getPanelHeight() != null) {

			this.panelHeight = rd.getPanelHeight();
		} else {

			this.panelHeight = 285;
		}

		if (rd.isCanNew()) {

			IOperationTypeService typeService = ServiceFactory.create(IOperationTypeService.class);
			OperationType ot = typeService.byCode(OperationTypes.add);
			ResourceNode resourceNode = rd.getResourceNode();
			if (resourceNode == null) {

				IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);
				resourceNode = resourceNodeService.byId(rd.getResourceNodeId());
			}
			boolean isAddPermission = PermissionHelper.isPermission(resourceNode, ot);
			if (isAddPermission) {

				// 还需要控制权限
				builder = new StringBuilder();
				builder.append("toolbar: [{").append(StringManager.NewLine);
				builder.append("text:'新增',");
				builder.append("iconCls:'fa fa-plus',");
				builder.append("handler:function(){");
				//builder.append("$(this).combogrid('hidePanel');");
				builder.append("IMessageBox.open('新增', '/panda" + rd.getPopupUrl() + "?openType=window', " + rd.getWidth() + ", " + rd.getHeight() + ", function(){");
				builder.append("});");
				builder.append("}");
				builder.append("}]");
				this.getDataOptions().add(builder.toString());
			}
		}
	}

	public class RefDataOption {

		public RefDataOption() {
			columns = new ArrayList<DoColumn>();
		}

		public int panelWidth;
		public String idField;
		public String textField;
		public String url;
		public boolean fitColumns;

		public ArrayList<DoColumn> columns;
	}

	public class DoColumn {
		public String field;
		public String title;
		public int width;
		public String align;
	}
}
