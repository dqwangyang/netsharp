package org.netsharp.panda.controls.treegrid;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.controls.datagrid.DataGrid;

public class TreeGrid extends DataGrid
{
    @DataOption(html="idField")
    public String idField ="id";

    @DataOption(html="treeField")
    public String treeField = "name";

    @DataOption(html="animate")
    public boolean animate;

	@DataOption(html = "onBeforeExpand", isEvent = true)
	public String onBeforeExpand;
    
    @Override
    public void initialize()
    {
        super.initialize();
        
        this.pagination = false;
        this.animate = true;
        this.setClassName( "easyui-treegrid");
    }
}