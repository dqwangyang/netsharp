package org.netsharp.entity;

/**
 * @author xufangbo
 * 档案实体
 */
public abstract class ArchiveEntity extends BizEntity {
	
	private static final long serialVersionUID = 5189024365304065752L;
	
	@org.netsharp.core.annotations.Column(name="disabled",header="停用")
	private Boolean disabled=false;
	
	@org.netsharp.core.annotations.Column(name="shorthand",header="简称")
    private String shorthand;
    
	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	public String getShorthand() {
		return shorthand;
	}
	public void setShorthand(String shorthand) {
		this.shorthand = shorthand;
	}
}
