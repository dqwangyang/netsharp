package org.netsharp.panda.commerce.base.controls;

import org.netsharp.panda.controls.datagrid.DataGridColumn;
import org.netsharp.panda.controls.datagrid.DecimalBoxColumn;
import org.netsharp.panda.entity.PDatagridColumn;

public class PropertyColumnDecimalBox extends PropertyColumnBase {
	@Override
	public DataGridColumn create(PDatagridColumn dcolumn) {
		DecimalBoxColumn numberBoxColumn = new DecimalBoxColumn();
		{
			numberBoxColumn.required = dcolumn.isRequired();

			if (dcolumn.getPrecision() != null) {
				numberBoxColumn.precision = dcolumn.getPrecision();
			}
		};

		this.render(numberBoxColumn, dcolumn);

		return numberBoxColumn;
	}
}
