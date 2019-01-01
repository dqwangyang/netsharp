package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.HtmlAttr;

public class CurrencyBox extends NumberBox
{
    @HtmlAttr(html="sumId")
    public String sumId;
	
	@DataOption(html = "min")
	public int min;
	
	@DataOption(html = "precision")
	public int precision = 2;
	
    @Override
    public void initialize()
    {
        super.initialize();
        this.setClassName("easyui-numberbox nsInput");
        this.setStyle("text-align:right;");
    }
}