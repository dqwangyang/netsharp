package org.netsharp.panda.plugin.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.panda.plugin.doozer.DoozerAccordion;
import org.netsharp.plugin.core.Doozer;
import org.netsharp.plugin.entity.Codonable;

/*插件系统手风琴控件配置*/

@Table(name="rs_accordion_item",orderBy="seq")
@Doozer(type=DoozerAccordion.class)
public class PAccordionItem extends Codonable {
	
	private static final long serialVersionUID = 7639828942986668816L;
	
	@Column(size=500)
	private String treePath;
	
	@Column(name="icon",header="图标")
	private String icon;

	public String getTreePath() {
		return treePath;
	}
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}
