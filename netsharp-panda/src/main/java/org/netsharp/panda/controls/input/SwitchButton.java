package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.DataOption;

/**
 * @author hw
 * 切换开关
 */
public class SwitchButton extends Input {

	@DataOption(html = "width")
	public Integer width = 60;

	@DataOption(html = "height")
	public Integer height = 28;

	@DataOption(html = "handleWidth")
	public Integer handleWidth;

	@DataOption(html = "checked")
	public Boolean checked;

	@DataOption(html = "disabled")
	public Boolean disabled;

	@DataOption(html = "readonly")
	public Boolean readonly;

	@DataOption(html = "reversed")
	public Boolean reversed;

	@DataOption(html = "onText")
	public String onText = "是";

	@DataOption(html = "offText")
	public String offText = "否";

	@DataOption(html = "handleText")
	public String handleText;

	@DataOption(html = "value")
	public String value;

	@DataOption(html = "onChange", isEvent = true)
	public String onChange;

	@Override
	public void initialize() {
		super.initialize();
		this.setClassName("easyui-switchbutton");
	}
}
