package org.netsharp.weui.controls.basic;

import org.netsharp.weui.annotation.HtmlAttr;
import org.netsharp.weui.annotation.HtmlNode;
import org.netsharp.weui.controls.Control;

@HtmlNode(html="div",isValue=true)
public class Div extends Control
{
    @HtmlAttr(html="title")
    public String title;

    @HtmlAttr(html="onclick")
    public String onclick;

    public String value;
}