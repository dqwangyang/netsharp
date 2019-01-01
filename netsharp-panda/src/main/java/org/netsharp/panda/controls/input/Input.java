package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;
import org.netsharp.util.StringManager;

@HtmlNode(html="input")
public class Input extends Control
{
    @HtmlAttr(html="type")
    public String type;

    @HtmlAttr(html="value")
    public String value;

    @HtmlAttr(html="onclick")
    public String onclick;

    @HtmlAttr(html="collected")
    public Boolean collected;

    @HtmlAttr(html="customControlType")
    public String customControlType;

    @DataOption(html="required")
    public boolean required;

    @HtmlAttr(html="disabled")
    public boolean disabled;
    
    @HtmlAttr(html="_disabled")
    public boolean oldDisabled;

    @HtmlAttr(html="readonly")
    public boolean readonly;

    @HtmlAttr(html="controlType")
    public String controlType;
    
    @Override
    public void initialize()
    {
        if (StringManager.isNullOrEmpty(this.controlType))
        {
            this.controlType = this.getClass().getSimpleName();
        }
        super.initialize();
    }

}
