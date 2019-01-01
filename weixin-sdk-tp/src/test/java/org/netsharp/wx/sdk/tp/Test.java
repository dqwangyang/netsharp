package org.netsharp.wx.sdk.tp;


import org.netsharp.wx.sdk.tp.api.component.wxa.UserInfoApi;
import org.netsharp.wx.sdk.tp.bean.entity.user.UserInfo;
import org.netsharp.wx.sdk.tp.utils.serialize.SerializeUtil;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO
 * @date 2018/7/16 16:54
 */
public class Test {

    private static final String text =
            "GJM7aTwlVU7M5/UOpj2Zc0iGJBS867qOjujFm63lyIyIqqZr7BTlCXEmyp9OHrSdSadXKRlC0W0gwHkWwvWUYrz" +
                    "/Khc3X43podX424HnyuVR9NAMWREeptWvwauQRA6/Gqa9E0dXaVNHXWzvS8xjtp0ImrlBM25U6Utsm9DpgfpJQ" +
                    "/WCsqWbOgZDMIEBsv/G2ioAnYuaenJWLVLUMH6slw==";
    private static final String text2 =
            "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZMQmRzooG2xrDcvSnxIMXFufNstNGTyaGS9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB" +
                    "+3hVbJSRgv+4lGOETKUQz6OYStslQ142dNCuabNPGBzlooOmB231qMM85d2" +
                    "/fV6ChevvXvQP8Hkue1poOFtnEtpyxVLW1zAo6/1Xx1COxFvrc2d7UL/lmHInNlxuacJXwu0fjpXfz" +
                    "/YqYzBIBzD6WUfTIF9GRHpOn/Hz7saL8xz+W//FRAUid1OksQaQx4CMs8LOddcQhULW4ucetDf96JcR3g0gfRK4PC7E" +
                    "/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns" +
                    "/8wR2SiRS7MNACwTyrGvt9ts8p12PKFdlqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYVoKlaRv85IfVunYzO0IKXsyl7JCUjCpoG20f0a04COwfneQAGGwd5oa+T8yO5hzuyDb/XcxxmK01EpqOyuxINew==";

    public static void main(String[] args) throws Exception {
        UserInfoApi userInfoApi = new UserInfoApi("wx4f4bc4dec97d474b");
        UserInfo userInfo = userInfoApi.getUserInfo(text2, "tiihtNczf5v6AKRyjwEUhQ==", "r7BXXKkLb8qrSNn05n0qiA==");
        System.out.println(SerializeUtil.beanToJson(userInfo));
    }
}


