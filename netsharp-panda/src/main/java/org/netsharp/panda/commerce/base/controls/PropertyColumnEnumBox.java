package org.netsharp.panda.commerce.base.controls;

import org.netsharp.panda.controls.datagrid.DataGridColumn;
import org.netsharp.panda.entity.PDatagridColumn;

public class PropertyColumnEnumBox extends PropertyColumnBase
{
    @Override
    public DataGridColumn create(PDatagridColumn dcolumn)
    {
//        Mtable meta = MtableManager.getMtable(dcolumn.getDatagrid().getEntityId());
//
//        Column col =  meta.FindProperty(dcolumn.getPropertyName());
//        String dicName = col.getPropertyName() + "Dictionary";
//
//        EnumBoxColumn enumBoxColumn = new EnumBoxColumn();
//        {
//        	enumBoxColumn.ColumnType = "EnumBox";
//        	enumBoxColumn.EnumItemName = "";
//        	enumBoxColumn.Formatter = "function(value,rowData,rowIndex){return " + dicName + ".byKey(value);}";
//        	enumBoxColumn.EnumItemId = "";
//        	enumBoxColumn.ValueField = "id";
//        	enumBoxColumn.TextField = "text";
//        	enumBoxColumn.Url = "tt.enum?Id=" + col.getIdEnum();
//        	enumBoxColumn.Required = dcolumn.isRequired();
//        	enumBoxColumn.Editable=false;
//        };
//
//        this.Render(enumBoxColumn, dcolumn);
//
//        EnumObject enumObject = EnumManager.getEnum(col.getIdEnum());
//
//        this.getScripts().add("        var " + dicName + " = new System.Dictionary();");
//
//        for (EnumItem item : enumObject.getItems())
//        {
//            this.getScripts().add("        " + dicName + ".add('" + item.getId() + "','" + item.getName() + "');");
//        }
//
//        return enumBoxColumn;
    	
    	return null;
    }
}
