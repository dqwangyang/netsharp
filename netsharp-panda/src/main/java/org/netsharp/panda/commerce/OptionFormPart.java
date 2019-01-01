package org.netsharp.panda.commerce;

import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.comunication.IHtmlWriter;


/**   
 * @ClassName:  OptionFormPart   
 * @Description:选项设置表单
 * @author: 韩伟
 * @date:   2017年9月18日 下午3:56:01   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class OptionFormPart extends FormPart{

	
	
	@Override
	protected void importJs(IHtmlWriter writer) {

		super.importJs(writer);
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.option.form.js"));
	}
}
