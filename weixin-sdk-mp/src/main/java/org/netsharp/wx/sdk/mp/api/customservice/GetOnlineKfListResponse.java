package org.netsharp.wx.sdk.mp.api.customservice;

import java.util.List;

import org.netsharp.wx.sdk.mp.api.Response;

/**
 * Created by kxh on 2015/3/26.
 */
public class GetOnlineKfListResponse extends Response {
    private List<OnlineKfInfo> kf_online_list;

    public GetOnlineKfListResponse() {

    }

    public List<OnlineKfInfo> getKf_online_list() {
        return kf_online_list;
    }

    public void setKf_online_list(List<OnlineKfInfo> kf_online_list) {
        this.kf_online_list = kf_online_list;
    }

   
}

