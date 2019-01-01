package org.netsharp.wx.sdk.mp.mch;

import java.util.HashMap;
import java.util.Map;

public final class PayErrors {
    private static java.util.HashMap<String, String> es;
    static {
        es = new java.util.HashMap<String, String>();
        es.put("SYSTEMERROR", "接口后台错误");
        es.put("INVALID_TRANSACTIONID", "无效 transaction");
        es.put("PARAM_ERROR", "提交参数错误");
        es.put("ORDERPAID", "订单已支付");
        es.put("OUT_TRADE_NO_USED", "商户订单号重复");
        es.put("NOAUTH", "商户无权限");
        es.put("NOTENOUGH", "余额不足");
        es.put("NOTSUPORTCARD", "不支持卡类型");
        es.put("ORDERCLOSED", "订单已关闭");
        es.put("BANKERROR", "银行系统异常");
        es.put("REFUND_FEE_INVALID", "退款金额大于支付金额");
        es.put("ORDERNOTEXIST", "ORDERNOTEXIST");
    }

    /*订单关闭状态消息*/
    private static Map<String, String>               order_close_messages;
    static {
        order_close_messages = new HashMap<String, String>();
        order_close_messages.put("ORDERPAID", "订单已支付");
        order_close_messages.put("SYSTEMERROR","系统错误");
        order_close_messages.put("ORDERNOTEXIST","订单不存在");
        order_close_messages.put("ORDERCLOSED","订单已关闭");
        order_close_messages.put("SIGNERROR","签名错误");
        order_close_messages.put("REQUIRE_POST_METHOD","请使用post方法");
        order_close_messages.put("XML_FORMAT_ERROR","XML格式错误");
    }
    public String getOrderCloseMessage(String errorCode){
        return order_close_messages.get(errorCode);
    }

}