package org.netsharp.panda.controls.layout;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.controls.other.Div;

public class Panel extends Div
{
    @DataOption(html="width")
    public Integer width;
    
    @DataOption(html="height")
    public Integer height;
    
    @DataOption(html="collapsible")
    public boolean collapsible;
    
    @DataOption(html="closed")
    public boolean closed = false;
    
    @DataOption(html="onCollapse", isEvent=true)
    public String onCollapse;

    @DataOption(html="onExpand", isEvent=true)
    public String onExpand;

    @DataOption(html="onResize", isEvent=true)
    public String onResize;
}

