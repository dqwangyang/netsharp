package org.netsharp.panda.controls.tree;

import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;

@HtmlNode(html="li")
 public class Li extends Control
{
    @HtmlAttr(html="onclick")
    public String onclick;
}
