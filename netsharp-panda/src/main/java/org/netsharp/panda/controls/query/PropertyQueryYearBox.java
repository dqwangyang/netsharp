package org.netsharp.panda.controls.query;

import java.util.Calendar;

import org.netsharp.core.Mtable;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.Combobox;
import org.netsharp.panda.controls.input.SelectOption;
import org.netsharp.panda.entity.PQueryItem;

public class PropertyQueryYearBox implements IPropertyQueryControl{

	@Override
	public Control create(PQueryItem queryItem, Mtable meta) {

		Combobox control = new Combobox();
		{
			control.collected = true;
			control.required = queryItem.isRequired();
			control.setId(queryItem.getPropertyName().replace(".", "_"));
			control.controlType = "YearBoxQueryItem";
			control.setStyle("width:" + queryItem.getWidth() + "px;");
			control.multiple = false;
			control.editable = false;
			control.getInnerValues().put("query", "1");
		}

		Calendar now = Calendar.getInstance();  
		int year = now.get(Calendar.YEAR);
		int startYear = year - 10;
		for (int i = year; i >= startYear; i--) {

			SelectOption option = new SelectOption();
			{
				option.optionValue = String.valueOf(i);
				option.value = i + "年";
			}
			control.getControls().add(option);
		}

		return control;
	}

}
