package org.netsharp.wx.pa.base;

import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.wx.pa.entity.NWeixinSubscriber;
import org.netsharp.wx.pa.entity.PublicAccount;

public interface IPublicAccountService extends IPersistableService<PublicAccount> {
	
    PublicAccount byOriginalId(String oid) ;
    PublicAccount byAppId(String appId) ;
    List<NWeixinSubscriber> querySubscribers(Long publicAccountId);
}
