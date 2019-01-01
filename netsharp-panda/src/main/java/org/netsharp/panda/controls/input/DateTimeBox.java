package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.DataOption;

public class DateTimeBox extends TextBox
{
	@DataOption(html = "width")
	public int width;
	
    @Override
    public void initialize()
    {
        super.initialize();

        this.setClassName("easyui-datetimebox");
    }
}
