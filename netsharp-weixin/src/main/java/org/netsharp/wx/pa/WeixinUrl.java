package org.netsharp.wx.pa;

import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.response.PublicAccountManager;


public class WeixinUrl {
	
	public static String getFullUrl(String url,String originalId,String openId){
		
		url = WeixinUrl.getFullUrl(url, originalId);
		
		if(url.indexOf("?")<=0){
			url += "?";
		}
		else{
			url += "&";
		}
		
		url += "openId=" + openId + "&originalId=" + originalId;
		
		return url;
	}
	
	/*把URL转换成全称的URL
	 * 场景：微信图文的URL配置的是相对路径，粉丝收到图文以后要打开绝对的路径*/
	public static String getFullUrl(String url,String originalId){
		
		if(url==null){
			return null;
		}
		
		if(url.startsWith("http:") || url.startsWith("www")){
			return url;
		}
		
		if(!url.startsWith("/")){
			url = "/"+url;
		}
		
		PublicAccount pa = PublicAccountManager.getInstance().get(originalId).getAccount();
		String host = pa.getHost();
		if(host.endsWith("/")){
			url = url.substring(1);
		}
		
		url = host + url;
		
		
		return url;
	}
}
