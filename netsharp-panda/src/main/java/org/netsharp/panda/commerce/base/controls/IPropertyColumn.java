package org.netsharp.panda.commerce.base.controls;


import java.util.ArrayList;

import org.netsharp.panda.controls.datagrid.DataGridColumn;
import org.netsharp.panda.entity.PDatagridColumn;

public interface IPropertyColumn
{
    DataGridColumn create(PDatagridColumn dcolumn);
    ArrayList<String> getScripts();
}