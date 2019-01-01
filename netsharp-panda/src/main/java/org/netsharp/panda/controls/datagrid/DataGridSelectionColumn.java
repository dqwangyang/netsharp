package org.netsharp.panda.controls.datagrid;

import org.netsharp.panda.annotation.DataOption;

public class DataGridSelectionColumn extends DataGridEditColumn
{
    @DataOption(html="checkbox")
    public boolean checkbox=false;

    @Override
    public void initialize()
    {
        this.checkbox = true;
        this.width =40;
        this.field = "ck";
        super.initialize();
    }
}
