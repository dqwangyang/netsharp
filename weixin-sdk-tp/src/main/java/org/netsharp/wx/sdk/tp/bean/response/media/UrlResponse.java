package org.netsharp.wx.sdk.tp.bean.response.media;


import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

/**
 * 上传图文消息内的图片获取URL.
 *
 * @author vioao
 */
public class UrlResponse extends BaseResponse {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "UrlResponse{"
                + "url='" + url + '\''
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() +
                +'}';
    }
}
