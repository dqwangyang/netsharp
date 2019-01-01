package org.netsharp.wx.pa.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.TreegridPart;
import org.netsharp.wx.pa.base.INMenuItemService;
import org.netsharp.wx.pa.entity.NMenuItem;
import org.netsharp.wx.sdk.mp.WeixinException;

public class MenuItemTreegridPart extends TreegridPart {
	
	public void generate(Integer publicAccountId){
		
		INMenuItemService menuService = ServiceFactory.create(INMenuItemService.class);
		
		Oql oql = new Oql();
		{
			oql.setType(NMenuItem.class);
			oql.setSelects("*,publicAccount.*");
		}
		
		NMenuItem item = menuService.queryFirst(oql);
		
		if(item.getPublicAccount()==null){
			throw new WeixinException(item.getName()+"必须配置公众号！");
		}
		
		String originalId = item.getPublicAccount().getOriginalId();
		
		menuService.generate(originalId);
	}
}
