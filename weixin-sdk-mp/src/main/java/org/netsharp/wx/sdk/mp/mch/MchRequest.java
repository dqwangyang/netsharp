package org.netsharp.wx.sdk.mp.mch;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.util.StringManager;
import org.netsharp.wx.sdk.mp.WebClient;
import org.netsharp.wx.sdk.mp.WeixinException;
import org.netsharp.wx.sdk.mp.pay.PayHelp;

public abstract class MchRequest<T extends MchResponse> {
    private String appId;
    private String mchId;
    private String paySignKey;
    protected Class<?> responseType;

    protected abstract String getUrl();

    protected void vaildate() {
        if (StringManager.isNullOrEmpty(this.getAppId())) {
            throw new WeixinException("AppId");
        }

        if (StringManager.isNullOrEmpty(this.getMchId())) {
            throw new WeixinException("MchId");
        }

        this.doValidate();
    }

    protected abstract void doValidate();

    protected final T httpPost(Map<String, String> postdata) {
        String xml = SdkHelper.writeXml(postdata);
//        System.out.println(postdata);
        return httpPost(xml);
    }

    protected final T httpPost(String xml) {
        String retXml = this.onPosting(xml);

        T t = deserialize(retXml);

        return onPost(t);
    }

    protected String onPosting(String xml) {
        WebClient wc = new WebClient();
        String retXml = wc.uploadString(getUrl(), xml);
//        System.out.println(retXml);
        return retXml;
    }

    protected T deserialize(String xml) {
        T t = SdkHelper.deserialize(this.responseType, xml);
        t.setXml(xml);

        return t;
    }

    protected T onPost(T t) {
        if (!MchResponseType.Success.equals(t.getReturn_code())) {
            throw new WeixinException(t.getReturn_Msg());
        }

        if (!MchResponseType.Success.equals(t.getResult_Code())) {
            throw new WeixinException(t.getError_Code_Des());
        }

        return t;
    }

    public T getResponse() {
        this.vaildate();

        return doResponse();
    }

    protected abstract T doResponse();

    protected final HashMap<String, String> createPostData() {

        HashMap<String, String> sk = new HashMap<String, String>();

        sk.put("appid", this.getAppId());
        sk.put("mch_id", this.getMchId());
        sk.put("nonce_str", PayHelp.getNonceString());

        return sk;
    }

    protected final void addSign(Map<String, String> postdata) {
        String signKey = this.getPaySignKey();
        String sign = PayHelp.getMd5Sign(postdata, signKey);

        postdata.put("sign", sign);
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String value) {
        appId = value;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String value) {
        mchId = value;
    }

    public String getPaySignKey() {
        return paySignKey;
    }

    public void setPaySignKey(String value) {
        paySignKey = value;
    }
}