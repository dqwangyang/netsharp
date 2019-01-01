package org.netsharp.panda.controls.query;

import java.util.Map;

import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.Combobox;
import org.netsharp.panda.controls.input.SelectOption;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.util.JavaEnumManager;
import org.netsharp.util.StringManager;

public class PropertyQueryJavaEnumBox implements IPropertyQueryControl {

	@Override
	public Control create(PQueryItem queryItem, Mtable meta) {

		Column column = meta.findProperty(queryItem.getPropertyName());
		Combobox control = new Combobox();
		{
			control.collected = true;
			control.required = queryItem.isRequired();
			control.setId(queryItem.getPropertyName().replace(".", "_"));
			control.controlType = "JavaEnumBoxQueryItem";
			control.setStyle("width:" + queryItem.getWidth() + "px;");
			control.multiple = true;
			control.editable = false;
			//此处比较麻烦。
			//control.onSelect = "function(record){if(record.value=='-1'){var values = $(this).combobox('getValues');var me = this;$(values).each(function(i,item){debugger;$(me).combobox('unselect',item);});$(this).combobox('setText','不限');}}";
			control.getInnerValues().put("query", "1");
		}

		Class<?> type = null;
		if (column != null) {

			type = column.getType();
		} else {

			String enumName = queryItem.getAppconfigCondition();
			try {
				type = Class.forName(enumName);
			} catch (ClassNotFoundException e) {

				throw new PandaException("没有配置自定义枚举");
			}
		}
		if (!type.isEnum()) {
			String message = queryItem.getPropertyName() + "字段配置成枚举，但是类型为:" + type.getName();
			throw new PandaException(message);
		}
		Map<String, String> map = JavaEnumManager.enumAsMapList2(type);
//		control.getControls().add(new SelectOption("-1", "不限", false));
		for (String key : map.keySet()) {

			String display = map.get(key);
			SelectOption option = new SelectOption();
			{
				option.optionValue = key.trim();
				option.value = display.trim();

				if(!StringManager.isNullOrEmpty(queryItem.getValue1()) && option.optionValue.equals(queryItem.getValue1())){
					
					option.selected = true;
				}
			}
			control.getControls().add(option);
		}
		return control;
	}
}
