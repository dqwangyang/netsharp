package org.netsharp.panda.controls.form;

import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;

@HtmlNode(html="form")
public class Form extends Control
{
    @HtmlAttr(html="action")
    public String action;
    
    @HtmlAttr(html="method")
    public String method;
}

