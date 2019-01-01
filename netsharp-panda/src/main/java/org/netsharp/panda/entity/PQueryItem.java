package org.netsharp.panda.entity;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

@Table(name="ui_pa_query_item",header="查询项")
public class PQueryItem extends UiField{


	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 7169041450593659766L;

	@Column(name="customized",header="自定义查询")
	private boolean customized = false;
	
	@Column(name="permission",header="权限")
    private Integer permission;
    
	@Column(name="value1",header="值1")
    private String value1;
    
	@Column(name="value2",header="值2")
    private String value2;
    
	@Column(name="operation",header="操作类型1")
    private String operation;

    @Column(name="ref_filter",header="参照查询条件",size=500)
    private String refFilter;
    
    @Column(name="db_type",header="数据类型")
    private String dbType;
    
    @Column(name="parameter_name1",header="参数名1")
    private String parameterName1;
    
    @Column(name="parameter_name2",header="参数名2")
    private String parameterName2;
    
    @Column(name="text",header="显示")
    private String text;
    
    @Column(name="validated",header="有效的输入")
    private boolean validated = false;
    
    @Column(name="custom_oql",header="自定义OQL")
    private boolean customOql = false;
    
    @Column(name="multiple",header="多选")
    private boolean multiple = false;
    
    @Column(name="shortcut",header="启用快捷键")
    private boolean shortcut = false;
    
    @Column(name = "interzone",header="间隔")
    private boolean interzone = false;

	@Column(name = "required",header="必输")
    private boolean required = false;
    
    @Column(name="aliases",header="别名")
    private String aliases;
    
	@Column(name = "width", header = "宽度")
	private int width = 150;

    @JsonBackReference
    @Reference(foreignKey="queryProjectId")
    private PQueryProject queryProject;
    
    @Column(name="query_project_id",header="查询方案")
    private Integer queryProjectId;


	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getRefFilter() {
		return refFilter;
	}

	public void setRefFilter(String refFilter) {
		this.refFilter = refFilter;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public boolean isShortcut() {
		return shortcut;
	}

	public void setShortcut(boolean shortcut) {
		this.shortcut = shortcut;
	}

	public String getParameterName1() {
		return parameterName1;
	}

	public void setParameterName1(String parameterName1) {
		this.parameterName1 = parameterName1;
	}

	public String getParameterName2() {
		return parameterName2;
	}

	public void setParameterName2(String parameterName2) {
		this.parameterName2 = parameterName2;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAliases() {
		return aliases;
	}

	public void setAliases(String aliases) {
		this.aliases = aliases;
	}

	public PQueryProject getQueryProject() {
		return queryProject;
	}

	public void setQueryProject(PQueryProject queryProject) {
		this.queryProject = queryProject;
	}

	public Integer getQueryProjectId() {
		return queryProjectId;
	}

	public void setQueryProjectId(Integer queryProjectId) {
		this.queryProjectId = queryProjectId;
	}

	public boolean isCustomized() {
		return customized;
	}

	public void setCustomized(boolean customized) {
		this.customized = customized;
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}

	public boolean isCustomOql() {
		return customOql;
	}

	public void setCustomOql(boolean customOql) {
		this.customOql = customOql;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public boolean isInterzone() {
		return interzone;
	}

	public void setInterzone(boolean interzone) {
		this.interzone = interzone;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}