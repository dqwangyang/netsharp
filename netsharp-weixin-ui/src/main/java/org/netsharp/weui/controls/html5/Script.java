package org.netsharp.weui.controls.html5;

import org.netsharp.weui.annotation.HtmlAttr;
import org.netsharp.weui.annotation.HtmlNode;
import org.netsharp.weui.controls.Control;

@HtmlNode(html="script",isValue=true)
public class Script extends Control
{
    public String value;

    @HtmlAttr(html="type")
    public String type;

//    @Override
//    public void initialize()
//    {
//    	type = "text/javascript";
//        super.initialize();
//    }
}