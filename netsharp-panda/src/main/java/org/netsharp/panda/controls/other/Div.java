package org.netsharp.panda.controls.other;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;

@HtmlNode(html="div",isValue=true)
public class Div extends Control
{
    @DataOption(html="href")
    public String href;

    @DataOption(html="iconCls")
    public String iconCls;

    @HtmlAttr(html="title")
    public String title;

    @HtmlAttr(html="onclick")
    public String onclick;

    public String value;
}