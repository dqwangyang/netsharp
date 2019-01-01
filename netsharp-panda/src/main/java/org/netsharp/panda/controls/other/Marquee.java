package org.netsharp.panda.controls.other;

import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;

@HtmlNode(html = "marquee", isValue = true)
public class Marquee extends Control {
	
	// 滚动方向d:包括4个值：up、 down、 left和 right
	@HtmlAttr(html = "direction")
	public String direction;
	// 滚动方式:scroll:循环滚动，默认效果； slide:只滚动一次就停止； alternate:来回交替进行滚动
	@HtmlAttr(html = "behavior")
	public String behavior;
	// 滚动速度是设置每次滚动时移动的长度，以像素为单位
	@HtmlAttr(html = "scrollamount")
	public Integer scrollamount;
	// 设置滚动的时间间隔，单位是毫秒
	@HtmlAttr(html = "scrolldelay")
	public Integer scrolldelay;
	// 滚动循环loop（默认值是-1，滚动会不断的循环下去）
	@HtmlAttr(html = "loop")
	public Integer loop;

	// 滚动范围width、height
	@HtmlAttr(html = "width")
	public Integer width;
	// 滚动范围width、height
	@HtmlAttr(html = "height")
	public Integer height;

	// 空白空间hspace、vspace
	@HtmlAttr(html = "hspace")
	public Integer hspace;
	// 空白空间hspace、vspace
	@HtmlAttr(html = "vspace")
	public Integer vspace;

	// 滚动背景颜色
	@HtmlAttr(html = "bgcolor")
	private String bgcolor;

	public String value;
}
