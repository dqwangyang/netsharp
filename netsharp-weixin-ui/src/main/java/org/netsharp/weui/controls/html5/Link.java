package org.netsharp.weui.controls.html5;

import org.netsharp.weui.annotation.HtmlAttr;
import org.netsharp.weui.annotation.HtmlNode;
import org.netsharp.weui.controls.Control;

@HtmlNode(html="link")
public class Link extends Control
{
    @HtmlAttr(html="rel")
    public String rel;

    @HtmlAttr(html="type")
    public String type;

    @HtmlAttr(html="href")
    public String href;

    @HtmlAttr(html="media")
    public String media;
}
