package org.netsharp.wx.sdk.tp.bean.response.template;

import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

/**
 * 模板消息发送结果.
 *
 * @author vioao
 */
public class SendTemplateMsgResponse extends BaseResponse {
    private Long msgid;

    public Long getMsgid() {
        return msgid;
    }

    public void setMsgid(Long msgid) {
        this.msgid = msgid;
    }

    @Override
    public String toString() {
        return "SendTemplateMsgResponse{"
                + "msgid=" + msgid
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
