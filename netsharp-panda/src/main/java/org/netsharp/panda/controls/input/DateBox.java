package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.DataOption;

public class DateBox extends TextBox {
	@DataOption(html = "width")
	public int width;

	@DataOption(html = "onSelect", isEvent = true)
	public String onSelect;

	// 宽度设置无效
	@Override
	public void initialize() {
		super.initialize();

		this.setClassName("easyui-datebox");
	}
}
