package org.netsharp.panda.controls.other;

import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;
import org.netsharp.util.StringManager;

@HtmlNode(html="a", isValue=true)
public class HyperLink extends Control
{
    @HtmlAttr(html="href")
    public String href;

    @HtmlAttr(html="target")
    public String target;

    @HtmlAttr(html="value")
    public String value;

    @HtmlAttr(html="title")
    public String title;

    @HtmlAttr(html="controlType")
    public String controlType;

    @HtmlAttr(html="collected")
    public Boolean collected;

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