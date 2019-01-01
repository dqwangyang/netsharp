package org.netsharp.panda.controls.table;

import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;

@HtmlNode(html="th",isValue=true)
public class TH extends Control
{
    @HtmlAttr(html="colspan")
    public Integer colspan;
    
    @HtmlAttr(html="rowspan")
    public Integer rowspan;

    @HtmlAttr(html="align")
    public String align;
    
    public String value;
}
