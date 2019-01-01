package org.netsharp.wx.sdk.tp.api.pay;

import java.util.Map;
import java.util.SortedMap;

import org.netsharp.wx.sdk.tp.api.pay.sdk.WXPay;
import org.netsharp.wx.sdk.tp.api.pay.sdk.WxPlatformPayConfig;
import org.netsharp.wx.sdk.tp.bean.entity.pay.OrderQueryRequest;
import org.netsharp.wx.sdk.tp.bean.entity.pay.UnifiedOrderRequest;
import org.netsharp.wx.sdk.tp.bean.response.pay.BaseResponse;
import org.netsharp.wx.sdk.tp.bean.response.pay.UnifiedOrderResponse;
import org.netsharp.wx.sdk.tp.bean.xml.wrapper.UnifiedOrderResponseWrapper;
import org.netsharp.wx.sdk.tp.utils.JsonMapper;
import org.netsharp.wx.sdk.tp.utils.JsonUtils;
import org.netsharp.wx.sdk.tp.utils.serialize.SerializeUtil;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 微信官方SDK支付包
 * @date 2018/7/6 16:47
 */
public class WxPaymentsApi {

	private WxPlatformPayConfig wxPlatformPayConfig;
	private WXPay wxPay;

	public WxPaymentsApi() {
	}

	public WxPaymentsApi(String appId, String mchId, String key) throws Exception {
		this.wxPlatformPayConfig = new WxPlatformPayConfig(appId, mchId, key);
		this.wxPay = new WXPay(wxPlatformPayConfig, true);
	}

	/**
	 * 统一下单 & 回调数据验证签名
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public UnifiedOrderResponse unifiedOrder(UnifiedOrderRequest request) throws Exception {
		@SuppressWarnings("unchecked")
		SortedMap<String, String> unifiedOrderRequestMap = JsonMapper.nonEmptyMapper().getMapper().convertValue(request, SortedMap.class);
		Map<String, String> stringStringMap = wxPay.unifiedOrder(unifiedOrderRequestMap);
		UnifiedOrderResponseWrapper responseWrapper = SerializeUtil.jsonToBean(JsonUtils.objectToJson(stringStringMap), UnifiedOrderResponseWrapper.class);
		return responseWrapper.getResponse();
	}

	/**
	 * 查询订单 & 回调数据验证签名
	 *
	 * @param orderQueryRequest OrderQueryRequest
	 * @throws Exception
	 */
	public BaseResponse orderQuery(OrderQueryRequest orderQueryRequest) throws Exception {
		@SuppressWarnings("unchecked")
		SortedMap<String, String> orderQueryMap = JsonMapper.nonEmptyMapper().getMapper().convertValue(orderQueryRequest, SortedMap.class);
		Map<String, String> stringStringMap = wxPay.orderQuery(orderQueryMap);
		return SerializeUtil.jsonToBean(JsonUtils.objectToJson(stringStringMap), BaseResponse.class);
	}

}
