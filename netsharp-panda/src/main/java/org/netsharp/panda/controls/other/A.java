package org.netsharp.panda.controls.other;

import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;


@HtmlNode(html="a", isValue=true)
public class A extends Control
{
    @Override
    public void initialize()
    {
        this.dataAjax = "false";

        super.initialize();
    }

    @HtmlAttr(html="href")
    public String href;
    
    @HtmlAttr(html="target")
    public String target;
    
    @HtmlAttr(html="onclick")
    public String onclick;

    @HtmlAttr(html="data-ajax")
    public String dataAjax;

    public String value;
    
    @HtmlAttr(html="title")
    public String title;
}
