package org.netsharp.wx.sdk.mp.api.jssdk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 参考：
 * http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#.E6.AD.A5.E9.AA.A4.E4.B8.89.EF.BC.9A.E9.80.9A.E8.BF.87config.E6.8E.A5.E5.8F.A3.E6.B3.A8.E5.85.A5.E6.9D.83.E9.99.90.E9.AA.8C.E8.AF.81.E9.85.8D.E7.BD.AE
 *
 * @author kxh
 */
public class JsSdkConfig implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -7535569377023236912L;
	// 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    private boolean debug;
    // 必填，公众号的唯一标识
    private String  appId;
    // 必填，生成签名的时间戳,,必须以秒为单位
    private String       timestamp;
    // 必填，生成签名的随机串
    private String       nonceStr;
    // 必填，签名，见附录1
    private String       signature;
    // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    private List<String> jsApiList;

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public List<String> getJsApiList() {
        return (jsApiList != null && jsApiList.size() > 0) ? jsApiList : getDefaultApis();
    }

    public void setJsApiList(List<String> jsApiList) {
        this.jsApiList = jsApiList;
    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }


    private List<String> getDefaultApis() {
        List<String> apiList = new ArrayList<String>();
        apiList.add("onMenuShareTimeline");
        apiList.add("onMenuShareAppMessage");
        apiList.add("onMenuShareQQ");
        apiList.add("onMenuShareWeibo");
        apiList.add("startRecord");
        apiList.add("stopRecord");
        apiList.add("onVoiceRecordEnd");
        apiList.add("playVoice");
        apiList.add("pauseVoice");
        apiList.add("stopVoice");
        apiList.add("onVoicePlayEnd");
        apiList.add("uploadVoice");
        apiList.add("downloadVoice");
        apiList.add("chooseImage");
        apiList.add("previewImage");
        apiList.add("uploadImage");
        apiList.add("downloadImage");
        apiList.add("translateVoice");
        apiList.add("getNetworkType");
        apiList.add("openLocation");
        apiList.add("getLocation");
        apiList.add("hideOptionMenu");
        apiList.add("showOptionMenu");
        apiList.add("hideMenuItems");
        apiList.add("showMenuItems");
        apiList.add("hideAllNonBaseMenuItem");
        apiList.add("showAllNonBaseMenuItem");
        apiList.add("closeWindow");
        apiList.add("scanQRCode");
        apiList.add("chooseWXPay");
        apiList.add("openProductSpecificView");
        apiList.add("addCard");
        apiList.add("chooseCard");
        apiList.add("openCard");

        this.jsApiList = apiList;

        return apiList;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("微信JSSDK配置信息：\n");
        str.append("debug=").append(debug).append(";");
        str.append("appid=").append(appId).append(";");
        str.append("timestamp=").append(timestamp).append(";");
        str.append("nonceStr=").append(nonceStr).append(";");
        str.append("signature=").append(signature).append(";");
        str.append("jsApiList:");
        for (String api : getJsApiList()) {
            str.append(api).append("/");
        }

        return str.toString();
    }
}
