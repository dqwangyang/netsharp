package org.netsharp.panda.controls.other;

import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;

@HtmlNode(html="body")
public class Body extends Control
{
	@HtmlAttr(html = "onresize")
	public String onresize;
}
