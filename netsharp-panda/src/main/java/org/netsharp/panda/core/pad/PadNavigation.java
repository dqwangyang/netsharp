package org.netsharp.panda.core.pad;

import java.util.List;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.accordion.Accordion;
import org.netsharp.panda.controls.accordion.AccordionItem;
import org.netsharp.panda.plugin.entity.PAccordion;
import org.netsharp.plugin.core.AddInTree;

public class PadNavigation implements IPad {
	
	public static String AccordionPath = "panda/workbench/accordion";
	public Control create() {
		
		Accordion accordion = new Accordion();
		{
			accordion.Isfit = true;
			accordion.setIsBorder("false");
		}

		List<Object> items = AddInTree.buildItems(PAccordion.class, null,AccordionPath, null);
		for (Object obj : items) {
			AccordionItem item = (AccordionItem) obj;
			accordion.getControls().add(item);
		}

		return accordion;
	}

	public boolean condition() {
		return true;
	}
}
