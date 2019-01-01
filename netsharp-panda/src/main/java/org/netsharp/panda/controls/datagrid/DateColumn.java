package org.netsharp.panda.controls.datagrid;

public class DateColumn extends DataGridEditColumn
{
    @Override
  public void initialize()
  {
      this.columnType = "Date";
      this.type = "datebox";
      super.initialize();
  }
}