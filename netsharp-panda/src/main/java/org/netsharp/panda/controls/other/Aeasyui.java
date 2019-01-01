package org.netsharp.panda.controls.other;

import org.netsharp.panda.annotation.HtmlAttr;


public class Aeasyui extends A
{
    @HtmlAttr(html="iconCls")
    public String iconCls;

    @Override
    public void initialize()
    {
        this.setStyle("easyui-linkbutton");

        super.initialize();
    }
}
