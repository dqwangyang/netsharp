package org.netsharp.panda.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.CompositeOne;
import org.netsharp.core.annotations.Table;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.resourcenode.entity.ResourceBizEntity;

@Table(name="ui_pa_reference",header="参照信息")
public class PReference extends ResourceBizEntity{


	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 2767666604833441209L;

	@Column(name = "filter",header="过滤条件",size=1000)
	protected String filter;
	
	@Column(name = "can_new",header="允许新增")
    protected boolean canNew = false;
    
	@Column(name = "width",header="弹出宽度")
    protected Integer width;
    
	@Column(name = "height",header="弹出高度")
    protected Integer height;
    
	@Column(name = "defaulted",header="默认参照")
    protected boolean defaulted;
    
	@Column(name = "popup",header="弹出参照")
    protected boolean popup=false;
    
	@Column(name = "popup_url",header="弹出参照工作区Id")
    protected String popupUrl;
    
	@Column(name = "intelligent_mode",header="智能提示模式,不匹配,左匹配,右匹配,全匹配")
    protected IntelligentMode intelligentMode;
    
	@Column(name = "intelligent_fields",header="智能提示匹配的字段")
    protected String intelligentFields;
    
	@Column(name = "lazy",header="懒加载")
    protected boolean Lazy; 
    
	@Column(name = "order_by",header="排序")
    protected String orderby;
    
	@Column(name = "new_voucher_id",header="点击新增时弹出的部件工作区")
    protected Integer newVoucherId;
    
	@Column(name = "filter_script",header="过滤脚本")
    protected String filterScript;
    
	@Column(name = "byid",header="ById")
    protected boolean byId = false;
    
    @Column(name="filter_builder",size=500,header="过滤条件构造器")
    protected String filterBuilder;
    
    @CompositeOne(foreignKey="datagridId",header="网格")
    protected PDatagrid dataGrid;
    
    @Column(name="datagrid_id",header="表格方案Id")
    protected Integer datagridId;
    
    @Column(name="panel_width",header="下拉面板宽度")
    protected Integer panelWidth;

    @Column(name="panel_height",header="下拉面板高度")
    protected Integer panelHeight;

    public String getFilter(){
        return this.filter;
    }
    public PReference setFilter(String filter){
        this.filter=filter;
        return this;
    }
    public boolean canNew(){
        return this.canNew;
    }
    public PReference setCanNew(boolean isCanNew){
        this.canNew=isCanNew;
        return this;
    }
    public Integer getWidth(){
        return this.width;
    }
    public PReference setWidth(Integer width){
        this.width=width;
        return this;
    }
    public Integer getHeight(){
        return this.height;
    }
    public PReference setHeight(Integer height){
        this.height=height;
        return this;
    }

    public String getIntelligentFields(){
        return this.intelligentFields;
    }
    public PReference setIntelligentFields(String intelligentFields){
        this.intelligentFields=intelligentFields;
        return this;
    }

    public String getOrderby(){
        return this.orderby;
    }
    public PReference setOrderby(String orderby){
        this.orderby=orderby;
        return this;
    }
    public Integer getNewVoucherId(){
        return this.newVoucherId;
    }
    public PReference setNewVoucherId(Integer newVoucherId){
        this.newVoucherId=newVoucherId;
        return this;
    }
    public String getFilterScript(){
        return this.filterScript;
    }
    public PReference setFilterScript(String filterScript){
        this.filterScript=filterScript;
        return this;
    }

    public Integer getDatagridId(){
        return this.datagridId;
    }
    public PReference setDatagridId(Integer datagridId){
        this.datagridId=datagridId;
        return this;
    }

	public PDatagrid getDataGrid() {
		return dataGrid;
	}

	public void setDataGrid(PDatagrid dataGrid) {
		this.dataGrid = dataGrid;
	}

	public String getPopupUrl() {
		return popupUrl;
	}
	public void setPopupUrl(String popupUrl) {
		this.popupUrl = popupUrl;
	}
	public IntelligentMode getIntelligentMode() {
		return intelligentMode;
	}
	public void setIntelligentMode(IntelligentMode intelligentMode) {
		this.intelligentMode = intelligentMode;
	}
	public String getFilterBuilder() {
		return filterBuilder;
	}
	public void setFilterBuilder(String filterBuilder) {
		this.filterBuilder = filterBuilder;
	}
	public Integer getPanelWidth() {
		return panelWidth;
	}
	public void setPanelWidth(Integer panelWidth) {
		this.panelWidth = panelWidth;
	}
	public Integer getPanelHeight() {
		return panelHeight;
	}
	public void setPanelHeight(Integer panelHeight) {
		this.panelHeight = panelHeight;
	}
	public boolean isDefaulted() {
		return defaulted;
	}
	public void setDefaulted(boolean defaulted) {
		this.defaulted = defaulted;
	}
	public boolean isPopup() {
		return popup;
	}
	public void setPopup(boolean popup) {
		this.popup = popup;
	}
	public boolean isLazy() {
		return Lazy;
	}
	public void setLazy(boolean lazy) {
		Lazy = lazy;
	}
	public boolean isById() {
		return byId;
	}
	public void setById(boolean byId) {
		this.byId = byId;
	}
	public boolean isCanNew() {
		return canNew;
	}
}