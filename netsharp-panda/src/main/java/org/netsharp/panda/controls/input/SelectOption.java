package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;


@HtmlNode(html="option",isValue=true)
public class SelectOption extends Control
{
	public SelectOption(){}
	
	public SelectOption(String optionValue,String value){
		this.optionValue=optionValue;
		this.value=value;
	}
	
	public SelectOption(String optionValue,String value,boolean selected){
		this.value=value;
		this.selected = selected;
		this.optionValue=optionValue;
	}
	
    //显示
    public String value;

    @HtmlAttr(html="selected")
    public boolean selected;

    @HtmlAttr(html="value")//开发字段值
    public String optionValue;
}
