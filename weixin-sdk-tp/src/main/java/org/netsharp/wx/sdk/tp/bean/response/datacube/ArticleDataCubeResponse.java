package org.netsharp.wx.sdk.tp.bean.response.datacube;

import java.util.List;

import org.netsharp.wx.sdk.tp.bean.entity.datacube.ArticleDataCube;
import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

public class ArticleDataCubeResponse extends BaseResponse {
    private List<ArticleDataCube> list;

    public List<ArticleDataCube> getList() {
        return list;
    }

    public void setList(List<ArticleDataCube> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ArticleDataCubeResponse{"
                + "list=" + list + '\''
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}

