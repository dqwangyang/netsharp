package org.netsharp.wx.sdk.mp.mch;


public class OrderQueryRequest extends MchRequest<OrderQueryResponse> {

    /**
     * 微信的订单号，优先使用
     */
    private String transactionId;

    /**
     * 商户系统内部的订单号，当没提供transaction_id时需要传这个
     */
    private String outTradeNo;

    public OrderQueryRequest() {
        this.responseType = OrderQueryResponse.class;
    }

    @Override
    protected String getUrl() {
        return "https://api.mch.weixin.qq.com/pay/orderquery";
    }

    @Override
    protected OrderQueryResponse doResponse() {
        java.util.Map<String, String> postData = this.createPostData();
        postData.put("transaction_id", this.getTransactionId());
        postData.put("out_trade_no", this.getOutTradeNo());

        this.addSign(postData);

        return this.httpPost(postData);
    }

    @Override
    protected void doValidate() {
        boolean isOk = (outTradeNo == null || outTradeNo.trim().length() == 0);
        isOk = isOk && (transactionId == null || transactionId.trim().length() == 0);
        if (isOk) {
            throw new IllegalArgumentException("transaction_id或out_trade_no至少有一个不能为空");
        }
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}