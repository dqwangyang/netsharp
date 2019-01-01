package org.netsharp.panda.controls.window;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.controls.layout.Panel;

public class Dialog extends Panel{

    @DataOption(html="resizable")
    public boolean resizable = true;
    
    @DataOption(html="modal")
    public boolean modal = true;
    
    @Override
    public void initialize()
    {
    	this.closed = true;
        this.setClassName("easyui-dialog");
        super.initialize();
    }
}
