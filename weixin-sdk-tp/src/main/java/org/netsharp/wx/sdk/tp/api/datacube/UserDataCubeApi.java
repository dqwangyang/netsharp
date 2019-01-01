package org.netsharp.wx.sdk.tp.api.datacube;

import java.util.Map;

import org.netsharp.wx.sdk.tp.Const;
import org.netsharp.wx.sdk.tp.bean.response.datacube.UserDataCubeResponse;
import org.netsharp.wx.sdk.tp.utils.Params;
import org.netsharp.wx.sdk.tp.utils.client.HttpUtil;

/**
 * 用户分析统计接口.
 * todo: 校验时间入参
 *
 * @author vioao
 */
public class UserDataCubeApi {

    /**
     * 获取用户分析统计结果.
     *
     * @param url         接口地址
     * @param accessToken 调用凭证
     * @param beginDate   开始时间
     * @param endDate     结束时间
     */
    private static UserDataCubeResponse getDataCube(String url, String accessToken,
                                                    String beginDate, String endDate) {
        Map<String, String> params = Params.create("access_token", accessToken).get();
        String postData = "{\"begin_date\":\"" + beginDate + "\",\"end_date\":\"" + endDate + "\"}";
        return HttpUtil.postJsonBean(url, params, postData, UserDataCubeResponse.class);
    }

    private static final String USER_SUMMARY = Const.Uri.API_URI + "/datacube/getusersummary";

    /**
     * 获取用户增减数据.
     * 最大时间跨度:7
     */
    public static UserDataCubeResponse getUserSummary(String accessToken, String beginDate, String endDate) {
        return getDataCube(USER_SUMMARY, accessToken, beginDate, endDate);
    }

    private static final String USER_CUMULATE = Const.Uri.API_URI + "/datacube/getusercumulate";

    /**
     * 获取累计用户数据.
     * 最大时间跨度:7
     */
    public static UserDataCubeResponse getUserCumulate(String accessToken, String beginDate, String endDate) {
        return getDataCube(USER_CUMULATE, accessToken, beginDate, endDate);
    }
}
