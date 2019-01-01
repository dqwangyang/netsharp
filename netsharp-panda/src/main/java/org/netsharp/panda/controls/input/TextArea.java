package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;
import org.netsharp.util.StringManager;

@HtmlNode(html="textarea",isValue=true)
public class TextArea extends Control
{
    @HtmlAttr(html="collected")
    public Boolean collected;

    @HtmlAttr(html="controlType")
    public String controlType;

    @HtmlAttr(html="placeholder")
    public String placeholder;
    
    @HtmlAttr(html="required")
    public boolean required;

    @HtmlAttr(html="disabled")
    public boolean disabled;
    
    @HtmlAttr(html="_disabled")
    public boolean oldDisabled;

    @DataOption(html="validateOnCreate")
    public boolean validateOnCreate = false;
    
    @DataOption(html="validateOnBlur")
    public boolean validateOnBlur = true;
    
    @DataOption(html="validType", isEvent = true)
    public String validType;
    
    public String value;

    @Override
    public void initialize()
    {
        if (StringManager.isNullOrEmpty(this.controlType))
        {
            this.controlType = this.getClass().getSimpleName();
        }

        this.setClassName("easyui-validatebox " + this.getClassName());
        this.value = "";
        super.initialize();
    }
}
