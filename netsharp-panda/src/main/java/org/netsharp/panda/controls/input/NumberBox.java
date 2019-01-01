package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.DataOption;

public class NumberBox extends TextBox
{	
	@DataOption(html = "width")
	public Integer width;

	@DataOption(html = "min")
	public Integer min;
	
	@DataOption(html = "onChange", isEvent = true)
	public String onChange;
	
    @Override
    public void initialize()
    {
        super.initialize();

        this.setClassName("easyui-numberbox nsInput");
        String style = this.getStyle() + "text-align:right;";
        this.setStyle(style);
    }

}
