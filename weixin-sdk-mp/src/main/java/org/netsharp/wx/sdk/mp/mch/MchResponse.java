package org.netsharp.wx.sdk.mp.mch;

public class MchResponse {

    private String xml;// [XmlIgnore]
    private String return_code;
    private String return_msg;
    private String result_code;
    private String err_code;
    private String err_code_des;
    private String appid;
    private String mch_id;
    private String nonce_str;
    private String sign;
    private String device_info;

    public final String getXml() {
        return xml;
    }

    public final void setXml(String value) {
        xml = value;
    }

    public final String getReturn_code() {
        return return_code;
    }

    public final void setReturn_code(String value) {
        return_code = value;
    }

    public final String getReturn_Msg() {
        return return_msg;
    }

    public final void setReturn_Msg(String value) {
        return_msg = value;
    }

    public final String getResult_Code() {
        return result_code;
    }

    public final void setResult_Code(String value) {
        result_code = value;
    }

    public final String getError_code() {
        return err_code;
    }

    public final void setError_code(String value) {
        err_code = value;
    }

    public final String getError_Code_Des() {
        return err_code_des;
    }

    public final void setError_Code_Des(String value) {
        err_code_des = value;
    }

    public final String getAppid() {
        return appid;
    }

    public final void setAppid(String value) {
        appid = value;
    }

    public final String getMch_id() {
        return mch_id;
    }

    public final void setMch_id(String value) {
        mch_id = value;
    }

    public final String getNonceString() {
        return nonce_str;
    }

    public final void setNonceString(String value) {
        nonce_str = value;
    }

    public final String getSign() {
        return sign;
    }

    public final void setSign(String value) {
        sign = value;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }
}