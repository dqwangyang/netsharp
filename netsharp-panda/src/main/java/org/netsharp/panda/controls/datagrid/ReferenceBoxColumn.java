package org.netsharp.panda.controls.datagrid;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.NetsharpException;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.EditorOption;
import org.netsharp.panda.base.IPDatagridService;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PReference;
import org.netsharp.panda.session.PermissionHelper;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

public class ReferenceBoxColumn extends DataGridEditColumn {

	private PReference prefernece;

	@DataOption(html = "foreignId")
	public String foreignId;

	@EditorOption(html = "idField", isOption = true, isEvent = false)
	public String idField;

	@EditorOption(html = "textField", isOption = true, isEvent = false)
	public String textField;

	@EditorOption(html = "pagination", isOption = true, isEvent = false)
	public boolean pagination;

	@EditorOption(html = "pageSize", isOption = true, isEvent = false)
	public int pageSize;

	@EditorOption(html = "mode", isOption = true, isEvent = false)
	public String mode;

	@EditorOption(html = "fitColumns", isOption = true, isEvent = false)
	public boolean fitColumns;

	@EditorOption(html = "panelWidth", isOption = true, isEvent = false)
	public int panelWidth;
	
	@EditorOption(html = "panelHeight", isOption = true, isEvent = false)
	public int panelHeight;

	@EditorOption(html = "url", isOption = true, isEvent = false)
	public String url;
	
	@EditorOption(html = "onHidePanel", isOption = true, isEvent = true)
	public String onHidePanel;

	@EditorOption(html = "refKey", isOption = true, isEvent = false)
	public String refKey;
	
	public String filter = "";

	@Override
	public void initialize() {
		this.columnType = "ReferenceBox";
		this.idField = "id";
		this.fitColumns = true;
		this.pagination = true;
		this.pageSize = 10;
		this.mode = "remote";
		this.type = "combogrid";
		this.onHidePanel="function(){var grid = $(this).combogrid('grid');var row = grid.datagrid('getSelected');if(row==null){$(this).combogrid('clear');}}";
		super.initialize();
	}

	@Override
	public void createEditor() {

		int width = 0;
		StringBuilder builder = new StringBuilder();
		builder.append("columns: [[").append(StringManager.NewLine);
		if (prefernece.getDataGrid() == null) {
			Integer datagridId = this.getPrefernece().getDatagridId();
			if (datagridId == null || datagridId <= 0) {
				throw new NetsharpException("参照" + this.prefernece.getName() + "必须配置网格");
			} else {
				IPDatagridService datagridService = ServiceFactory.create(IPDatagridService.class);
				PDatagrid pdatagrid = datagridService.byId(datagridId);

				this.prefernece.setDataGrid(pdatagrid);
			}
		}

		List<String> ss = new ArrayList<String>();
		for (PDatagridColumn dc : this.prefernece.getDataGrid().getColumns()) {
			
			if (dc.isSystem()) {
				continue;
			}
			
			String propertyName = dc.getPropertyName();
			if (!StringManager.isNullOrEmpty(propertyName) && propertyName.indexOf(".") > 0) {
				propertyName = propertyName.replaceAll("\\.", "_");
			}

			String dataOption = "{field:'" + propertyName + "',title:'" + dc.getHeader() + "',width:" + dc.getWidth();
			if (dc.getOrderbyMode() != null) {
				dataOption += ",sortable:true";
			}

			String formatter = dc.getFormatter();
			if (!StringManager.isNullOrEmpty(formatter)) {
				dataOption += ",formatter:function(val,rec){" + formatter + "}";
			}

			dataOption += "}";
			ss.add(dataOption);

			width += dc.getWidth();
		}

		builder.append(StringManager.join("," + StringManager.NewLine, ss)).append(StringManager.NewLine);
		builder.append("]]").append(StringManager.NewLine);
		this.editorOptions.add(builder.toString());
		
		if(prefernece.isCanNew()){
			
			IOperationTypeService typeService = ServiceFactory.create(IOperationTypeService.class);
			OperationType ot = typeService.byCode(OperationTypes.add);
			ResourceNode resourceNode = prefernece.getResourceNode();
			if(resourceNode == null){
				
				IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);
				resourceNode =  resourceNodeService.byId(prefernece.getResourceNodeId());
			}
			boolean isAddPermission = PermissionHelper.isPermission(resourceNode, ot);
			if(isAddPermission){

				//还需要控制权限
				builder = new StringBuilder();
				builder.append("buttons: [{").append(StringManager.NewLine);
				builder.append("text:'新增',");
				builder.append("iconCls:'fa fa-plus',");
				builder.append("handler:function(){");
				builder.append(this.jsInstance + ".hidePanel('"+this.field+"');");
				builder.append("IMessageBox.window('新增', '/panda/" + prefernece.getPopupUrl() + "?openType=window', "+prefernece.getWidth()+", "+prefernece.getHeight()+", function(){");
				builder.append("});");
				builder.append("}");
				builder.append("}]");
				this.editorOptions.add(builder.toString());
			}
		}
		
		this.refKey = this.field;
		this.url = "/panda/rest/reference?id=" + this.prefernece.getId() + "&filter=" + this.filter;
		
		// 加了分页，最少得400
		if(prefernece.getPanelWidth() != null){

			this.panelWidth = prefernece.getPanelWidth();
		}else {

			this.panelWidth = width >= 460 ? width : 460;
		}
		
		if(prefernece.getPanelHeight() != null){
			
			this.panelHeight = prefernece.getPanelHeight();
		}else{

			this.panelHeight = 385;
		}
		super.createEditor();
	}

	public PReference getPrefernece() {
		return prefernece;
	}

	public void setPrefernece(PReference prefernece) {
		this.prefernece = prefernece;
	}
}
