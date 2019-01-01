package org.netsharp.wx.sdk.mp.mch;

public class OrderQueryResponse extends MchResponse {

    /**
     * @see OrderTradeStateEnum
     */
    private String trade_state;

    private String attach; // 商家数据包，原样返回
    private String is_subscribe; // 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
    private int    total_fee; // 订单总金额，单位为分
    private String fee_type; // 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
    private String bank_type; // 银行类型，采用字符串类型的银行标识
    private String trade_type; // 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY，详细说明见参数规定
    private String openid; // 用户在商户appid下的唯一标识
    private String time_end; // 订单支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
    private int    cash_fee; // 订单现金支付金额
    private String cash_fee_type; // 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
    private String transaction_id; // 微信支付订单号
    private String out_trade_no; // 商户系统的订单号，与请求一致


    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public int getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(int cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
}

