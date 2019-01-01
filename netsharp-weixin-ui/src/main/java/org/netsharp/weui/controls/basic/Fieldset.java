package org.netsharp.weui.controls.basic;

import org.netsharp.weui.annotation.HtmlNode;
import org.netsharp.weui.controls.Control;

@HtmlNode(html="fieldset")
public class Fieldset extends Control{

	public String title;
	
    @Override
    public void initialize()
    {
        super.initialize();
    	Legend legend = new Legend();{
    		
    		legend.value = this.title;
    	}
    	this.controls.add(legend);
    }
    
}
