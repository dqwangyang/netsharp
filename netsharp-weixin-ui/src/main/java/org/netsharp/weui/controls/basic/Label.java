package org.netsharp.weui.controls.basic;

import org.netsharp.weui.annotation.HtmlNode;
import org.netsharp.weui.controls.Control;

@HtmlNode(html="label", isValue=true)
public class Label extends Control
{
	public Label(){}
	
	public Label(String value){
		this.value=value;
	}
    public String value;
}
