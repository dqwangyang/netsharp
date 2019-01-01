package org.netsharp.wx.sdk.mp.api.jssdk;

import java.io.Serializable;
import java.util.Date;

import org.netsharp.wx.sdk.mp.api.Response;


public class JsApiTicketResponse extends Response implements Serializable {
    private static final long serialVersionUID = 4800536733225939709L;

    private String ticket;  //获取到的 jsapi ticket
    private int    expires_in;  //票据有效时间，单位：秒

    private Date createTime;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isExpired() {
        // 提前一分钟将ticket假定为无效，重新获取ticket，以免完全按照 expires_in的时间，导致可能发生的ticket过期的现象
        int preTime = 60 * 1000;
        try {
            return (createTime.getTime() + expires_in * 1000 + preTime) <= System.currentTimeMillis();
        } catch (Exception ex) {
            return true;
        }
    }
}