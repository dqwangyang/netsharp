package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.DataOption;

public class FileBox extends TextBox{
	
    @DataOption(html="buttonText")
    public String buttonText;
    
    @DataOption(html="buttonIcon")
    public String buttonIcon;
    
    @DataOption(html="buttonAlign")
    public String buttonAlign;
    
    @DataOption(html="accept")
    public String accept;
    
    @DataOption(html="multiple")
    public Boolean multiple;
    
    @DataOption(html="separator")
    public String separator;
    
    @Override
    public void initialize()
    {
        super.initialize();
    	this.setClassName("easyui-filebox nsInput");
        this.controlType="FileBox";
    }
}
