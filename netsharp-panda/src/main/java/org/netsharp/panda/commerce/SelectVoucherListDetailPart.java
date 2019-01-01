/**  
* @Title: SelectVoucherListDetailPart.java 
* @Package org.netsharp.panda.commerce 
* @Description: TODO
* @author hanwei
* @date 2015-5-20 下午6:07:11 
* @version V1.0  
*/ 

package org.netsharp.panda.commerce;

import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.Pagination;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;


/** 
 * @ClassName: SelectVoucherListDetailPart 
 * @Description: TODO
 * @author hanwei 
 * @date 2015-5-20 下午6:07:11 
 *  
 */

public class SelectVoucherListDetailPart extends ListPart{
	public SelectVoucherListDetailPart(){
		super();
	}
	
	public Pagination doQuery(Oql oql) {

		Class<?> serviceType = null;
		if (StringManager.isNullOrEmpty(this.context.getService())) {
			serviceType = IPersistableService.class;
		} else {
			serviceType = ReflectManager.getType(this.context.getService());
			if (serviceType == null) {
				serviceType = IPersistableService.class;
			}
		}
		
		if(StringManager.isNullOrEmpty(oql.filter)){
			oql.filter="1=2";
		}

		IPersistableService<?> service = (IPersistableService<?>) ServiceFactory.create(serviceType);
		Pagination pag = service.queryPaging(oql);

		return pag;
	}
	
	@Override
	protected void importJs(IHtmlWriter writer) {
		super.importJs(writer);
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.select.voucher.detail.part.js"));
	}
}