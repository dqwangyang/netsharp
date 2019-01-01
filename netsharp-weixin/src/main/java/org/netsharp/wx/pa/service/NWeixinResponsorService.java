package org.netsharp.wx.pa.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;
import org.netsharp.wx.pa.base.INWeixinResponsorService;
import org.netsharp.wx.pa.entity.NWeixinResponsor;

@Service
public class NWeixinResponsorService extends PersistableService<NWeixinResponsor> implements INWeixinResponsorService {
	public NWeixinResponsorService(){
		super();
		this.type=NWeixinResponsor.class;
	}
}
