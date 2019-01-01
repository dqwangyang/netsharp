package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.HtmlAttr;

public class UploadFile extends Input
{
    @HtmlAttr(html="fileTypeExts")
    public String fileTypeExts;

    /// <summary>
    /// 回填控件ID
    /// </summary>
    @HtmlAttr(html="backFillControl")
    public String backFillControl;

    @Override
    public void initialize()
    {
        super.initialize();
        this.className = "nsInput";
        this.type = "file";
    }
}
