package org.netsharp.panda.controls.upload;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.controls.input.Input;


public class QiNiuUpload extends Input{

    @HtmlAttr(html="collected")
    public Boolean collected;
    
	@DataOption(html = "width")
	public int Width;

    @Override
    public void initialize()
    {
        this.setClassName("easyui-filebox nsInput");
        super.initialize();
    }
}
