package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.HtmlAttr;

public class TextBox extends Input {
	@HtmlAttr(html = "placeholder")
	public String placeholder;

	@DataOption(html = "validType", isEvent = true)
	public String validType;

	@DataOption(html = "validateOnCreate")
	public boolean validateOnCreate = false;

	@DataOption(html = "validateOnBlur")
	public boolean validateOnBlur = true;

	@HtmlAttr(html = "onchange")
	public String onChange;

	@Override
	public void initialize() {
		super.initialize();
		this.setClassName("easyui-validatebox nsInput");
		this.type = "text";
	}
}
