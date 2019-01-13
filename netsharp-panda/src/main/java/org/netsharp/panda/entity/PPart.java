package org.netsharp.panda.entity;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.CompositeOne;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.resourcenode.entity.ResourceBizEntity;
import org.netsharp.util.StringManager;

@Table(name="ui_pa_part",header="工作区部件")
public class PPart extends ResourceBizEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 6207965780887393190L;

	@Column(name="tooltip",size=500,header="提示")
    private String tooltip;
	
	@Column(name="js_controller",size=500,header="客户端控制器")
	private String jsController;
	
	@Column(name="service_controller",size=500,header="服务端控制器")
	private String serviceController;
	
	@Column(name="dock_style",header="布局样式")
	private DockType dockStyle;
	
	@Column(name="parent_code",header="上级编码")
	private String parentCode;
	
	@Column(name="relation_cole",header="关联编码")
	private String relationRole;
	
	@Column(name="filter",header="过滤条件")
	private String filter="";
	
	@Column(name="style",header="样式")
	private String style;
	
	@Column(name="imports",size=500,header="导入的文件")
	private String imports;
	
	@Column(name="visible",header="显示")
	private boolean visible = true;
	
	@Column(name="header_visible",header="显示标题")
	private boolean headerVisible = false;
	
	@Column(name="toolbar",header="工具栏路径")
	private String toolbar;
	
	@Column(name="report_project_id",header="报表方案")
	private Integer reportProjectId;

	@Column(name="width",header="宽度")
	private Integer width = 0;
	
	@Column(name="height",header="高度")
	private Integer height = 0;
	
	@Column(name="open_mode",header="打开方式")
	private OpenMode openMode = OpenMode.OPEN;

	@Column(name="window_width",header="弹出框宽度")
	private Integer windowWidth = 0;
	
	@Column(name="window_height",header="弹出框高度")
	private Integer windowHeight = 0;

	@Column(name="url",size=300,header="路径")
	private String url;
	
//	@JsonIgnore
	@CompositeOne(foreignKey="formId",header="表单")
	private PForm form;
	
	@Column(name="form_id",header="表单Id")
	private Integer formId;
	
	@Reference(foreignKey="partTypeId",header="部件类型")
	private PPartType partType;
	
	@Column(name="part_type_id",header="部件类型")
	private Long partTypeId;
	
//	@JsonIgnore
	@CompositeOne(foreignKey="datagridId",header="列表")
	private PDatagrid datagrid;
	
	@Column(name="datagrid_id",header="表格方案Id")
	private Long datagridId;
	
	@Column(name="workspace_id",header="工作区ID")
	private Long workspaceId;
	
	@JsonBackReference
	@Reference(foreignKey="workspaceId")
	private PWorkspace workspace;
	
	@Column(name = "auto_query", header = "自动查询")
	private boolean autoQuery = true;
	
	public boolean isAutoQuery() {
		return autoQuery;
	}

	public void setAutoQuery(boolean autoQuery) {
		this.autoQuery = autoQuery;
	}

	public PPart parent(){
		
		if(StringManager.isNullOrEmpty( this.getParentCode())){
			return null;
		}
		
		for(PPart part : workspace.getParts()){
			if(StringManager.equals(part.getCode(), this.getParentCode())){
				return part;
			}
		}
		
		return null;
	}

	public String getJsController() {

		if(!StringManager.isNullOrEmpty(this.jsController)){
			return this.jsController;
		}
		
		if(this.partType!=null){
			return this.partType.getJsController();
		}
		return null;
	}

	public void setJsController(String jsController) {
		this.jsController = jsController;
	}

	public String getServiceController() {
		
		if(!StringManager.isNullOrEmpty(this.serviceController)){
			return this.serviceController;
		}
		
		if(this.partType!=null){
			return this.partType.getServiceController();
		}
		return null;
	}

	public void setServiceController(String serviceController) {
		this.serviceController = serviceController;
	}

	public DockType getDockStyle() {
		if(this.dockStyle!=null){
			return this.dockStyle;
		}
		
		if(this.partType!=null){
			return this.partType.getDockStyle();
		}
		return null;
	}

	public void setDockStyle(DockType dockStyle) {
		this.dockStyle = dockStyle;
		
	}

	public String getRelationRole() {
		return relationRole;
	}

	public void setRelationRole(String relationRole) {
		this.relationRole = relationRole;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getImports() {
		return imports;
	}

	public void setImports(String imports) {
		this.imports = imports;
	}

	public String getToolbar() {
		return toolbar;
	}

	public void setToolbar(String toolbar) {
		this.toolbar = toolbar;
	}

	public PForm getForm() {
		return form;
	}

	public void setForm(PForm form) {
		this.form = form;
	}

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	public PPartType getPartType() {
		return partType;
	}

	public void setPartType(PPartType partType) {
		this.partType = partType;
		
		if(this.partType==null){
			this.partTypeId=null;
		}
		else{
			this.partTypeId=partType.getId();
		}
	}

	public Long getPartTypeId() {
		return partTypeId;
	}

	public void setPartTypeId(Long idPartType) {
		this.partTypeId = idPartType;
	}

	public Long getWorkspaceId() {
		return workspaceId;
	}

	public void setWorkspaceId(Long workspaceId) {
		this.workspaceId = workspaceId;
	}

	public PDatagrid getDatagrid() {
		return datagrid;
	}

	public void setDatagrid(PDatagrid datagrid) {
		this.datagrid = datagrid;
		
		if(this.datagrid==null){
			this.datagridId=null;
		}
		else{
			this.datagridId=this.datagrid.getId();
		}
	}

	public Long getDatagridId() {
		return datagridId;
	}

	public void setDatagridId(Long datagridId) {
		this.datagridId = datagridId;
	}

	public Integer getReportProjectId() {
		return reportProjectId;
	}

	public void setReportProjectId(Integer idReportProject) {
		this.reportProjectId = idReportProject;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public PWorkspace getWorkspace() {
		return workspace;
	}

	
	public void setWorkspace(PWorkspace workspace) {
		this.workspace = workspace;
		if(this.workspace==null){
			this.workspaceId=null;
		}else{
			this.workspaceId=this.workspace.getId();
		}
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public boolean isVisible() {
		return visible;
	}

	public boolean isHeaderVisible() {
		return headerVisible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setHeaderVisible(boolean headerVisible) {
		this.headerVisible = headerVisible;
	}

	public Integer getWindowWidth() {
		return windowWidth;
	}

	public void setWindowWidth(Integer windowWidth) {
		this.windowWidth = windowWidth;
	}

	public Integer getWindowHeight() {
		return windowHeight;
	}

	public void setWindowHeight(Integer windowHeight) {
		this.windowHeight = windowHeight;
	}

	public OpenMode getOpenMode() {
		return openMode;
	}

	public void setOpenMode(OpenMode openMode) {
		this.openMode = openMode;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
}
