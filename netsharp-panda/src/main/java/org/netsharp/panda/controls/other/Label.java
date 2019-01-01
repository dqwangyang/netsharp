package org.netsharp.panda.controls.other;

import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;

@HtmlNode(html="label", isValue=true)
public class Label extends Control
{
    @HtmlAttr(html="collected")
    public Boolean collected;    
    
    @HtmlAttr(html="controlType")
    public String controlType;
    
	public Label(){}
	
	public Label(String value){
		this.value=value;
	}
    public String value;
}
