package org.netsharp.panda.controls.bootstrap;

import org.netsharp.panda.controls.other.A;
import org.netsharp.panda.controls.other.Div;
import org.netsharp.panda.controls.other.H3;
import org.netsharp.panda.controls.other.I;
import org.netsharp.panda.controls.other.P;
import org.netsharp.util.StringManager;

public class SmallBox extends Div{
	
    public String bgClassName;
	
    public String data;
    
    public String explain;
    
    public String iconClassName;
    
    public String moreUrl;

    @Override
	public void initialize() {
		this.setClassName("small-box " + this.bgClassName) ;
		Div inner = new Div();{
			
			inner.setClassName("inner") ;
			H3 h3 = new H3();{
				h3.value = this.data;
			}
			inner.getControls().add(h3);
			
			P p = new P();{
				p.value = this.explain;
			}
			
			inner.getControls().add(p);
		}
		this.getControls().add(inner);
		
		
		Div icon = new Div();{
			icon.setClassName("icon");
			I i = new I();{
				i.setClassName(this.iconClassName) ;
			}
			icon.getControls().add(i);
		}
		this.getControls().add(icon);
		
		
		if(!StringManager.isNullOrEmpty(this.moreUrl)){
			
			A a = new A();{
				a.setClassName("small-box-footer");
				a.href = this.moreUrl;
				a.getInnerValues().put("target","_blank");
				a.value = "更多信息";
				I i = new I();{
					i.setClassName("fa fa-arrow-circle-right");
				}
				a.getControls().add(i);
			}
			this.getControls().add(a);
		}

    	super.initialize();
	}
}
