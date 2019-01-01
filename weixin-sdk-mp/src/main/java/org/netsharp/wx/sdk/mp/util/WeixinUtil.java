package org.netsharp.wx.sdk.mp.util;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.util.XmlService;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WeixinUtil {

	/*
	 * 将微信消息中的CreateTime转换成标准格式的时间(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param createTime 消息创建时间
	 * 
	 * @return
	 */

	private static Log logger = LogFactory.getLog(WeixinUtil.class);

	public static Date formatTime(String createTime) {

		// unix时间精确到秒，java是毫秒

		long time = Long.parseLong(createTime) * 1000L;

		return new Date(time);
	}

	public static long ToUnixTime(Date date) {
		return date.getTime() / 1000;
	}

	public static HashMap<String, String> serializeToDictionary(InputStream stream) {
		Document xdoc = XmlService.read(stream);
		return serializeToDictionary(xdoc);
	}

	// public static HashMap<String, String> serializeToDictionary(String xml) {
	// Document xdoc = XmlService.read(xml);
	//
	// return serializeToDictionary(xdoc);
	// }

	public static HashMap<String, String> serializeToDictionary(Document xdoc) {
		HashMap<String, String> dic = new HashMap<String, String>();

		Node root = xdoc.getChildNodes().item(0);
		NodeList nodes = root.getChildNodes();

		for (int i = 0; i < nodes.getLength(); i++) {

			Node subNode = nodes.item(i);
			String name = subNode.getNodeName();
			String value = subNode.getTextContent();

			logger.debug(name + " : " + value);

			dic.put(name, value);
		}

		return dic;
	}
}