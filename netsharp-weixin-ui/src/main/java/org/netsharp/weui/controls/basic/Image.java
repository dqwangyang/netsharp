package org.netsharp.weui.controls.basic;

import org.netsharp.weui.annotation.HtmlAttr;
import org.netsharp.weui.annotation.HtmlNode;
import org.netsharp.weui.controls.Control;

@HtmlNode(html="img")
public class Image extends Control
{
    @HtmlAttr(html="src")
    public String src;

    @HtmlAttr(html="alt")
    public String alt;
}
