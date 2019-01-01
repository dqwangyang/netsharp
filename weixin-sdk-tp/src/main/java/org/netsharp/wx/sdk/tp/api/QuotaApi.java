package org.netsharp.wx.sdk.tp.api;

import java.util.Map;

import org.netsharp.wx.sdk.tp.Const;
import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;
import org.netsharp.wx.sdk.tp.utils.Params;
import org.netsharp.wx.sdk.tp.utils.client.HttpUtil;

/**
 * 接口调用频次API.
 *
 * @author vioao
 */
public class QuotaApi {
    private static final String CLEAR_QUOTA = Const.Uri.API_URI + "/cgi-bin/clear_quota";

    /**
     * 公众号调用或第三方平台帮公众号调用对公众号的所有api调用（包括第三方帮其调用）次数进行清零.
     */
    public static BaseResponse clearQuota(String accessToken, String appId) {
        Map<String, String> params = Params.create("access_token", accessToken).get();
        String data = "{\"appid\":\"" + appId + "\"}";
        return HttpUtil.postJsonBean(CLEAR_QUOTA, params, data, BaseResponse.class);
    }
}
