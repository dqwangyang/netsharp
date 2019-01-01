package org.netsharp.wx.sdk.mp.message;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.core.convertor.TypeConvertorFactory;
import org.netsharp.util.ReflectManager;
import org.netsharp.wx.sdk.mp.WeixinException;
import org.netsharp.wx.sdk.mp.message.request.ImageRequest;
import org.netsharp.wx.sdk.mp.message.request.LinkRequest;
import org.netsharp.wx.sdk.mp.message.request.LocationRequest;
import org.netsharp.wx.sdk.mp.message.request.TextRequest;
import org.netsharp.wx.sdk.mp.message.request.VideoRequest;
import org.netsharp.wx.sdk.mp.message.request.VoiceRequest;
import org.netsharp.wx.sdk.mp.message.request.event.EventRequest;
import org.netsharp.wx.sdk.mp.message.request.event.KfEventRequest;
import org.netsharp.wx.sdk.mp.message.request.event.LocationEvent;
import org.netsharp.wx.sdk.mp.message.request.event.MassSendFinishEvent;
import org.netsharp.wx.sdk.mp.message.request.event.SubscribeEvent;
import org.netsharp.wx.sdk.mp.util.WeixinUtil;

public final class MessageSerializer {
    private static java.util.HashMap<String, Class<?>> RequestMessageTypes = new java.util.HashMap<String, Class<?>>();
    private static java.util.HashMap<String, Class<?>> RequestEventTypes   = new java.util.HashMap<String, Class<?>>();
    private static Log                                 logger              = LogFactory.getLog(WeixinMessageDispatcher.class);

    static {
        RequestMessageTypes.put(MessageType.Text, TextRequest.class);
        RequestMessageTypes.put(MessageType.Image, ImageRequest.class);
        RequestMessageTypes.put(MessageType.Voice, VoiceRequest.class);
        RequestMessageTypes.put(MessageType.Video, VideoRequest.class);
        RequestMessageTypes.put(MessageType.Location, LocationRequest.class);
        RequestMessageTypes.put(MessageType.Link, LinkRequest.class);

        RequestEventTypes.put(EventType.Subscribe, SubscribeEvent.class);
        RequestEventTypes.put(EventType.Unsubscribe, EventRequest.class);
        RequestEventTypes.put(EventType.Scan, SubscribeEvent.class);
        RequestEventTypes.put(EventType.Location, LocationEvent.class);
        RequestEventTypes.put(EventType.click, EventRequest.class);
        RequestEventTypes.put(EventType.View, EventRequest.class);
        RequestEventTypes.put(EventType.MASSSENDJOBFINISH, MassSendFinishEvent.class);
        RequestEventTypes.put(EventType.Enter, EventRequest.class);

        // 客服事件
        RequestEventTypes.put(EventType.KfCreateSession, KfEventRequest.class);
        RequestEventTypes.put(EventType.KfCloseSession, KfEventRequest.class);
        RequestEventTypes.put(EventType.KfSwitchSession, KfEventRequest.class);
    }

    public static RequestMessage createRequest(HashMap<String, String> dic){
        String messageType = dic.get("MsgType");
        if (messageType == null) {
            throw new WeixinException("未能获得消息类型，消息无法处理");
        }
        String   event = dic.get("Event");
        Class<?> clz   = null;
        if (messageType.equals("event")) {
            if (event == null) {
                logger.error("错误的事件类型!");
            } else {
                clz = RequestEventTypes.get(event.toLowerCase());
                if (clz == null) {
                    logger.error("错误的事件类型:" + event);
                }
            }
        } else {
            clz = RequestMessageTypes.get(messageType);
            if (clz == null) {
                logger.error("错误的事件类型:" + event);
            }
        }
        if (clz == null) {
            return null;
        }

        RequestMessage requestMessage = (RequestMessage) ReflectManager.newInstance(clz);

        assert requestMessage != null;
        requestMessage.setCreateTime(WeixinUtil.formatTime(dic.get("CreateTime")));
        requestMessage.setFromUserName(dic.get("FromUserName"));
        requestMessage.setToUserName(dic.get("ToUserName"));
        requestMessage.setMsgType(messageType);

        if (requestMessage instanceof KfEventRequest) {
            KfEventRequest er = (KfEventRequest) requestMessage;
            er.setEvent(event);
            er.setKfAccount(dic.get("KfAccount"));
            er.setFromKfAccount(dic.get("FromKfAccount"));
            er.setToKfAccount(dic.get("ToKfAccount"));
            if (dic.containsKey("Encrypt")) {
                er.setEncrypt(dic.get("Encrypt"));
            }
        } else if (requestMessage instanceof EventRequest) {
            EventRequest er = (EventRequest) requestMessage;
            er.setEvent(event);
            er.setEventKey(dic.get("EventKey"));
            if (dic.containsKey("Encrypt")) {
                er.setEncrypt(dic.get("Encrypt"));
            }
        }

        ArrayList<String> exceptFields = new ArrayList<String>();
        exceptFields.add("#text");
//		exceptFields.add("FromUserName");
//		exceptFields.add("ToUserName");
//		exceptFields.add("MsgType");
//		exceptFields.add("EventKey");
//		exceptFields.add("Event");

        Class<?>                        type = requestMessage.getClass();
        Iterator<Entry<String, String>> iter = dic.entrySet().iterator();

        while (iter.hasNext()) {

            Entry<String, String> kvp = iter.next();
            String key = kvp.getKey();
            String value = kvp.getValue();

            if (exceptFields.contains(key)) {
                continue;
            }

            if (key.equals("CreateTime")) {
                Date date = WeixinUtil.formatTime(value);
                requestMessage.setCreateTimeString(value);
                requestMessage.setCreateTime(date);

                continue;
            }

            String fieldName = ReflectManager.getFieldName(key);

            Field pi = ReflectManager.getField(type, fieldName);
            if (pi == null) {
                continue;
            }
            pi.setAccessible(true);

            ITypeConvertor tc = TypeConvertorFactory.create(pi.getType());
            Object propertyValue = tc.fromString(value);

            ReflectManager.set(pi, requestMessage, propertyValue);
        }

        logger.info("生成RequestMessage: " + requestMessage.toString());

        return requestMessage;
    }
}