package org.netsharp.wx.sdk.mp.api.shorturl;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.wx.sdk.mp.api.Request;
import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

public class ShortUrlRequest extends Request<ShortUrlResponse> {
    public ShortUrlRequest() {
        this.responseType = ShortUrlResponse.class;
    }

    public final String getAction() {
        return "long2short";
    }

    private String longUrl;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        ShortUrlResponse response = this.getResponse();
        return response.getShort_url();
    }

    @Override
    public String getUrl() {
        return String.format("https://api.weixin.qq.com/cgi-bin/shorturl?access_token=%1$s", this.getAccessToken());
    }

    @Override
    protected WeixinValidation doValidate() {
        if (StringHelper.isNullOrEmpty(getLongUrl())) {
            return WeixinValidation.create(false, "LongUrl is null");
        }

        return super.doValidate();
    }

    @Override
    protected ShortUrlResponse doResponse() {
        String    json   = this.CreateJson();
        return this.executeHttpPost(json);
//        WebClient client = new WebClient();
//
//        String result = client.uploadString(getUrl(), json);
//
//        this.logger.info(result);
//
//        return this.deserialize(result);
    }

    private String CreateJson() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("action", this.getAction());
        data.put("long_url", this.getLongUrl());

        return this.serialize(data);
    }
}