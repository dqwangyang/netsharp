package org.netsharp.panda.controls.other;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.util.StringManager;

@HtmlNode(html="a", isValue=true)
public class Linkbutton extends HyperLink
{
    @DataOption(html="plain")
    public boolean plain;

    @DataOption(html="iconCls")
    public String iconCls;

    @Override
    public void initialize()
    {
    	if(StringManager.isNullOrEmpty(this.getClassName())){

            this.setClassName("easyui-linkbutton");
    	}
    }

}