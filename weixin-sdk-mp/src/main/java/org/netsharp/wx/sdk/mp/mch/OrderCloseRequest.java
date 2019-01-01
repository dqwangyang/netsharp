package org.netsharp.wx.sdk.mp.mch;

import org.apache.commons.lang.StringUtils;

/**
 * 当订单支付失败时，需要关闭相应的支付单
 */
public class OrderCloseRequest extends MchRequest<MchResponse> {

    private String outTradeNo;

    public OrderCloseRequest() {
        this.responseType = MchResponse.class;
    }

    @Override
    protected String getUrl() {
        return "https://api.mch.weixin.qq.com/pay/closeorder";
    }

    @Override
    protected void doValidate() {
        if (StringUtils.isEmpty(outTradeNo)) {
            throw new IllegalArgumentException("参数 out_trade_no 不能为空");
        }
    }

    @Override
    protected MchResponse doResponse() {
        java.util.Map<String, String> postData = this.createPostData();
        postData.put("out_trade_no", this.getOutTradeNo());
        this.addSign(postData);

        return this.httpPost(postData);
    }

    public boolean getOrderCloseStatus() {
        MchResponse response = this.getResponse();
        // TODO 订单关闭状态解析。。。微信文档不清楚
        if (response.getResult_Code().equalsIgnoreCase("")) {

        }
        return false;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}