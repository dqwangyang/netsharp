package org.netsharp.panda.core;

import org.netsharp.panda.controls.tab.Tab;
import org.netsharp.panda.dic.DockType;

public class Parts extends Tab {
	public DockType dockStyle;
	public Workspace workspace;

	public void setSize() {
		// //下面代码只支持左右，不支持上下
		workspace.addJscript("            var eastHeight = $(\"#" + dockStyle.getText() + "\").css(\"height\");", JscriptType.Initialize);
		workspace.addJscript("            $(\"#" + this.id + "\").tabs({ \"height\": Number(eastHeight.substring(0, eastHeight.length - 2)) });", JscriptType.Initialize);
	}
}
