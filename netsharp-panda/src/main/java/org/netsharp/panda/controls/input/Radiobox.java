package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.HtmlAttr;


public class Radiobox extends Input
{
    @HtmlAttr(html="checked")
    public String checked;
    
    @HtmlAttr(html="title")
    public String title;
    
	@Override
	public void initialize() {
		
		this.type = "radio";
		super.initialize();
	}
}
