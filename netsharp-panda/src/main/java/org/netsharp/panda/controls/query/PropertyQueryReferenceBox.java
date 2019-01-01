package org.netsharp.panda.controls.query;

import java.util.ArrayList;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Mtable;
import org.netsharp.core.TableRelation;
import org.netsharp.panda.base.IPReferenceService;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.ReferenceBox;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PReference;
import org.netsharp.util.StringManager;

public class PropertyQueryReferenceBox implements IPropertyQueryControl{

	@Override
	public Control create(PQueryItem queryItem, Mtable meta) {
		
		String[] paths = queryItem.getPropertyName().split("\\.");
		ArrayList<String> ss = new ArrayList<String>();
		for (String p : paths) {
			ss.add(p);
		}
		ss.remove(ss.size() - 1);

		String relationName = StringManager.join(".", ss);
		TableRelation relation = meta.findRelation(relationName);

		String foreignkey = "";

		if (ss.size() > 1) {
			for (int i = 0; i < ss.size() - 1; i++) {
				foreignkey += ss.get(i) + ".";
			}

			foreignkey = foreignkey + relation.getForeignProperty();
		} else {
			foreignkey = relation.getForeignProperty();
		}

		PReference pReference = queryItem.getReference();
		if(pReference == null){
			
			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			pReference = referenceService.byCode(queryItem.getReferenceCode());
		}
		
		ReferenceBox control = new ReferenceBox();
		{
			control.collected = true;
			if(!StringManager.isNullOrEmpty(queryItem.getRefFilter())){
				control.filter = queryItem.getRefFilter().replaceAll("'", "----").replace("=", "____");//RestReferenceService.processRequest
			}
			control.multiple = queryItem.isMultiple();
			control.width = queryItem.getWidth();
			control.required = queryItem.isRequired();
			control.controlType = "ReferenceBoxQueryItem";
			control.foreignkey = foreignkey;
			control.setId(queryItem.getPropertyName().replaceAll("\\.", "_"));
			control.setName(queryItem.getPropertyName().replaceAll("\\.", "_"));
			control.reference = pReference;
			control.idField = relation.getToTable().getKeyColumn().getPropertyName();
			control.getInnerValues().put("query", "1");
		}

		return control;
	}

}
