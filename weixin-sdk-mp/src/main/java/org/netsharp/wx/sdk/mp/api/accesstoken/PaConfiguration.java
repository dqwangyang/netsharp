package org.netsharp.wx.sdk.mp.api.accesstoken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.wx.sdk.mp.WeixinException;

/*
 * 公众号的配置信息
 * */
public class PaConfiguration {
	
	private static Map<String,PAccount> paccounts = new HashMap<String,PAccount>();
	
	public static PAccount get(String originalId){
		if(paccounts.size()==0){
			throw new WeixinException("请初始化公众号配置信息");
		}
		
		PAccount paccount = paccounts.get(originalId);
		
		if(paccount==null){
			throw new WeixinException(String.format("============> 公众号[%s]加载不成功！", originalId));
		}
		
		return paccount;
	}
	
	/*公众号的初始化，可以通过缓存来处理，服务器启动的时候要进行初始化，也要考虑测试环境下的初始化*/
	public static void initialize(List<PAccount> pas){
		
		paccounts.clear();
		
		for(PAccount paccount : pas){
			paccounts.put(paccount.getOriginalId(), paccount);
		}
	}
	
	public static void dispose(){
		paccounts.clear();
	}
}
