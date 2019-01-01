package org.netsharp.panda.controls.datagrid;

public class PasswordTextboxColumn extends DataGridEditColumn
{
    @Override
   public void initialize()
   {
       this.columnType = "PasswordTextbox";
       super.initialize();
   }
}
