package org.netsharp.panda.controls.tab;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.controls.other.Div;
import org.netsharp.panda.dic.TabPosition;

public class Tab extends Div
{
    @DataOption(html="fit")
    public boolean fit;

    @DataOption(html="plain")
    public boolean plain;

    @DataOption(html="tabPosition")
    public TabPosition tabPosition = TabPosition.top;

    @DataOption(html="onSelect",isEvent=true)
    public String onSelect;

    @DataOption(html="tabWidth")
    public int tabWidth;

    @DataOption(html="tabHeight")
    public int tabHeight;
    
    
    @Override
    public void initialize()
    {
        this.setClassName("easyui-tabs");

        super.initialize();
    }
}