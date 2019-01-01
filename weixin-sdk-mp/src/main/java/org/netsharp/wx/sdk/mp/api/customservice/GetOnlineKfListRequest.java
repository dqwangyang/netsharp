package org.netsharp.wx.sdk.mp.api.customservice;

import org.netsharp.wx.sdk.mp.WebClient;
import org.netsharp.wx.sdk.mp.api.Request;

/**
 * 获取客服在线状态接口
 * <p/>
 * Created by kxh on 2015/3/26.
 */
public class GetOnlineKfListRequest extends Request<GetOnlineKfListResponse> {

    public GetOnlineKfListRequest() {
        super();
        this.responseType = GetOnlineKfListResponse.class;
    }

    @Override
    public String getUrl() {
        String url = "https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token=%s";
        return String.format(url, this.getAccessToken());
    }

    @Override
    protected GetOnlineKfListResponse doResponse()  {
        WebClient client = new WebClient();
        String responseInfo = client.downloadString(getUrl());

        System.out.println(responseInfo);

        GetOnlineKfListResponse response = this.deserialize(responseInfo);

        return response;
    }

//    public static void main(String[] args) throws IOException {
//        String json = "{\n" +
//                "\"kf_online_list\": [\n" +
//                "{\n" +
//                "\"kf_account\": \"test1@test\",\n" +
//                "\"status\": 1,\n" +
//                "\"kf_id\": \"1001\",\n" +
//                "\"auto_accept\": 0, \n" +
//                "\"accepted_case\": 1\n" +
//                "},\n" +
//                "{\n" +
//                "\"kf_account\": \"test2@test\",\n" +
//                "\"status\": 1, \n" +
//                "\"kf_id\": \"1002\", \n" +
//                "\"auto_accept\": 0, \n" +
//                "\"accepted_case\": 2\n" +
//                "}\n" +
//                "]\n" +
//                "}";
//        GetOnlineKfListResponse rsp = JsonManage.deSerialize2(json, GetOnlineKfListResponse.class);
//        //rsp = (GetOnlineKfListResponse) JsonManage.deSerialize(GetOnlineKfListResponse.class, json );
//        System.out.println(rsp.toString());
//    }
}
