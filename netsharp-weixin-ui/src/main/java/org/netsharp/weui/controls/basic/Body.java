package org.netsharp.weui.controls.basic;

import org.netsharp.weui.annotation.HtmlAttr;
import org.netsharp.weui.annotation.HtmlNode;
import org.netsharp.weui.controls.Control;

@HtmlNode(html="body")
public class Body extends Control
{
	@HtmlAttr(html = "onresize")
	public String onresize;
}
