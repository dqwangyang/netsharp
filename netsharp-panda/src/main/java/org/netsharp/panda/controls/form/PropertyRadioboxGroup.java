package org.netsharp.panda.controls.form;

import java.lang.reflect.Field;
import java.util.Map;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.RadioboxGroup;
import org.netsharp.panda.controls.input.SelectOption;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.JavaEnumManager;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

public class PropertyRadioboxGroup implements IPropertyControl{

	@Override
	public Control create(PForm form, PFormField formField, FormGroup group) {

		//visibility: hidden;
		RadioboxGroup radioboxGroup = new RadioboxGroup();
		{
			radioboxGroup.collected = true;
			radioboxGroup.setId(formField.getPropertyName());
			radioboxGroup.required = formField.isRequired();
			radioboxGroup.value = formField.getDefaultValue();
			if(!StringManager.isNullOrEmpty(formField.getDataOptions())){

				radioboxGroup.dataOptions.add(formField.getDataOptions());
			}
			if(!StringManager.isNullOrEmpty(formField.getTroikaTrigger())){
				
				radioboxGroup.onChange = "function(newValue, oldValue){"+formField.getTroikaTrigger()+"}";
			}
		}
		
		Object object = ReflectManager.newInstance(form.getResourceNode().getEntityId());
		Field field = ReflectManager.getField(object.getClass(), formField.getPropertyName());
		
		Class<?> type = field.getType();
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

			radioboxGroup.getControls().add(option);
		}

		return radioboxGroup;
	}

}
