package org.netsharp.panda.controls.toolbar;

import java.util.ArrayList;

import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.other.Div;
import org.netsharp.util.StringManager;

@HtmlNode(html="div")
public class Toolbar extends Control
{
    public String SubWidth = "300px";

    @Override
    public void initialize()
    {
    	if(StringManager.isNullOrEmpty(this.getId())){

            this.setId("toolbar");
    	}

        this.setClassName("toolbar");

        super.initialize();
    }
    
    // initialize之前执行
    // parent是toolbar的parent
    public void populateSubs(Control parent)
    {
    	for(Control control : this.getControls()){
    		ToolbarItem item = (ToolbarItem)control;
    		if(!item.IsPopup){
    			continue;
    		}
    		
    		this.initializeSubItems(item, parent);
    	}
    }
    
    private void initializeSubItems(ToolbarItem item,Control parent){
    	
    	String id = "submenu"+item.getId();
    	item.Menu="#" + id;
    	Div div = new Div();
    	{
    		div.setId(id);
    		parent.getControls().add(div);
    	}
    	
    	for(Control subItem : item.getControls()){
    		div.getControls().add(subItem);
    	}
    	
    	item.getControls().clear();
    }

    public String getIds()
    {
    	return this.getId("");
    }
    
    //js三驾马车用来搜集所有的菜单
    public String getId(String prefix){
    	
        ArrayList<String> ss=new ArrayList<String>();
        for (Control control : this.getControls())
        {
        	ToolbarItem item=(ToolbarItem)control;
        	if(item!=null){
        		item.getIds(ss,prefix);
        	}
        }

        return "[" + StringManager.join(",", ss) + "]";
    }
}