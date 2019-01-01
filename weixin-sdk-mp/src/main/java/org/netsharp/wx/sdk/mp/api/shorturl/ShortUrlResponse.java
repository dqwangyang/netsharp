package org.netsharp.wx.sdk.mp.api.shorturl;

import org.netsharp.wx.sdk.mp.api.Response;

public class ShortUrlResponse extends Response {
    private String short_url;

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }
}