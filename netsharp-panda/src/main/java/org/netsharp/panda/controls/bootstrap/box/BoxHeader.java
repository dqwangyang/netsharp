package org.netsharp.panda.controls.bootstrap.box;

import org.netsharp.panda.controls.other.Button;
import org.netsharp.panda.controls.other.Div;
import org.netsharp.panda.controls.other.H3;
import org.netsharp.panda.controls.other.I;

public class BoxHeader extends Div{

	public String headerText;
	 
    @Override
	public void initialize() {
    	
    	this.setClassName("box-header with-border");
    	
    	H3 h3 = new H3();{
    		h3.setClassName("box-title");
    		h3.value = headerText;
    	}
    	this.getControls().add(h3);

    	Div tools = new Div();{
    		
    		Button collapseButton = new Button();{
    			
    			collapseButton.setClassName("btn btn-box-tool");
    			collapseButton.getInnerValues().put("data-widget", "collapse");
    			I icon = new I();{
    				icon.setClassName("fa fa-minus");
    			}
    			collapseButton.getControls().add(icon);
    		}
    		tools.getControls().add(collapseButton);
    		
    		Button removeButton = new Button();{
    			
    			removeButton.setClassName("btn btn-box-tool");
    			removeButton.getInnerValues().put("data-widget", "remove");
    			I icon = new I();{
    				icon.setClassName("fa fa-times");
    			}
    			removeButton.getControls().add(icon);
    		}
    		tools.getControls().add(collapseButton);
    	}
    	this.getControls().add(tools);
    }
}
