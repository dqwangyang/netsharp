package org.netsharp.panda.controls.form;

import java.lang.reflect.Field;
import java.util.Map;

import org.netsharp.core.annotations.Subs;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.CheckboxGroup;
import org.netsharp.panda.controls.input.SelectOption;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.JavaEnumManager;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

/**
 * @author hw
 * 表单多选组合
 */
public class PropertyCheckboxGroup implements IPropertyControl{

	@Override
	public Control create(PForm form, PFormField formField, FormGroup group) {
		
		
		CheckboxGroup radioboxGroup = new CheckboxGroup();
		{
			radioboxGroup.collected = true;
			radioboxGroup.setId(formField.getPropertyName());
			radioboxGroup.required = formField.isRequired();
			radioboxGroup.value = formField.getDefaultValue();
			
			if(!StringManager.isNullOrEmpty(formField.getDataOptions())){

				radioboxGroup.dataOptions.add(formField.getDataOptions());
			}
		}

		try {
			
			Class<?> type = Class.forName(form.getEntityId());
			String enumFieldName = formField.getConvertor();
			Field field = ReflectManager.getField(type, formField.getPropertyName());
			
			Subs subs = field.getAnnotation(Subs.class);
			Class<?> subType = subs.subType();
			radioboxGroup.foreignKey = subs.foreignKey();
			radioboxGroup.enumFieldName = enumFieldName;
			//获取明细表的字段
			Field enumField = ReflectManager.getField(subType, enumFieldName);
			if (!enumField.getType().isEnum()) {
				String message = enumFieldName + "字段配置成枚举，但是类型为:" + enumField.getName();
				throw new PandaException(message);
			}
			Map<String, String> map = JavaEnumManager.enumAsMapList2(enumField.getType());
			for (String key : map.keySet()) {
				
				String display = map.get(key);
				SelectOption option = new SelectOption();
				{
					option.optionValue = key;
					option.value = display;
				}
				radioboxGroup.getControls().add(option);
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			throw new PandaException(e.getMessage());
		}

		
		return radioboxGroup;
	}

}
