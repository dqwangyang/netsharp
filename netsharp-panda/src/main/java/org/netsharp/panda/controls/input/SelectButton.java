package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.HtmlAttr;


public class SelectButton extends Input
{
    @HtmlAttr(html="fileTypeExts")
    public String fileTypeExts;

    /// <summary>
    /// 回填控件ID
    /// </summary>
    @HtmlAttr(html="backFillControl")
    public String backFillControl;
}