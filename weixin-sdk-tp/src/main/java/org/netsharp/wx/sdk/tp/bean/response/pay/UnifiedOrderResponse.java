package org.netsharp.wx.sdk.tp.bean.response.pay;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 统一下单
 * @date 2018/6/27 13:57
 */
public class UnifiedOrderResponse extends BaseResponse {

    @JsonProperty("device_info")
    private String deviceInfo;

    @JsonProperty("trade_type")
    private String tradeType;

    @JsonProperty("prepay_id")
    private String prepayId;

    /**
     * trade_type为NATIVE时有返回，用于生成二维码，展示给用户进行扫码支付
     */
    @JsonProperty("code_url")
    private String codeUrl;

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    @Override
    public String toString() {
        return "UnifiedOrderResponse{" +
                "deviceInfo='" + deviceInfo + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", prepayId='" + prepayId + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                '}';
    }
}
