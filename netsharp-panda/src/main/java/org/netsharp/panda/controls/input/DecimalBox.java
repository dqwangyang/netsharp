package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.HtmlAttr;

public class DecimalBox extends NumberBox
{

    @HtmlAttr(html="sumId")
    public String sumId;
    
	@DataOption(html = "precision")
	public int precision = 2;

    @Override
    public void initialize()
    {
        super.initialize();
    }
}
