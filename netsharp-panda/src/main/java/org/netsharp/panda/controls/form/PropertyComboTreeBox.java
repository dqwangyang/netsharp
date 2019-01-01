package org.netsharp.panda.controls.form;

import java.util.ArrayList;

import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.TableRelation;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.ComboTreeBox;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.StringManager;

public class PropertyComboTreeBox implements IPropertyControl {

	public Control create(PForm form, PFormField formField, FormGroup group) {

		String[] paths2 = formField.getPropertyName().split("\\.");
		ArrayList<String> ss2 = new ArrayList<String>();
		for (String p : paths2) {
			ss2.add(p);
		}
		ss2.remove(ss2.size() - 1);

		Mtable meta = MtableManager.getMtable(form.getEntityId());
		TableRelation relation2 = meta.findRelation(StringManager.join(".", ss2));

		String url = "/panda/rest/comboxtree?id2=" + formField.getReference().getId() + "&parentId=";
		ComboTreeBox control = new ComboTreeBox();
		{
			control.collected = true;
			control.foreignId = ss2.get(0) + "." + relation2.getToTable().getKeyColumn().getPropertyName();
			control.foreignkey = relation2.getForeignProperty();
			control.foreignName = formField.getPropertyName();
			control.setId(formField.getPropertyName().replaceAll("\\.", "_"));
			control.setName(formField.getPropertyName().replaceAll("\\.", "_"));
			control.url = url;
			control.required = formField.isRequired();
			control.width = formField.getWidth();
			control.innerValues.put("lines", "true");
			control.innerValues.put("animate", "true");
			
			// 处理异步加载
			control.onBeforeExpand = "function (node, param) {$(this).tree('options').url = '"+url+"'+node.id;}";
			if (!StringManager.isNullOrEmpty(formField.getRefFilter())) {
				String filter = formField.getRefFilter().replaceAll("'", "----").replace("=", "____");
				control.url += formField.getId() + "&filter=" + filter;
			}
		}

		if (formField.isFullColumn()) {
			control.setStyle("width:100%;");
		} else {

			control.width = formField.getWidth();
		}
		return control;
	}
}
