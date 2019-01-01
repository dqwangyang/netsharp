package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.HtmlNode;

@HtmlNode(html = "select")
public class RadioboxGroup extends Input
{

	@DataOption(html = "onChange", isEvent = true)
	public String onChange;
	
	@Override
	public void initialize() {

		this.controlType = "RadioboxGroup";
		this.setClassName("easyui-radiogroupbox");
		this.collected = true;
		super.initialize();
	}
}