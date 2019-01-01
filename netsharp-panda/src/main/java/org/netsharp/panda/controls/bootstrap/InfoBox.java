package org.netsharp.panda.controls.bootstrap;

import org.netsharp.panda.controls.other.Div;
import org.netsharp.panda.controls.other.I;
import org.netsharp.panda.controls.other.Span;

public class InfoBox extends Div{

	 public String iconBgClassName;

	 public String iconClassName;
	 
	 public String text;
	 
	 public String data;
	 
    @Override
	public void initialize() {
    	
    	this.setClassName("info-box") ;
    	Span iconSpan = new Span();{
    		iconSpan.setClassName("info-box-icon "+ iconBgClassName) ;
			I i = new I();{
				i.setClassName(this.iconClassName) ;
			}
			iconSpan.getControls().add(i);
    	}
    	this.getControls().add(iconSpan);
    	
    	Div contentDiv = new Div();{
    		contentDiv.setClassName("info-box-content") ;
    		Span textSpan = new Span();{
    			textSpan.setClassName("info-box-text") ;
    			textSpan.value = text;
    		}
    		contentDiv.getControls().add(textSpan);
    		
    		Span numberSpan = new Span();{
    			numberSpan.setClassName("info-box-number") ;
    			numberSpan.value = data;
    		}
    		contentDiv.getControls().add(numberSpan);
    	}
    	this.getControls().add(contentDiv);
    	super.initialize();
    }
}
