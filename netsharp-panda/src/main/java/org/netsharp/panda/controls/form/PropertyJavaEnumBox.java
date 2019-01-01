package org.netsharp.panda.controls.form;

import java.util.Map;

import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.Combobox;
import org.netsharp.panda.controls.input.SelectOption;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.JavaEnumManager;
import org.netsharp.util.StringManager;

public class PropertyJavaEnumBox implements IPropertyControl {
	
	public Control create(PForm form, PFormField formField, FormGroup group) {
		
		Mtable meta = MtableManager.getMtable(form.getEntityId());
		Column column = meta.findProperty(formField.getPropertyName());

		Combobox enumbox = new Combobox();
		{
	        if (formField.isFullColumn())
	        {
	        	enumbox.setStyle("width:100%;");
	        }else{
	        	
	        	enumbox.width = formField.getWidth();
			}

			if(!StringManager.isNullOrEmpty(formField.getTroikaTrigger())){
				
				enumbox.onChange = "function(newValue,oldValue){"+formField.getTroikaTrigger()+"}";
			}
			
			enumbox.collected = true;
			enumbox.setId(formField.getPropertyName());
			enumbox.required = formField.isRequired();
			enumbox.controlType = "JavaEnumBox";
			enumbox.defaultValue = formField.getDefaultValue();
			enumbox.editable = false;
		}

		Class<?> type = column.getType();
		if (!type.isEnum()) {
			String message = formField.getPropertyName() + "字段配置成枚举，但是类型为:" + type.getName();
			throw new PandaException(message);
		}
		Map<String, String> map = JavaEnumManager.enumAsMapList2(type);
		
		for (String key : map.keySet()) {
			String display = map.get(key);

			SelectOption option = new SelectOption();
			{
				option.optionValue = key;
				option.value = display;
			}

			enumbox.getControls().add(option);
		}

		return enumbox;
	}
}
