//package org.netsharp.weixinsdk.api;
//
//import java.util.Date;
//
//import org.junit.Test;
//import org.netsharp.core.util.JsonManage;
//import org.netsharp.wx.pa.sdk.WeixinAccountConfig;
//import org.netsharp.wx.pa.sdk.api.Response;
//import org.netsharp.wx.pa.sdk.api.accesstoken.AccessToken;
//import org.netsharp.wx.pa.sdk.api.customessage.TextCustomerMessageRequest;
//
///**
// * @author kxh
// */
//public class CustomerMessageApiTest {
//    //7vadMIubcP3c9mSEGxPvnLVgErVfINi7WfkczjXYFyuLkErOO9tyo3XXhzY7GpuMX4CeWFMEmSY1hXFM9irlPiNBC_fnK3xT4non6DGfLo8
//    private static String ACCESS_TOKEN = "7vadMIubcP3c9mSEGxPvnLVgErVfINi7WfkczjXYFyuLkErOO9tyo3XXhzY7GpuMX4CeWFMEmSY1hXFM9irlPiNBC_fnK3xT4non6DGfLo8";
//    private static String OPEN_ID      = "oHnZ4uGF-aljD4umBAwb5j69XT60"; // 赵新新的openid
//
//    /*文本类型的客服消息测试*/
//    @Test
//    public void sendTextMEssage() {
//        AccessToken accessToken = new AccessToken();
//        accessToken.setToken(ACCESS_TOKEN);
//        accessToken.setAppId(WeixinAccountConfig.getAppId());
//        accessToken.setAppSecret(WeixinAccountConfig.getAppSecret());
//        accessToken.setCreateTime(new Date());
//
//        TextCustomerMessageRequest request = new TextCustomerMessageRequest();
//        request.setTokenInfo(accessToken);
//        String content = "这是一个文本类型的客服消息测试";
//        request.setContent(content);
//        request.setOpenId(OPEN_ID);
//
//        Response resp = request.getResponse();
//        System.out.println(String.format("给用户[%s]发送消息的响应：[%s]", OPEN_ID, JsonManage.serialize2(resp)));
//    }
//}
