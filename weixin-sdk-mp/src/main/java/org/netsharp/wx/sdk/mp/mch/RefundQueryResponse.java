package org.netsharp.wx.sdk.mp.mch;

import java.util.ArrayList;
import java.util.List;

public class RefundQueryResponse extends MchResponse {
    private String trade_state;
    private String attach; // 商家数据包，原样返回
    private String is_subscribe; // 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
    private String bank_type; // 银行类型，采用字符串类型的银行标识
    private String trade_type; // 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY，详细说明见参数规定
    private String openId; // 用户在商户appid下的唯一标识
    private String time_end; // 订单支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010

    private int    cash_fee; // 订单现金支付金额
    private String cash_fee_type; // 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY


    private String fee_type; // 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
    private int    total_fee; // 订单总金额，单位为分
    private String transaction_id; // 微信支付订单号
    private String out_trade_no; // 商户系统的订单号，与请求一致
    private int    refund_fee; // 退款总金额；单位分；支付部分退款
    private int    refund_count; // 退款笔数

    // 明细退款单
    List<RefundForm> refundDetails = new ArrayList<RefundForm>();

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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
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

    public int getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(int refund_fee) {
        this.refund_fee = refund_fee;
    }

    public int getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(int refund_count) {
        this.refund_count = refund_count;
    }

    public List<RefundForm> getRefundDetails() {
        return refundDetails;
    }

    public void setRefundDetails(List<RefundForm> refundDetails) {
        this.refundDetails = refundDetails;
    }
}

/**
 * 退款单
 * <p/>
 * 退款状态：
 * SUCCES: 退款成功<br/>
 * FAIL: 退款失败<br/>
 * PROCESSING: 退款处理中<br/>
 * NOTSURE: 未确定，需要商户原退款单号重新发起<br/>
 * CHANGE: 转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者财付通转账的方式进行退款。<br/>
 */
class RefundForm {
    private String out_refund_no;  // 商户退款单号
    private String refund_id; // 微信退款单号
    private String refund_status; // 退款状态
    private int    refund_fee; // 退款金额，单位分；

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(String refund_status) {
        this.refund_status = refund_status;
    }

    public int getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(int refund_fee) {
        this.refund_fee = refund_fee;
    }
}