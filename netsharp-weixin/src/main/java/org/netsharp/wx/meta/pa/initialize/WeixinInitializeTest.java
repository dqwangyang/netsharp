package org.netsharp.wx.meta.pa.initialize;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.id.IId;
import org.netsharp.wx.pa.base.INWeixinResponsorService;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.dic.PublicAccountType;
import org.netsharp.wx.pa.entity.NWeixinResponsor;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.response.WeixinCustomerServiceResponse;
import org.netsharp.wx.pa.response.WeixinHelpResponse;
import org.netsharp.wx.pa.response.WeixinLocationResponse;
import org.netsharp.wx.pa.response.WeixinPayResponse;
import org.netsharp.wx.pa.response.WeixinReloadReplyResponse;
import org.netsharp.wx.pa.response.WeixinReplyResponse;
import org.netsharp.wx.pa.response.WeixinUnSubscribeResponse;
import org.netsharp.wx.pa.response.WeixinUnhitResponse;
import org.netsharp.wx.pa.response.subscribe.WeixinSubscribeResponse;

public class WeixinInitializeTest {

	@Test
	public void initialize() {
		
		PublicAccount pa = this.publicAccount();
		
		this.responsor(pa);
	}

	private PublicAccount publicAccount() {

		IPublicAccountService paService = ServiceFactory.create(IPublicAccountService.class);

		Oql oql = new Oql();
		{
			oql.setSelects("*");
		}

		PublicAccount pa = paService.queryFirst(oql);
		if (pa != null) {
			return pa;
		}

		pa = new PublicAccount();
		{
			pa.toNew();

			pa.setName("NETSHARP");
			pa.setWeixinCode("netsharp");
			pa.setPublicAccountType(PublicAccountType.personal);
			pa.setOriginalId("gh_befcc6d4c40d");
			pa.setQrCode("https://mp.weixin.qq.com/misc/getqrcode?fakeid=3005081142&token=166047266&action=download&style=1&pixsize=224");

			pa.setAppId("wx01da8fb6c9d686a4");
			pa.setAppSecret("16cb338ea21b684968b011fff6e96ece");
			pa.setEncryptionKey("VjTCXLDcQIcT8JtO2frf0EPGKSka3qEqT1uvPJTI28d");
			pa.setToken("netsharp2020");

		}

		pa = paService.save(pa);

		return pa;
	}

	public void responsor(PublicAccount pa) {

		INWeixinResponsorService service = ServiceFactory.create(INWeixinResponsorService.class);

		Oql oql = new Oql();
		{
			oql.setSelects("id");
		}

		int count = service.queryCount(oql);
		if (count > 0) {
			return;
		}
		
		Mtable meta = MtableManager.getMtable(NWeixinResponsor.class);
		IId id = meta.getId();

		NWeixinResponsor resp = new NWeixinResponsor();
		{
			resp.setName("微信支付");
			resp.setJavaType(WeixinPayResponse.class.getName());
			resp.setSeq(100d);
			resp.setPublicAccount(pa);
			resp.toNew();
			
			meta.setId(resp, id.newId());
		}
		service.save(resp);

		{
			resp.setName("自动回复");
			resp.setJavaType(WeixinReplyResponse.class.getName());
			resp.setSeq(200d);
			resp.toNew();
			
			meta.setId(resp, id.newId());
		}
		service.save(resp);

		{
			resp.setName("刷新自动回复配置");
			resp.setJavaType(WeixinReloadReplyResponse.class.getName());
			resp.setSeq(300d);
			resp.toNew();
			
			meta.setId(resp, id.newId());
		}
		service.save(resp);

		{
			resp.setName("关注公众号");
			resp.setJavaType(WeixinSubscribeResponse.class.getName());
			resp.setSeq(400d);
			resp.toNew();
			
			meta.setId(resp, id.newId());
		}
		service.save(resp);

		{
			resp.setName("发送帮助");
			resp.setJavaType(WeixinHelpResponse.class.getName());
			resp.setSeq(500d);
			resp.toNew();
			
			meta.setId(resp, id.newId());
		}
		service.save(resp);

		{
			resp.setName("取消关注公众号");
			resp.setJavaType(WeixinUnSubscribeResponse.class.getName());
			resp.setSeq(600d);
			resp.toNew();
			
			meta.setId(resp, id.newId());
		}
		service.save(resp);

		{
			resp.setName("得到地理位置");
			resp.setJavaType(WeixinLocationResponse.class.getName());
			resp.setSeq(700d);
			resp.toNew();
			
			meta.setId(resp, id.newId());
		}
		service.save(resp);

		{
			resp.setName("客服系统");
			resp.setJavaType(WeixinCustomerServiceResponse.class.getName());
			resp.setSeq(800d);
			resp.toNew();
			
			meta.setId(resp, id.newId());
		}
		service.save(resp);

		{
			resp.setName("未命关键字");
			resp.setJavaType(WeixinUnhitResponse.class.getName());
			resp.setSeq(900d);
			resp.toNew();
			
			meta.setId(resp, id.newId());
		}
		service.save(resp);
	}
}
