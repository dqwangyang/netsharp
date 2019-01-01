package org.netsharp.panda.commerce.base.controls;

import java.util.ArrayList;

import org.netsharp.panda.commerce.base.DatagridPartBase;
import org.netsharp.panda.controls.datagrid.DataGridColumn;
import org.netsharp.panda.entity.PDatagridColumn;

public abstract class PropertyColumnBase implements IPropertyColumn
{
	protected ArrayList<String> scripts;
	
	public ArrayList<String> getScripts(){
		if(scripts==null){
			scripts=new ArrayList<String>();
		}
		return scripts;
	}

    public abstract DataGridColumn create(PDatagridColumn dcolumn);

    protected void render(DataGridColumn column, PDatagridColumn dcolumn)
    {
        DatagridPartBase.render(column, dcolumn);
    }
}
