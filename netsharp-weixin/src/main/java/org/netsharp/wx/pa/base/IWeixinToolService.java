package org.netsharp.wx.pa.base;

public interface IWeixinToolService {
    /**
     * 长链接转短链接
     *
     * @param IntegerUrl
     * @return
     */
    String getShortUrl(String IntegerUrl,String originalId);
}
