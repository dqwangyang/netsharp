package org.netsharp.wx.pa.service;

import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.entity.CatEntity;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.wx.pa.MenuManage;
import org.netsharp.wx.pa.base.INMenuItemService;
import org.netsharp.wx.pa.entity.NMenuItem;
import org.netsharp.wx.sdk.mp.WeixinException;

@Service
public class NMenuItemService extends PersistableService<NMenuItem> implements INMenuItemService {

	public NMenuItemService(){
		super();
		this.type=NMenuItem.class;
	}
	
	@Override
	public NMenuItem save(NMenuItem entity) {
		
		if(!StringManager.isNullOrEmpty(entity.getReply()) &&! StringManager.isNullOrEmpty(entity.getUrl())){
			throw new WeixinException("关键字回复和URL不能同时配");
		}
		
		if(! StringManager.isNullOrEmpty(entity.getUrl()) && entity.getOauthType()==null){
			throw new WeixinException("菜单配置URL时必须设置OAuthType字段");
		}
		
		entity = super.save(entity);

		return entity;
	}
	
	public void generate(String originalId){
		
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("publicAccount.originalId='"+originalId+"'");
		}
		
		List<NMenuItem> list = this.pm.queryList(oql);
		
		List<NMenuItem> tops = CatEntity.listToTree(list);
		
		MenuManage m = new MenuManage();

		m.create(originalId, tops);
	}
}