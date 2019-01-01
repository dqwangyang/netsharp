package org.netsharp.wx.sdk.tp.bean.response.user;

import java.util.List;

import org.netsharp.wx.sdk.tp.bean.entity.user.User;
import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

public class UserListResponse extends BaseResponse {
    private List<User> userInfoList;

    public List<User> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<User> userInfoList) {
        this.userInfoList = userInfoList;
    }

    @Override
    public String toString() {
        return "UserListResponse{"
                + "userInfoList=" + userInfoList
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
