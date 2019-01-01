package org.netsharp.wx.sdk.tp.bean.entity.pay;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 统一下单
 * @date 2018/6/27 13:57
 */
public class OrderQueryRequest {

    /**
     * 商家订单号
     */
    @JsonProperty("out_trade_no")
    private String tradeNumber;
    /**
     * 微信流水号
     */
    @JsonProperty("transaction_id")
    private String transactionId;

    public String getTradeNumber() {
        return tradeNumber;
    }

    public void setTradeNumber(String tradeNumber) {
        this.tradeNumber = tradeNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
