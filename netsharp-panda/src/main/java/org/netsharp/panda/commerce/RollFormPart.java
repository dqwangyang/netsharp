package org.netsharp.panda.commerce;

import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.comunication.IHtmlWriter;

public class RollFormPart extends FormPart{

	@Override
	protected void importJs(IHtmlWriter writer) {
		super.importJs(writer);
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.roll.form.part.js"));
	}
}
