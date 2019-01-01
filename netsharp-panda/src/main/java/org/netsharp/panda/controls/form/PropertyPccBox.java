package org.netsharp.panda.controls.form;

import java.util.ArrayList;

import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.TableRelation;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.Combobox;
import org.netsharp.panda.controls.input.SelectOption;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.StringManager;

public class PropertyPccBox implements IPropertyControl{

	@Override
	public Control create(PForm form, PFormField formField, FormGroup group) {

		String[] paths = formField.getPropertyName().split("\\.");
		ArrayList<String> ss = new ArrayList<String>();
		for (String p : paths) {
			ss.add(p);
		}
		ss.remove(ss.size() - 1);

		Mtable meta = MtableManager.getMtable(form.getEntityId());
		TableRelation relation = meta.findRelation(StringManager.join(".", ss));
		
		Combobox control = new Combobox();
		{
	        control.collected = true;
	        control.setId(formField.getPropertyName().replaceAll("\\.", "_"));
			control.setName(formField.getPropertyName().replaceAll("\\.", "_"));
	        control.required = formField.isRequired();
	        control.controlType = "PccBox";
	        control.defaultValue = formField.getDefaultValue();
	        control.valueField = "id";
	        control.textField = "name";
	        control.getDataOptions().add(formField.getDataOptions());
	        //control.getDataOptions().add("foreignId:'"+relation.getForeignProperty()+"'");
	        control.getDataOptions().add("foreignkey:'"+relation.getForeignProperty()+"'");
	        control.getDataOptions().add("foreignName:'"+formField.getPropertyName()+"'");;
	        if (formField.isFullColumn())
	        {
	        	control.setStyle("width:100%;");
	        }else{
	        	
	        	control.width = formField.getWidth();
			}
	        
			SelectOption option = new SelectOption();
			{
				option.optionValue = "";
				option.value = "";
			}

			control.getControls().add(option);

		}
		return control;
	}
}
