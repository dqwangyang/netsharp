package org.netsharp.wx.sdk.tp.api;

import java.util.Map;

import org.netsharp.wx.sdk.tp.Const;
import org.netsharp.wx.sdk.tp.bean.response.ip.IpListResponse;
import org.netsharp.wx.sdk.tp.utils.Params;
import org.netsharp.wx.sdk.tp.utils.client.HttpUtil;

/**
 * 微信服务器IP API接口.
 *
 * @author vioao
 */
public class IpApi {
    private static final String GET_CALL_BACK_IP = Const.Uri.API_URI + "/cgi-bin/getcallbackip";

    /**
     * 获取微信服务器IP地址.
     */
    public static IpListResponse getIpList(String token) {
        Map<String, String> params = Params.create("access_token", token).get();
        return HttpUtil.getJsonBean(GET_CALL_BACK_IP, params, IpListResponse.class);
    }
}
