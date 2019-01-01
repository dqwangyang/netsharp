package org.netsharp.wx.pa.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;
import org.netsharp.wx.pa.base.INWeixinSubscriberService;
import org.netsharp.wx.pa.entity.NWeixinSubscriber;

@Service
public class NWeixinSubscriberService extends PersistableService<NWeixinSubscriber> implements INWeixinSubscriberService {
	
	public NWeixinSubscriberService(){
		super();
		
		this.type=NWeixinSubscriber.class;
	}
}