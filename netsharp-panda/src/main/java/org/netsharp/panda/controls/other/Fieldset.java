package org.netsharp.panda.controls.other;

import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;

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
