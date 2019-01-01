package org.netsharp.wx.sdk.mp.mch.redpack;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.wx.sdk.mp.api.accesstoken.PAccount;
import org.netsharp.wx.sdk.mp.api.accesstoken.PaConfiguration;

/**
 * 微信红包接口相关常量定义
 *
 * @author kxh
 */
public class RedPacketConstant {
    // 微信红包接口
    private final static String WX_REDPACKET_API_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
    // 证书
    private static       String APICLIENT_CERT       = "apiclient_cert.p12";
    private final static Map<String, String> ERROR_CODE = new HashMap<String, String>();

    static {
        ERROR_CODE.put("NOAUTH", "没有开通api权限");
        ERROR_CODE.put("PARAM_ERROR", "参数错误");
        ERROR_CODE.put("OPENID_ERROR", "OpenId错误");
        ERROR_CODE.put("NOTENOUGH", "账户余额不足");
        ERROR_CODE.put("SYSTEMERROR", "系统繁忙，请再试");
        // 只允许北京时间0-8点之外的时间段有效
        ERROR_CODE.put("TIME_LIMITED", "企业红包的发送时间爱受限");
        // 每分钟发红包数量不超过1800个（可联系微信支付调高额度）
        ERROR_CODE.put("SECOND_OVER_LIMITED", "企业红包发放达到每分钟的发放上限");
        // 每个红包金额必须大于1元，小于200元，（可联系微信支付调高额度至4999元，wxhongbao@tecent.com）
        ERROR_CODE.put("MONEY_LIMIT", "红包金额发放限制");
    }

    public static String getRedPacketApiUrl() {
        return WX_REDPACKET_API_URL;
    }

    public static String getErrorMsg(String errorCode) {
        return ERROR_CODE.get(errorCode);
    }

    public static String getMchId(String originalId) {
    	PAccount pa = PaConfiguration.get(originalId);
    	
    	return pa.getMch_id();
    }

    public static String getApiclientCert() {
        return APICLIENT_CERT;
    }

    public static String getAppId(String originalId) {
    	PAccount pa = PaConfiguration.get(originalId);
    	
    	return pa.getApp_id();
    }

    public static String getPartnerKey(String originalId) {
    	PAccount pa = PaConfiguration.get(originalId);
    	
    	return pa.getMch_parner_key();
    }
}
