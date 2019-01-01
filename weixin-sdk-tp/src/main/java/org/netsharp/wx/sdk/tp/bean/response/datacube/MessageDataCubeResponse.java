package org.netsharp.wx.sdk.tp.bean.response.datacube;

import java.util.List;

import org.netsharp.wx.sdk.tp.bean.entity.datacube.MessageDataCube;
import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

public class MessageDataCubeResponse extends BaseResponse {
    private List<MessageDataCube> list;

    public List<MessageDataCube> getList() {
        return list;
    }

    public void setList(List<MessageDataCube> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "MessageDataCubeResponse{"
                + "list=" + list
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
