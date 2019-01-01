package org.netsharp.panda.core;

import org.netsharp.panda.controls.html5.PlainHtml;
import org.netsharp.util.StringManager;

public class Part extends View {
	
	public Workspace Workspace;

	protected void addTip() {
		
		if (StringManager.isNullOrEmpty(this.context.getTooltip())) {
			return;
		}

		String tip = "<div class='demo-info' style='margin-bottom:10px'>" + "            <div class='demo-tip icon-tip'>&nbsp;</div>" + "            <div>{memoto}</div>" + "</div>";

		tip = tip.replaceAll("{memoto}", this.context.getTooltip());

		this.getControls().add(new PlainHtml(tip));
	}

	@Override
	protected void addJscript(String jscript, JscriptType jsType) {
		Workspace.addJscript(jscript, jsType);
	}
}
