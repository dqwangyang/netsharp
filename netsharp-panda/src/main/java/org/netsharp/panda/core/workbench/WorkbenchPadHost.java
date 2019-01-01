package org.netsharp.panda.core.workbench;

import org.netsharp.panda.controls.layout.LayoutPanel;
import org.netsharp.panda.controls.layout.LayoutRegion;

public class WorkbenchPadHost extends LayoutPanel
{
    public static String padPath = "panda/workbench/pad";
    
    @Override
    public void initialize()
    {
    	this.width = 200;
        this.split = false;
        this.collapsible=true;
        this.region = LayoutRegion.Left;
        this.title = "导航栏";
        this.setId("west");
        super.initialize();
    }
}

   
