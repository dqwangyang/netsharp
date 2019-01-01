package org.netsharp.panda.controls.table;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;

@HtmlNode(html="thead")
public class Thead extends Control
{
    @DataOption(html="frozen")
    public boolean frozen;
}
