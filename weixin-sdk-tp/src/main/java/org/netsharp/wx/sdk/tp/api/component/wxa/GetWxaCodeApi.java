package org.netsharp.wx.sdk.tp.api.component.wxa;

import java.util.Map;

import org.netsharp.wx.sdk.tp.Const;
import org.netsharp.wx.sdk.tp.bean.MediaFile;
import org.netsharp.wx.sdk.tp.bean.entity.component.wxa.GetWxaCode;
import org.netsharp.wx.sdk.tp.bean.entity.component.wxa.GetWxaCodeUnlimit;
import org.netsharp.wx.sdk.tp.utils.Params;
import org.netsharp.wx.sdk.tp.utils.client.HttpUtil;
import org.netsharp.wx.sdk.tp.utils.serialize.SerializeUtil;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 获取小程序分享二维码
 * @date 2018/6/27 20:26
 */
public class GetWxaCodeApi {

    private static final String UN_LIMIT_QR_CODE = Const.Uri.API_URI + "/wxa/getwxacodeunlimit";

    /**
     * 通过该接口生成的小程序码，永久有效，数量暂无限制。用户扫描该码进入小程序后，
     * 开发者需在对应页面获取的码中 scene 字段的值，
     * 再做处理逻辑。使用如下代码可以获取到二维码中的 scene
     * 字段的值。调试阶段可以使用开发工具的条件编译自定义参数 scene=xxxx 进行模拟，
     * 开发工具模拟时的 scene 的参数值需要进行 urlencode
     * https://developers.weixin.qq.com/miniprogram/dev/api/qrcode.html?search-key=%E5%B0%8F%E7%A8%8B%E5%BA%8F%20%E7%A0%81
     * @param accessToken
     * @param unlimit
     * @return
     */
    public static MediaFile getWxaCodeUnLimit(String accessToken, GetWxaCodeUnlimit unlimit) {
        Map<String, String> params = Params.create("access_token", accessToken).get();
        return HttpUtil.download(UN_LIMIT_QR_CODE, params, SerializeUtil.beanToJson(unlimit));
    }

    /**
     * 我们推荐生成并使用小程序码，它具有更好的辨识度。目前有两个接口可以生成小程序码，开发者可以根据自己的需要选择合适的接口。
     * 接口A: 适用于需要的码数量较少的业务场景 接口地址：
     * https://api.weixin.qq.com/wxa/getwxacode?access_token=ACCESS_TOKEN
     * @param accessToken
     * @param getWxaCode GetWxaCode
     * @return
     */
    public static MediaFile getWxaCodeLimit(String accessToken, GetWxaCode getWxaCode) {
        Map<String, String> params = Params.create("access_token", accessToken).get();
        return HttpUtil.download(GET_WXA_CODE, params, SerializeUtil.beanToJson(getWxaCode));
    }

    public static final String GET_WXA_CODE = Const.Uri.API_URI + "/wxa/getwxacode";

    /**
     * 注意：通过该接口生成的小程序二维码，永久有效，
     * 数量限制见文末说明，请谨慎使用。用户扫描该码进入小程序后，
     * 将直接进入 path 对应的页面
     *
     * @param accessToken
     * @param path
     * @param width
     * @return
     */
    public static MediaFile createWxaQrcode(String accessToken, String path, int width){
        String data = String.format("{\"path\":\"%s\",\"width\":%d}",path,width);
        Map<String, String> params = Params.create("access_token", accessToken).get();
        return HttpUtil.download(CREATE_WXA_QRCODE, params, data);
    }

    private static final String CREATE_WXA_QRCODE = Const.Uri.API_URI + "/cgi-bin/wxaapp/createwxaqrcode";
}
