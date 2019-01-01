package org.netsharp.panda.controls.tree;

import java.util.ArrayList;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;
import org.netsharp.util.StringManager;

@HtmlNode(html="ul")
public class Tree extends Control
{
    public String Service;
    public String EntityId;
    public String Filter;

    @DataOption(html="animate")
    public boolean IsAnimate;

    @DataOption(html="onLoadSuccess", isEvent=true)
    public String onLoadSuccess;
    
    @DataOption(html="onClick", isEvent=true)
    public String OnClick;

    @DataOption(html="onDblClick", isEvent=true)
    public String OnDblClick;

    @DataOption(html="onBeforeExpand", isEvent=true)
    public String OnBeforeExpand;

    @DataOption(html="onBeforeDrag", isEvent=true)
    public String onBeforeDrag;
    
    @DataOption(html="onStartDrag", isEvent=true)
    public String onStartDrag;
    
    @DataOption(html="onStopDrag", isEvent=true)
    public String onStopDrag;
    
    @DataOption(html="onDragEnter", isEvent=true)
    public String onDragEnter;
    
    @DataOption(html="onDragOver", isEvent=true)
    public String onDragOver;
    
    @DataOption(html="onDragLeave", isEvent=true)
    public String onDragLeave;

    @DataOption(html="onBeforeDrop", isEvent=true)
    public String onBeforeDrop;
    
    @DataOption(html="onDrop", isEvent=true)
    public String onDrop;
    
    @DataOption(html="lines")
    public boolean lines=true;
    
    @DataOption(html="dnd")
    public boolean dnd = true;
    
    @DataOption(html="url", mustWrite=true)
    public String Url;

    public ArrayList<TreeNode> nodes;
    
    @Override
    public void initialize()
    {
        this.setClassName("easyui-tree");

        this.IsAnimate = true;

        super.initialize();
    }

    

    @Override
    protected String renderProperties()
    {
        JTreeNodeSerializor j = new JTreeNodeSerializor();
        String json = j.Serialize(this);

        if (!StringManager.isNullOrEmpty(json))
        {
            json = j.SubJson(json);

            this.innerValues.put("data", json);
        }

        return super.renderProperties();
    }
}


