package org.netsharp.panda.controls.table;

import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;

@HtmlNode(html = "td", isValue = true)
public class TD extends Control {
	@HtmlAttr(html = "colspan")
	public Integer colspan;

	@HtmlAttr(html = "rowspan")
	public Integer rowspan;

	@HtmlAttr(html = "align")
	public String align;

	@HtmlAttr(html = "width")
	public String width;

	public String value;
}
