package org.netsharp.panda.controls.other;

import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;

@HtmlNode(html="img")
public class Image extends Control
{
    @HtmlAttr(html="src")
    public String src;

    @HtmlAttr(html="alt")
    public String alt;
}
