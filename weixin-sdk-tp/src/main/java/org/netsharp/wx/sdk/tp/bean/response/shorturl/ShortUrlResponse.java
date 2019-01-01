package org.netsharp.wx.sdk.tp.bean.response.shorturl;

import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

/**
 * 微信返回短连接Bean.
 *
 * @author vioao
 */
public class ShortUrlResponse extends BaseResponse {
    private String shortUrl;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public String toString() {
        return "ShortUrlResponse{"
                + "shortUrl='" + shortUrl + '\''
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
