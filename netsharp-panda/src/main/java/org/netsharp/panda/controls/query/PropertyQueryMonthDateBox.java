package org.netsharp.panda.controls.query;

import org.netsharp.core.Mtable;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.entity.PQueryItem;

public class PropertyQueryMonthDateBox  implements IPropertyQueryControl{

	@Override
	public Control create(PQueryItem queryItem, Mtable meta) {

		MonthboxQueryControl control = new MonthboxQueryControl();
		control.required = queryItem.isRequired();
		return control;
	}

}
