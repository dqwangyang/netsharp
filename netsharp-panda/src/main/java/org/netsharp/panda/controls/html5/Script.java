package org.netsharp.panda.controls.html5;

import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;

@HtmlNode(html="script",isValue=true)
public class Script extends Control
{
    public String value;

    @HtmlAttr(html="type")
    public String type;

    @Override
    public void initialize()
    {
    	type = "text/javascript";
        super.initialize();
    }
}