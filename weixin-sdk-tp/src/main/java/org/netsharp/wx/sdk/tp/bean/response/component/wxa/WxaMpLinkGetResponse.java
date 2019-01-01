package org.netsharp.wx.sdk.tp.bean.response.component.wxa;

import org.netsharp.wx.sdk.tp.bean.entity.component.wxa.WxOpens;
import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

/**
 * 获取公众号关联的小程序接口返回.
 *
 * @author vioao
 */
public class WxaMpLinkGetResponse extends BaseResponse {
    private WxOpens wxopens;

    public WxOpens getWxopens() {
        return wxopens;
    }

    public void setWxopens(WxOpens wxopens) {
        this.wxopens = wxopens;
    }

    @Override
    public String toString() {
        return "WxaMpLinkGetResponse{"
                + "wxopens=" + wxopens
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
