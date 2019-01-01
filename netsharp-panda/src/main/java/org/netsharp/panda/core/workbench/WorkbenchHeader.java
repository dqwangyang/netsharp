package org.netsharp.panda.core.workbench;

import org.netsharp.panda.controls.html5.Nav;
import org.netsharp.panda.controls.layout.LayoutPanel;
import org.netsharp.panda.controls.layout.LayoutRegion;
import org.netsharp.panda.controls.other.Div;

/**
 * @ClassName: WorkbenchHeader
 * @Description:TODO
 * @author: 韩伟
 * @date: 2017年9月17日 下午4:30:43
 * 
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved.
 */
public class WorkbenchHeader extends LayoutPanel {

	@Override
	public void initialize() {

		this.setId("header");
		this.split = false;
		this.region = LayoutRegion.Top;
		this.height = 50;

		Div logo = new Div();
		logo.setId("logo");
		this.getControls().add(logo);

		// <nav role="navigation" id="nav-main" class="okayNav">

		Nav nav = new Nav();
		{
			nav.className = "okayNav";
			nav.id = "nav-main";
			nav.innerValues.put("role", "navigation");
			nav.getControls().add(new LeftNavigation());
		}
		this.getControls().add(nav);
		this.getControls().add(new RightNavigation());
		super.initialize();
	}
}
