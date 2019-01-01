package org.netsharp.wx.sdk.tp.bean.response.kf;

import java.util.List;

import org.netsharp.wx.sdk.tp.bean.entity.kf.Session;
import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

/**
 * 客服会话列表.
 *
 * @author vioao
 */
public class SessionListResponse extends BaseResponse {
    private List<Session> sessionlist;

    public List<Session> getSessionlist() {
        return sessionlist;
    }

    public void setSessionlist(List<Session> sessionlist) {
        this.sessionlist = sessionlist;
    }

    @Override
    public String toString() {
        return "SessionListResponse{"
                + "sessionlist=" + sessionlist
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
