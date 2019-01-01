package org.netsharp.panda.controls.query;

import org.netsharp.core.ITable;
import org.netsharp.panda.controls.datagrid.DataGrid;
import org.netsharp.panda.controls.other.Div;
import org.netsharp.panda.entity.PDatagrid;

public class QuerSolutionPanel extends Div
{
    public ITable<?> queryProjects;

    public DataGrid datagrid = null;

    protected PDatagrid datagridProject;

    @Override
    public void initialize()
    {
        super.initialize();
    }
}
