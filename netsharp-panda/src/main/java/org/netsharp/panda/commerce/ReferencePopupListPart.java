package org.netsharp.panda.commerce;

import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.comunication.IHtmlWriter;


public class ReferencePopupListPart  extends ListPart{
	
	@Override
	protected void importJs(IHtmlWriter writer) {
		super.importJs(writer);
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.reference.popup.list.part.js"));
	}
}
