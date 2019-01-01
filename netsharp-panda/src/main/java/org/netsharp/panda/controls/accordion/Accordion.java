package org.netsharp.panda.controls.accordion;

import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.controls.other.Div;

public class Accordion extends Div
{
    @HtmlAttr(html="fit")
    public boolean Isfit;

    @Override
    public void initialize()
    {
        this.setClassName("easyui-accordion");

        super.initialize();
    }
}
