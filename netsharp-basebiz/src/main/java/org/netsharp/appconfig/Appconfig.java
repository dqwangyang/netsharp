package org.netsharp.appconfig;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.resourcenode.entity.ResourceBizEntity;

//系统配置
@Table(name="sys_app_config",header="系统选项")
public class Appconfig extends ResourceBizEntity{ 

	private static final long serialVersionUID = 8996644750065517836L;
	
	@Column(name="value",size=200,header="值")
    private String value;
	
	@Column(name="default_value",size=200,header="默认值")
    private String defaultValue; 
	
    @Column(name="type",size=300,header="值类型")
    private String type;
    
    @Column(name="group_name",header="分组")
    private String groupName; 
    
    @Column(name="system",header="系统选项")
    private boolean system = true;
    
    @Column(name="visible",header="显示")
    private boolean visible = true;
    
	@Column(name="option_value",size=500,header="选项值",memoto="如：A,B,C")
    private String optionValue; 
    
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public boolean isSystem() {
		return system;
	}
	public void setSystem(boolean system) {
		this.system = system;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
}