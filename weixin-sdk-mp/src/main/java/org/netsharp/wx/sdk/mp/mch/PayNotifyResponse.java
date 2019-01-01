package org.netsharp.wx.sdk.mp.mch;

public class PayNotifyResponse extends MchResponse {
    private String openid;
    private String is_subscribe;
    private String bank_type;
    private String total_fee;
    private String transaction_id;
    private String out_trade_no;
    private String attach;
    private String time_end;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
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

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

//[XmlIgnore]
//    public final java.math.BigDecimal getAmount() {
//        int fee = Integer.parseInt(this.getTotalFee());
//
//        return fee / 100;
//    }
//

//
//    //[XmlIgnore]
//    public final java.util.Date getPayTime() {
//        java.util.Date dt = new java.util.Date(0);
//
//        RefObject<java.util.Date> tempRef_dt = new RefObject<java.util.Date>(dt);
//        java.util.Date.TryParse(this.getTimeEnd(), tempRef_dt);
//        dt = tempRef_dt.argvalue;
//
//        return dt;
//    }

}