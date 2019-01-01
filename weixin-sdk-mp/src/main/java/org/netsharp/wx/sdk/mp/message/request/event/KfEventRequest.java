package org.netsharp.wx.sdk.mp.message.request.event;

/**
 * Created by kxh on 2015/3/26.
 */
public class KfEventRequest extends EventRequest {
    private String kfAccount;
    private String toKfAccount;
    private String fromKfAccount;

    public String getKfAccount() {
        return kfAccount;
    }

    public void setKfAccount(String kfAccount) {
        this.kfAccount = kfAccount;
    }

    public String getToKfAccount() {
        return toKfAccount;
    }

    public void setToKfAccount(String toKfAccount) {
        this.toKfAccount = toKfAccount;
    }

    public String getFromKfAccount() {
        return fromKfAccount;
    }

    public void setFromKfAccount(String fromKfAccount) {
        this.fromKfAccount = fromKfAccount;
    }
}
