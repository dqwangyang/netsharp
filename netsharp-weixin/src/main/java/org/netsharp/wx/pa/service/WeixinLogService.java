package org.netsharp.wx.pa.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;
import org.netsharp.wx.pa.base.IWeixinLogService;
import org.netsharp.wx.pa.entity.WeixinLog;

@Service
public class WeixinLogService extends PersistableService<WeixinLog> implements IWeixinLogService {

	public WeixinLogService(){
		super();
		this.type=WeixinLog.class;
	}
	
}