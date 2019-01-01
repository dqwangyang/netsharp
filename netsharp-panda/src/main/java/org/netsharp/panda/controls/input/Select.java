package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;

@HtmlNode(html="select")
public class Select extends Control
{
    @HtmlAttr(html="collected")
    public Boolean collected;
    
    @DataOption(html="onChange",isEvent=true)
    public String onChange;

    @DataOption(html="editable")
    public Boolean editable;
    
    @HtmlAttr(html="disabled")
    public boolean disabled;
    
    @DataOption(html="required")
    public boolean required;
    
    @DataOption(html="value")
    public String value;
    
    @Override
    public void initialize()
    {
        super.initialize();

        this.className = "easyui-combobox";
    }
}
