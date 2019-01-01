package org.netsharp.wx.sdk.tp.bean.response.kf;

import java.util.List;

import org.netsharp.wx.sdk.tp.bean.entity.kf.KfAccount;
import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

/**
 * 客服账号列表.
 *
 * @author vioao
 */
public class KfListResponse extends BaseResponse {
    private List<KfAccount> kfList;

    public List<KfAccount> getKfList() {
        return kfList;
    }

    public void setKfList(List<KfAccount> kfList) {
        this.kfList = kfList;
    }

    @Override
    public String toString() {
        return "KfListResponse{"
                + "kfList=" + kfList
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
