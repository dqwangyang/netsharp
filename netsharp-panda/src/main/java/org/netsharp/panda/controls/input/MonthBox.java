package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.DataOption;

public class MonthBox extends DateBox{
	
	@DataOption(html = "showday")
	public boolean showDay;
	
    //宽度设置无效
    @Override
    public void initialize()
    {
        super.initialize();
        this.showDay = false;
        this.setClassName("easyui-datebox");
    }
}
