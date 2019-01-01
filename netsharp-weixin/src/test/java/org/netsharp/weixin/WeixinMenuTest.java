package org.netsharp.weixin;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.wx.pa.MenuManage;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.entity.NMenuItem;
import org.netsharp.wx.pa.entity.PublicAccount;

public class WeixinMenuTest {
	// product service account
	// private String host="http://yikuaixiu.com";
	// private String appId=WeixinAccountConfig.getAppId();
	// private String appSecret=WeixinAccountConfig.getAppSecret();
	// private String originalId=WeixinAccountConfig.getOriginalId();
	// private String token="ykx0001";
	// private String wechatAccount="yikuaixiu01";
	// private String
	// encryptionKey="t4XovNdyMrFAQ4HJyvJrXX8RMoDDclGxdqk36fnG2zG");

	// test service account
	private String host = "http://test.yikuaixiu.com";
	private String appId = "wx7a20349386db4782";
//	private String appSecret = "e1220175e8ea2da4c1e500281f6e1b50";
	private String originalId = "gh_cb008567feef";
//	private String token = "ykxtest6543grt5";
//	private String wechatAccount = "jingchenggaoke";
//	private String encryptionKey = "t4XovNdyMrFAQ4HJyvJrXX8RMoDDclGxdqk36fnG2zG";
	

	@Test
	public void createMenu() throws UnsupportedEncodingException {

		List<NMenuItem> tops = new ArrayList<NMenuItem>();

		IPublicAccountService service = ServiceFactory.create(IPublicAccountService.class);
		PublicAccount wcp = service.byOriginalId(originalId);

		String weixinReturnUrl = URLEncoder.encode(host + "/nav/weixin/order/order", "utf-8");

		NMenuItem item = new NMenuItem();
		{
			item.setCode(null);
			item.setName("预约服务");
			item.setPublicAccount(wcp);
			// weixinReturnUrl =
			// URLEncoder.encode(host+"/wx/oauth?src=weixin_order","utf-8");
			item.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?from=weixin&appid=" + appId + "&redirect_uri=" + weixinReturnUrl + "&response_type=code&scope=snsapi_base&state=mystate#wechat_redirect");
			// item.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+weixinReturnUrl+"&response_type=code&scope=snsapi_base&state=mystate#wechat_redirect");
			item.toNew();
		}
		;
		tops.add(item);

		//
		item = new NMenuItem();
		{
			item.setCode(null);
			item.setName("活动攻略");
			// weixinReturnUrl =
			// URLEncoder.encode(host+"/wx/oauth?src=user_center","utf-8");
			// item.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+weixinReturnUrl+"&response_type=code&scope=snsapi_base&state=mystate#wechat_redirect");
			item.setUrl(host + "/nav/weixin/act/activityList");
			item.setPublicAccount(wcp);
			item.toNew();
		}
		;
		tops.add(item);

		weixinReturnUrl = URLEncoder.encode(host + "/nav/weixin/my/index", "utf-8");
		item = new NMenuItem();
		{
			item.setCode(null);
			item.setName("个人中心");
			// weixinReturnUrl =
			// URLEncoder.encode(host+"/wx/oauth?src=user_center","utf-8");
			item.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?from=weixin&appid=" + appId + "&redirect_uri=" + weixinReturnUrl + "&response_type=code&scope=snsapi_base&state=mystate#wechat_redirect");
			item.setPublicAccount(wcp);
			item.toNew();
		}
		;
		tops.add(item);

		MenuManage m = new MenuManage();

		m.create(originalId, tops);
	}

	@Test
	public void uri() throws URISyntaxException {
		// String
		// str="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=pk_atItH9M7f07ql2G8yKFLvX2T3gUNZFQA5cne6Inno-_iT-NTuSd0vkocAST9sz-RA5lJr334xcwSqDXDsMroXSrWHi_0eEdgUrncAkNo";
		//
		// URI uri=new URI(str);
	}
}
