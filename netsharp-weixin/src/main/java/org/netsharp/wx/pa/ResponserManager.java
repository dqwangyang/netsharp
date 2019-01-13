package org.netsharp.wx.pa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.NetsharpException;
import org.netsharp.core.Oql;
import org.netsharp.util.ReflectManager;
import org.netsharp.wx.pa.base.INWeixinResponsorService;
import org.netsharp.wx.pa.base.IWeixinLogService;
import org.netsharp.wx.pa.entity.NWeixinResponsor;
import org.netsharp.wx.pa.entity.WeixinLog;
import org.netsharp.wx.pa.response.PublicAccountManager;
import org.netsharp.wx.sdk.mp.message.RequestMessage;
import org.netsharp.wx.sdk.mp.message.request.TextRequest;
import org.netsharp.wx.sdk.mp.message.request.event.EventRequest;
import org.netsharp.wx.sdk.mp.message.request.event.LocationEvent;

public class ResponserManager {
    private static Log                    logger            = LogFactory.getLog(ResponserManager.class.getSimpleName());
    private static List<IWeixinResponsor> weixinResponseors = new ArrayList<IWeixinResponsor>();

    private static ReentrantLock lock = new ReentrantLock();

    public static List<IWeixinResponsor> getResponseors()  {
        if (weixinResponseors.size() == 0) {
            reload();
        }

        return weixinResponseors;
    }

    public static void reload()  {
        try {
            lock.lock();
            if (weixinResponseors.size() > 0) {
                return;
            }

            INWeixinResponsorService service = ServiceFactory.create(INWeixinResponsorService.class);
            Oql oql = new Oql();
            {
                oql.setType(NWeixinResponsor.class);
                oql.setSelects("NWeixinResponsor.{Id,Code,JavaType,publicAccountId}");
                oql.setFilter("Disabled=0 OR Disabled IS NULL");
                oql.setOrderby("Seq");
            }

            List<NWeixinResponsor> set = service.queryList(oql);

            for (NWeixinResponsor nresponsor : set) {
            	if(nresponsor.getDisabled()){
            		continue;
            	}
                String javaType = nresponsor.getJavaType();

                IWeixinResponsor responser = (IWeixinResponsor) ReflectManager.newInstance(javaType);
                if(responser==null){
                	logger.warn("未能创建IWeixinResponsor:"+javaType);
                }else{
                	weixinResponseors.add(responser);
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static void addlog(RequestMessage request, boolean isHit) {
        if (request instanceof LocationEvent) {
            return;
        }

        String    originalId = request.getToUserName();
        Long       pid        = PublicAccountManager.getInstance().get(originalId).getAccount().getId();
        WeixinLog log        = new WeixinLog();
        {
            log.setMsgId(request.getMsgId());
            log.setMsgType(request.getMsgType());
            log.setHit(isHit);
            log.setFromUserName(request.getFromUserName());
            log.setToUserName(request.getToUserName());
            log.setPublicAccountId(pid);

            log.toNew();
        }

        if (request instanceof TextRequest) {
            TextRequest text = (TextRequest) request;
            {
                log.setKeywords(text.getContent());
            }
        } else if (request instanceof EventRequest) {
            EventRequest text = (EventRequest) request;
            {
                log.setKeywords(text.getEvent());
                log.setMemoto(text.getEventKey());
            }
        }

        IWeixinLogService service = ServiceFactory.create(IWeixinLogService.class);
        try {
            if (log.getKeywords() != null && log.getKeywords().length() > 50) {
                log.setKeywords(log.getKeywords().substring(0, 50));
            }
            service.save(log);
        } catch (NetsharpException e) {
            logger.error("微信请求添加日志错误", e);
        }
    }
}