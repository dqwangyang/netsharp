/**  
* @Title: SelectVoucherListPart.java 
* @Package org.netsharp.panda.commerce 
* @Description: TODO
* @author hanwei
* @date 2015-5-20 下午5:45:35 
* @version V1.0  
*/ 

package org.netsharp.panda.commerce;

import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.comunication.IHtmlWriter;


/** 
 * @ClassName: SelectVoucherListPart 
 * @Description: TODO
 * @author hanwei 
 * @date 2015-5-20 下午5:45:35 
 *  
 */

public class SelectVoucherListPart extends ListPart{
	
	public SelectVoucherListPart(){
		super();
	}
	
	@Override
	protected void importJs(IHtmlWriter writer) {
		super.importJs(writer);
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.select.voucher.part.js"));
	}
}
