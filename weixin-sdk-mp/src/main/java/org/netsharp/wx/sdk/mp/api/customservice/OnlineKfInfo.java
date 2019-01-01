package org.netsharp.wx.sdk.mp.api.customservice;

public class OnlineKfInfo {
	
    private String kf_account;// 完整客服账号，格式为：账号前缀@公众号微信号
    private int    status;// 客服在线状态 1：pc在线，2：手机在线。若pc和手机同时在线则为 1+2=3
    private String kf_id;// 客服工号
    private int    auto_accept;// 客服设置的最大自动接入数
    private int    accepted_case; // 客服当前正在接待的会话数

    public OnlineKfInfo() {

    }

    public String getKf_account() {
        return kf_account;
    }

    public void setKf_account(String kf_account) {
        this.kf_account = kf_account;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getKf_id() {
        return kf_id;
    }

    public void setKf_id(String kf_id) {
        this.kf_id = kf_id;
    }

    public int getAuto_accept() {
        return auto_accept;
    }

    public void setAuto_accept(int auto_accept) {
        this.auto_accept = auto_accept;
    }

    public int getAccepted_case() {
        return accepted_case;
    }

    public void setAccepted_case(int accepted_case) {
        this.accepted_case = accepted_case;
    }
}