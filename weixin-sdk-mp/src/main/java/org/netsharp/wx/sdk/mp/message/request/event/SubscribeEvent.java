package org.netsharp.wx.sdk.mp.message.request.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.util.StringManager;
import org.netsharp.wx.sdk.mp.message.EventType;

import java.util.regex.Pattern;

public class SubscribeEvent extends EventRequest {
    protected static Log logger = LogFactory.getLog(SubscribeEvent.class);
    private String ticket;

    public final String getTicket() {
        return ticket;
    }

    public final void setTicket(String value) {
        ticket = value;
    }

    public final Long getSceneId() {
        Long    sceneId  = 0L;
        String eventKey = this.getEventKey();
        try {

            String scenceString = null;

            if (this.getEvent().toLowerCase().equals(EventType.Subscribe)) {
                if (!StringManager.isNullOrEmpty(eventKey) && eventKey.indexOf('_') > 0) {
                    String[] param = eventKey.split("[_]", -1);
                    scenceString = param[1];
                }
            } else if (this.getEvent().toLowerCase().equals(EventType.Scan)) {
                scenceString = eventKey;
            } else {
                return sceneId;
            }

            if (!StringManager.isNullOrEmpty(scenceString)) {
                Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
                if(pattern.matcher(scenceString).matches()){
                    sceneId = Long.valueOf(scenceString);
                }
            }
        } catch (Exception ex) {
            logger.error("微信关注异常消息：" + this.toString());
            logger.error("", ex);
        }
        return sceneId;
    }
    //扫码字符串参数 | 分隔  手机号|业务id|来源标识  如  18600873737|a10000|SB  来源标识用来判断执行处理器
    public final String getSceneStr() {
        String eventKey = this.getEventKey();
        String scenceString = null;
        try {
            if (this.getEvent().toLowerCase().equals(EventType.Subscribe)) {
                if (!StringManager.isNullOrEmpty(eventKey) && eventKey.indexOf('_') > 0) {
                    String[] param = eventKey.split("[_]", -1);
                    scenceString = param[1];
                }
            }else if (this.getEvent().toLowerCase().equals(EventType.Scan)) {
                scenceString = eventKey;
            }
        } catch (Exception ex) {
            logger.error("微信关注异常消息获取sceneStr异常：" + this.toString());
            logger.error("", ex);
        }
        return scenceString;
    }
}