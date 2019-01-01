package org.netsharp.wx.ea.service;

import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.wx.ea.base.IWxeaAppService;
import org.netsharp.wx.ea.entity.WxeaApp;

@Service
public class WxeaAppService extends PersistableService<WxeaApp> implements IWxeaAppService {

	public WxeaAppService() {
		super();
		this.type = WxeaApp.class;
	}

	@Override
	public WxeaApp save(WxeaApp entity) {

		super.save(entity);
		// 验证重复编码
		this.byCode(entity.getCode());
		return entity;
	}

	@Override
	public WxeaApp byCode(String code) {
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("code='" + code + "'");
		}
		List<WxeaApp> ls = this.pm.queryList(oql);
		
		if (ls.size() == 1) {
			return ls.get(0);
		}

		return null;

	}

}
