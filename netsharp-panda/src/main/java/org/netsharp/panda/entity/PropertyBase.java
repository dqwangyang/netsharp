package org.netsharp.panda.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.entity.Entity;

public abstract class PropertyBase extends Entity{

	private static final long serialVersionUID = 13122467874713090L;
	
	@Column(name = "group_name",header="分组")
	protected String groupName;
	
	@Column(name = "default_value",header="默认值")
    protected String defaultValue;
    
	@Column(name = "memoto",header="备注")
    protected String memoto;
    
	@Column(name = "required",header="必输")
    protected boolean required = false;
    
	@Column(name = "property_trigger",header="属性触发器")
    protected String propertyTrigger;
    
	@Column(name = "property_codition",header="属性条件")
    protected String propertyCodition;
    
	@Column(name = "unique",header="唯一约束")
    protected boolean unique = false;
    
	@Column(name = "property_validation",header="属性验证")
    protected String propertyValidation;
    
	@Column(name = "readonly",header="只读")
    protected boolean readonly = false;
    
	@Column(name = "appconfig_condition",header="选项条件")
    protected String appconfigCondition;
    
	@Column(name = "tooltip",header="提示语")
    protected String tooltip;
    
	@Column(name = "no_copy",header="不复制")
    protected boolean nocopy = false;
    
	@Column(name = "audit_edit",header="审核后可改")
    protected boolean auditEdit = false;

    protected PropertyBase(){
        super();
    }

    public String getGroupName(){
        return this.groupName;
    }
    public PropertyBase setGroupName(String groupName){
        this.groupName=groupName;
        return this;
    }
    public String getDefaultValue(){
        return this.defaultValue;
    }
    public PropertyBase setDefaultValue(String defaultValue){
        this.defaultValue=defaultValue;
        return this;
    }
    public String getMemoto(){
        return this.memoto;
    }
    public PropertyBase setMemoto(String memoto){
        this.memoto=memoto;
        return this;
    }

    public String getPropertyTrigger(){
        return this.propertyTrigger;
    }
    public PropertyBase setPropertyTrigger(String propertyTrigger){
        this.propertyTrigger=propertyTrigger;
        return this;
    }
    public String getPropertyCodition(){
        return this.propertyCodition;
    }
    public PropertyBase setPropertyCodition(String propertyCodition){
        this.propertyCodition=propertyCodition;
        return this;
    }

    public String getPropertyValidation(){
        return this.propertyValidation;
    }
    public PropertyBase setPropertyValidation(String propertyValidation){
        this.propertyValidation=propertyValidation;
        return this;
    }

    public String getAppconfigCondition(){
        return this.appconfigCondition;
    }
    public PropertyBase setAppconfigCondition(String appconfigCondition){
        this.appconfigCondition=appconfigCondition;
        return this;
    }
    public String getTooltip(){
        return this.tooltip;
    }
    public PropertyBase setTooltip(String tooltip){
        this.tooltip=tooltip;
        return this;
    }

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public boolean isNocopy() {
		return nocopy;
	}

	public void setNocopy(boolean nocopy) {
		this.nocopy = nocopy;
	}

	public boolean isAuditEdit() {
		return auditEdit;
	}

	public void setAuditEdit(boolean auditEdit) {
		this.auditEdit = auditEdit;
	}
}