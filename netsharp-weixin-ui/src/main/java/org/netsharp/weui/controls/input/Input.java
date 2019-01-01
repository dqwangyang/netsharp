package org.netsharp.weui.controls.input;

import org.netsharp.util.StringManager;
import org.netsharp.weui.annotation.HtmlAttr;
import org.netsharp.weui.annotation.HtmlNode;
import org.netsharp.weui.controls.Control;


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

    @HtmlAttr(html="placeholder")
    public String placeholder;

    @HtmlAttr(html="disabled")
    public boolean disabled;

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
