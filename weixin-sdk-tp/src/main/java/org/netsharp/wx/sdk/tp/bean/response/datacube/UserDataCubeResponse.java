package org.netsharp.wx.sdk.tp.bean.response.datacube;

import java.util.List;

import org.netsharp.wx.sdk.tp.bean.entity.datacube.UserDataCube;
import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

public class UserDataCubeResponse extends BaseResponse {
    private List<UserDataCube> list;

    public List<UserDataCube> getList() {
        return list;
    }

    public void setList(List<UserDataCube> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "UserDataCubeResponse{"
                + "list=" + list
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
