package org.netsharp.wx.sdk.mp.mch;

/**
 * 订单交易状态
 *
 */
public enum OrderTradeStateEnum {
    SUCCESS("SUCCESS", "支付成功"),
    NOTPAY("NOTPAY", "未支付"),
    REFUND("REFUND", "转入退款"),
    CLOSED("CLOSED", "已关闭"),
    REVOKED("REVOKED", "已撤销"),
    USERPAYING("USERPAYING", "用户支付中"),
    PAYERROR("PAYERROR", "其他原因"),;

    private String text;
    private String state;

    OrderTradeStateEnum(String state, String text) {
        this.state = state;
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public String getState() {
        return this.state;
    }
}
