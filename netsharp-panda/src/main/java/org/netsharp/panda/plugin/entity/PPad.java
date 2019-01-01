package org.netsharp.panda.plugin.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.panda.plugin.doozer.DoozerPad;
import org.netsharp.plugin.core.Doozer;
import org.netsharp.plugin.entity.Codonable;

@Table(name="rs_pad",orderBy="seq",header="工作台面板")
@Doozer(type=DoozerPad.class)
public class PPad  extends Codonable {
	
	private static final long serialVersionUID = 4615793446762431076L;
	
	@Column(header="图标")
	private String icon;
	
	@Column(size=300,header="类型")
	private String type;
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
