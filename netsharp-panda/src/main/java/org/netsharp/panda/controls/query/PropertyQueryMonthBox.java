package org.netsharp.panda.controls.query;

import java.util.Calendar;

import org.netsharp.core.Mtable;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.Combobox;
import org.netsharp.panda.controls.input.SelectOption;
import org.netsharp.panda.entity.PQueryItem;

public class PropertyQueryMonthBox implements IPropertyQueryControl {

	@Override
	public Control create(PQueryItem queryItem, Mtable meta) {

		Combobox control = new Combobox();
		{
			control.collected = true;
			control.required = queryItem.isRequired();
			control.setId(queryItem.getPropertyName().replace(".", "_"));
			control.controlType = "MonthBoxQueryItem";
			control.setStyle("width:" + queryItem.getWidth() + "px;");
			control.multiple = false;
			control.editable = false;
			control.getInnerValues().put("query", "1");
		}

		Calendar now = Calendar.getInstance();
		int month = now.get(Calendar.MONTH)+1;

		for (int i = 1; i <= 12; i++) {

			SelectOption option = new SelectOption();
			{
				if (i == month) {

					option.selected = true;
				}
				option.optionValue = String.valueOf(i);
				option.value = i + "月";
			}
			control.getControls().add(option);
		}

		return control;
	}

}
