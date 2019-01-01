package org.netsharp.wx.sdk.mp.mch;

import java.util.Map;

import org.netsharp.util.StringManager;
import org.netsharp.wx.sdk.mp.WeixinException;
import org.netsharp.wx.sdk.mp.pay.PayOrder;

public class PrePayRequest extends MchRequest<PrePayResponse>
{
	private PayOrder order;
	
	public PrePayRequest(){
		
		super();
		
		this.responseType = PrePayResponse.class;
	}
	
	@Override
	protected PrePayResponse doResponse()
	{
		Map<String, String> map = this.createPostData();
		
		map.put("body", this.getOrder().getBody());
		map.put("openid", this.getOrder().getOpenId());
		map.put("out_trade_no", this.getOrder().getCode());
		map.put("total_fee", this.getOrder().getTotalFee());
		map.put("spbill_create_ip", this.getOrder().getClientIp());
		map.put("notify_url", this.getOrder().getNotify_Url());
		map.put("trade_type", getOrder().getTrade_type());
		map.put("attach", this.getOrder().getAttach());
		
		this.addSign(map);
		
		return this.httpPost(map);
	}

	@Override
	protected String getUrl()
	{
		return "https://api.mch.weixin.qq.com/pay/unifiedorder";
	}

	@Override
	protected void doValidate()
	{
		if (getOrder() == null)
		{
			throw new WeixinException("订单信息order不能为null");
		}
		else if (StringManager.isNullOrEmpty(getOrder().getBody()))
		{
			throw new WeixinException("商品描述不能为空");
		}
		else if (StringManager.isNullOrEmpty(getOrder().getCode()))
		{
			throw new WeixinException("订单号不能为空");
		}
		else if (getOrder().getCode().length() >= 32)
		{
			throw new IllegalArgumentException("订单号必须在32字节以下");
		}
		else if (StringManager.isNullOrEmpty(getOrder().getClientIp()))
		{
			throw new WeixinException("用户客户端IP不能为空");
		}
		else if (getOrder().getClientIp().indexOf(".") < 0)
		{
			throw new IllegalArgumentException("用户客户端IP不合法");
		}
		else if (getOrder().getAmount()<=0)
		{
			throw new IllegalArgumentException("订单总金额超出合法范围");
		}
		else if (StringManager.isNullOrEmpty(getOrder().getAppId()))
		{
			throw new WeixinException("appid");
		}
		else if (StringManager.isNullOrEmpty(getOrder().getMch_id()))
		{
			throw new WeixinException("mch_id");
		}
		else if (StringManager.isNullOrEmpty(getOrder().getPaySignKey()))
		{
			throw new WeixinException("pay key");
		}
		else if (getOrder().getTrade_type().equals("JSAPI") && StringManager.isNullOrEmpty(this.getOrder().getOpenId()))
		{
			throw new WeixinException("openid is null");
		}
	}

	@Override
	public String getAppId()
	{
		if (this.getOrder() == null)
		{
			return "";
		}

		return this.getOrder().getAppId();
	}
	@Override
	public void setAppId(String value)
	{
		throw new RuntimeException("此参数必须通过Order设置");
	}

	@Override
	public String getMchId()
	{
		if (this.getOrder() == null)
		{
			return "";
		}

		return this.getOrder().getMch_id();
	}
	@Override
	public void setMchId(String value)
	{
		throw new RuntimeException("此参数必须通过Order设置");
	}

	@Override
	public String getPaySignKey()
	{
		if (this.getOrder() == null)
		{
			return "";
		}

		return this.getOrder().getPaySignKey();
	}
	@Override
	public void setPaySignKey(String value)
	{
		throw new RuntimeException("此参数必须通过Order设置");
	}


	
	public final PayOrder getOrder()
	{
		return order;
	}
	public final void setOrder(PayOrder value)
	{
		order = value;
	}
}