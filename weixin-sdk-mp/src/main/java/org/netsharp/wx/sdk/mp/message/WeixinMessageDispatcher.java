package org.netsharp.wx.sdk.mp.message;

import java.io.InputStream;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;
import org.netsharp.wx.sdk.mp.WeixinException;
import org.netsharp.wx.sdk.mp.message.request.event.EventRequest;
import org.netsharp.wx.sdk.mp.sdk.AesException;
import org.netsharp.wx.sdk.mp.sdk.SHA1;
import org.netsharp.wx.sdk.mp.sdk.WeixinRequestParameters;
import org.netsharp.wx.sdk.mp.util.WeixinUtil;

/**
 * 微信消息分发器，处理微信的请求和回复消息，不处理菜单的链接消息
 * 主要通过管理IMessageListener来实现消息的request和response的消息处理机制
 */
public class WeixinMessageDispatcher {

	private static Log logger = LogFactory.getLog(WeixinMessageDispatcher.class.getSimpleName());
	public static String ListererType = "org.netsharp.wx.pa.WeixinMessageListener";// WeixinMessageListener.class;
	
	/* 主要通过管理IMessageListener来实现消息的request和response的消息处理机制 */
	public final String processRequest(WeixinRequestParameters par, InputStream requestStream) {

		String responseXml = "";

		HashMap<String, String> dic = WeixinUtil.serializeToDictionary(requestStream);
		RequestMessage req = MessageSerializer.createRequest(dic);
		if (req == null) {
			return responseXml;
		}

		if (req instanceof EventRequest) {
			// 忽略：点击菜单跳转链接时的事件回复
			if (((EventRequest) req).getEvent().equalsIgnoreCase("view")) {
				return responseXml;
			}
		}

		//
		par.Oid = req.getToUserName();
		this.validate(par);

		IMessageListener listener = (IMessageListener) ReflectManager.newInstance(ListererType);
		ResponseMessage rsp = listener.processRequest(req);

		if (rsp != null) {
			responseXml = rsp.ToXml();
		}

		return responseXml;
	}

//	/* 主要通过管理IMessageListener来实现消息的request和response的消息处理机制 */
//	public final String processRequest(WeixinRequestParameters par, String requestXml) {
//
//		String responseXml = "";
//
//		HashMap<String, String> dic = WeixinUtil.serializeToDictionary(requestXml);
//		RequestMessage req = MessageSerializer.createRequest(dic);
//		if (req == null) {
//			return responseXml;
//		}
//
//		if (req instanceof EventRequest) {
//			// 忽略：点击菜单跳转链接时的事件回复
//			if (((EventRequest) req).getEvent().equalsIgnoreCase("view")) {
//				return responseXml;
//			}
//		}
//
//		//
//		par.Oid = req.getToUserName();
//		this.validate(par);
//
//		IMessageListener listener = (IMessageListener) ReflectManager.newInstance(ListererType);
//		ResponseMessage rsp = listener.processRequest(req);
//
//		if (rsp != null) {
//			responseXml = rsp.ToXml();
//		}
//
//		return responseXml;
//	}

	/*
	 * 调用场景有两个 1.微信后台服务器配置的时候进行验证 2.每次微信的request和微信response都对请求进行验证
	 */
	public boolean validate(WeixinRequestParameters par) {

		if (ListererType == null) {
			throw new WeixinException("请配置MessageCenter.ListererType:" + IMessageListener.class.getName());
		}

		if (StringManager.isNullOrEmpty(par.Token)) {
			IMessageListener listener = (IMessageListener) ReflectManager.newInstance(ListererType);
			par.Token = listener.getToken(par.Oid);
		}

		try {

			String signature = SHA1.getSHA1(par);
			boolean result = signature.equals(par.Signature);

			logger.debug("微信签名验证成功!");

			return result;

		} catch (AesException e) {

			logger.warn(par.toString());

			throw new WeixinException("微信签名验证失败", e);
		}
	}
}