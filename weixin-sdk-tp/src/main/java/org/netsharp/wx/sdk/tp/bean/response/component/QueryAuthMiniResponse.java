package org.netsharp.wx.sdk.tp.bean.response.component;

import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 小程序授权
 * @date 2018/6/23 21:08
 */
public class QueryAuthMiniResponse extends BaseResponse {

    private QueryAuthResponse authorizationInfo;

    public QueryAuthResponse getAuthorizationInfo() {
        return authorizationInfo;
    }

    public void setAuthorizationInfo(QueryAuthResponse authorizationInfo) {
        this.authorizationInfo = authorizationInfo;
    }
}
