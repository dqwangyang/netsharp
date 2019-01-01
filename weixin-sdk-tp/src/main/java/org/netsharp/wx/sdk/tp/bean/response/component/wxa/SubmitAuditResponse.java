package org.netsharp.wx.sdk.tp.bean.response.component.wxa;

import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

public class SubmitAuditResponse extends BaseResponse {
    private String auditid;

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid;
    }

    @Override
    public String toString() {
        return "SubmitAuditResponse{"
                + "auditid='" + auditid + '\''
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
