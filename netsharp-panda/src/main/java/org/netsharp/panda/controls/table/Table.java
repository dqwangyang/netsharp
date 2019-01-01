package org.netsharp.panda.controls.table;

import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;

@HtmlNode(html="table")
public class Table extends Control
{
	public Thead thead;

    @HtmlAttr(html="cellpadding")
    public Integer cellPadding = 0;

    @HtmlAttr(html="cellspacing")
    public Integer cellSpacing = 0;

//    @HtmlAttr(html="border")
//    public Integer border;

    @HtmlAttr(html="style")
    public String style;

    @HtmlAttr(html="width")
    public String width;

    @HtmlAttr(html="borderColor")
    public String borderColor;

	public Thead getThead() {
		return this.thead;
	}

	public void setThead(Thead thead) {
		this.thead = thead;
	}

	public Integer getCellPadding() {
		return this.cellPadding;
	}

	public void setCellPadding(Integer cellPadding) {
		this.cellPadding = cellPadding;
	}

	public Integer getCellSpacing() {
		return this.cellSpacing;
	}

	public void setCellSpacing(Integer cellSpacing) {
		this.cellSpacing = cellSpacing;
	}

//	public Integer getBorder() {
//		return border;
//	}
//
//	public void setBorder(Integer border) {
//		this.border = border;
//	}

	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

}