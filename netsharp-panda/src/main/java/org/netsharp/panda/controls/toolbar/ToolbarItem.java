package org.netsharp.panda.controls.toolbar;

import java.util.ArrayList;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;
import org.netsharp.util.StringManager;

@HtmlNode(html="a",isValue=true)
public class ToolbarItem extends Control
{
    @HtmlAttr(html="href")
    public String Href;

    @HtmlAttr(html="iconCls")
    public String Icon;

    @HtmlAttr(html="onclick")
    public String OnClick;

    @HtmlAttr(html="plain")
    public boolean Plain = true;

    @DataOption(html="menu")
    public String Menu;

    @HtmlAttr(html="title")
    public String Title;
    
    public String value;

    /// <summary>
    /// 是否有子菜单
    /// </summary>
    public boolean IsPopup = false;

    /// <summary>
    /// 是否分割
    /// </summary>
    public boolean IsSplitter;

    @Override
    public void initialize()
    {
        if (StringManager.isNullOrEmpty(this.getClassName()))
        {
            if(this.IsSplitter)
            {
                this.setClassName("menu-sep");//menu-sep
            }
            else if (IsPopup)
            {
                this.setClassName("easyui-menubutton");
            }
            else
            {
                this.setClassName("easyui-linkbutton");
            }
        }

        super.initialize();
    }

    //js三驾马车用来搜集所有的菜单
    void getIds(ArrayList<String> ids)
    {
        ids.add("\"" + this.getCode() + "\"");

        for (Control control : this.getControls())
        {
        	ToolbarItem item=(ToolbarItem)control;
            item.getIds(ids);
        }
    }
    
    //js三驾马车用来搜集所有的菜单
    void getIds(ArrayList<String> ids,String prefix)
    {
        ids.add("\"" + prefix + this.getCode() + "\"");
        for (Control control : this.getControls())
        {
        	ToolbarItem item=(ToolbarItem)control;
            item.getIds(ids);
        }
    }
}


