package org.netsharp.panda.commerce;

import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.comunication.IHtmlWriter;

public class RollListPart extends ListPart {

	@Override
	protected void importJs(IHtmlWriter writer) {
		super.importJs(writer);
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.roll.list.part.js"));
	}
}
