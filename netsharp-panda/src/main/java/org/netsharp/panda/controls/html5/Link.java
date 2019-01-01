package org.netsharp.panda.controls.html5;

import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;

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
