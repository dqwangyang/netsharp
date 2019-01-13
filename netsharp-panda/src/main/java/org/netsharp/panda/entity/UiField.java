package org.netsharp.panda.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.panda.controls.ControlTypes;

/**
 * @author HW
 * 界面字段
 */
public abstract class UiField extends PropertableField {

	private static final long serialVersionUID = -1955925502266384793L;
	
	@Column(name = "visible",header="显示")
	protected boolean visible = true;
	
	@Column(name = "header",header="字段")
    protected String header;
    
	@Column(name = "control_type",header="控件类型")
    protected ControlTypes controlType;
    
	@Column(name = "system",header="系统字段")
    protected boolean system = false;
    
    @Column(name = "custom_control_type",size=200,header="自定义控件类型")
    protected String customControlType;
    
    @Column(name = "appconfig_condition",size=200,header="选项条件")
    protected String appconfigCondition;
    
    @Column(name = "tooltip",size=500,header="提示语")
    protected String tooltip;

    @Reference(foreignKey="referenceId",header="参照")
	private PReference reference;
    
    @Column(name="reference_id",header="参照")
    private Long referenceId;
    
    @Column(name="reference_code",header="参照编码")
    private String referenceCode;
    
    @Column(name="data_options",size=200,header="选项配置")
    private String dataOptions;
    
    public String getReferenceCode() {
		return referenceCode;
	}
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}
	public String getHeader(){
        return this.header;
    }
    public UiField setHeader(String header){
        this.header=header;
        return this;
    }
    public ControlTypes getControlType(){
        return this.controlType;
    }
    public UiField setControlType(ControlTypes controlType){
        this.controlType=controlType;
        return this;
    }

    public String getCustomControlType() {
		return customControlType;
	}
	public void setCustomControlType(String customControlType) {
		this.customControlType = customControlType;
	}
	public String getAppconfigCondition(){
        return this.appconfigCondition;
    }
    public UiField setAppconfigCondition(String appconfigCondition){
        this.appconfigCondition=appconfigCondition;
        return this;
    }
    public String getTooltip(){
        return this.tooltip;
    }
    public UiField setTooltip(String tooltip){
        this.tooltip=tooltip;
        return this;
    }
	public PReference getReference() {
		return reference;
	}
	public void setReference(PReference reference) {
		this.reference = reference;
		if(this.reference!=null){
			this.referenceId=this.reference.getId();
		}
		else{
			this.referenceId=null;
		}
	}
	public Long getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}

	public String getDataOptions() {
		return dataOptions;
	}
	public void setDataOptions(String dataOptions) {
		this.dataOptions = dataOptions;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean isSystem() {
		return system;
	}
	public void setSystem(boolean system) {
		this.system = system;
	}
}