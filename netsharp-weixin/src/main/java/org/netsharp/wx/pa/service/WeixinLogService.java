package org.netsharp.wx.pa.service;

import org.netsharp.communication.Service;
import org.netsharp.core.EntityState;
import org.netsharp.core.NetsharpException;
import org.netsharp.service.PersistableService;
import org.netsharp.session.SessionManager;
import org.netsharp.wx.pa.base.IWeixinLogService;
import org.netsharp.wx.pa.entity.WeixinLog;

@Service
public class WeixinLogService extends PersistableService<WeixinLog> implements IWeixinLogService {

	public WeixinLogService(){
		super();
		this.type=WeixinLog.class;
	}

	@Override
    public WeixinLog save(WeixinLog entity) {


        if (entity.getCreatorId() == null || entity.getCreatorId().intValue() <= 0) {
            entity.setCreatorId(1L);
            entity.setCreator("微信");
        }

        if (entity.getUpdatorId() == null || entity.getUpdatorId().intValue() <= 0) {
            entity.setUpdatorId(1L);
            entity.setUpdator("微信");
        }

        return  super.save(entity);
    }


}