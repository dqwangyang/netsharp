package org.netsharp.wx.sdk.mp.api.qrcode;

import org.netsharp.wx.sdk.mp.api.Response;

public class QrCodeResponse extends Response {
    private String ticket;
    private int    expire_seconds;
    private String url;
    private String qrCodeUrl;
    private byte[] qrCodeData;

    public final String getTicket() {
        return ticket;
    }

    public final void setTicket(String value) {
        ticket = value;
    }

    public final int getExpire_seconds() {
        return expire_seconds;
    }

    public final void setExpire_seconds(int value) {
        expire_seconds = value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public final String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public final void setQrCodeUrl(String value) {
        qrCodeUrl = value;
    }

    public final byte[] getQrCodeData() {
        return qrCodeData;
    }

    public final void setQrCodeData(byte[] value) {
        qrCodeData = value;
    }
}