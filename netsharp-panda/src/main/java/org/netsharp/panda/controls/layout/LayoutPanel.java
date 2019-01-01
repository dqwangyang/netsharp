package org.netsharp.panda.controls.layout;

import org.netsharp.panda.annotation.DataOption;


public class LayoutPanel extends Panel{

    @DataOption(html="region")
    public String region;
    
    @DataOption(html="split")
    public boolean split;
    
}
