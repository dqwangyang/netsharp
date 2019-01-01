package org.netsharp.wx.sdk.tp.bean.response.datacube;

import java.util.List;

import org.netsharp.wx.sdk.tp.bean.entity.datacube.InterfaceDataCube;
import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

public class InterfaceDataCubeResponse extends BaseResponse {
    private List<InterfaceDataCube> list;

    public List<InterfaceDataCube> getList() {
        return list;
    }

    public void setList(List<InterfaceDataCube> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "InterfaceDataCubeResponse{"
                + "list=" + list
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
