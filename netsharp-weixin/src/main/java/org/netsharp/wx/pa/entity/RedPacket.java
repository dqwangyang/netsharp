package org.netsharp.wx.pa.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

/**
 * 红包活动模型
 */
@Table(name = "wx_pa_red_packet")
public class RedPacket extends WeixinEntity {


	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -2343353616451639648L;

	// 本次活动预设的可发放的红包总额，单位分
    @Column(name = "amount")
    private int amount;

    // 已发放的红包金额，单位分
    @Column(name = "consume_amount")
    private int consumeAmount;

    // 活动状态，0表示正常，1表示活动提前结束（提示红包已领完），2表示红包已真正领完
    @Column(name = "status")
    private String status;

    // 提供方名称 < 32
    @Column(name = "nickname", size = 32)
    private String nickname;

    // 商户名称：红包发送者名称 < 32
    @Column(name = "send_name", size = 32)
    private String sendName;

    // 付款金额，单位分，和最大最小金额是相斥存在
    @Column(name = "total_amount")
    private int totalAmount;

    // 最小红包金额，单位分
    @Column(name = "min_value")
    private int minValue;

    // 最大红包金额，单位分
    @Column(name = "max_value")
    private int maxValue;

    // 红包发放总人数
    @Column(name = "total_num")
    private int totalNum;

    // 红包祝福语 < 128
    @Column(name = "wishing", size = 128)
    private String wishing;

    // 活动名称 < 32
    @Column(name = "act_name", size = 32)
    private String actName;

    // 备注 < 256
    @Column(name = "remark", size = 256)
    private String remark;

    // 商户logo的url < 128
    @Column(name = "logo_imgurl", size = 128)
    private String logoImgUrl;

    // 分享文案 < 256
    @Column(name = "share_content", size = 256)
    private String shareContent;

    // 分享的链接 < 128
    @Column(name = "share_url", size = 128)
    private String shareUrl;

    // 分享的图片 < 128
    @Column(name = "share_imgurl", size = 128)
    private String shareImgUrl;

    // 调用接口机器的ip
    @Column(name = "client_ip", size = 15)
    private String clientIp;


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLogoImgUrl() {
        return logoImgUrl;
    }

    public void setLogoImgUrl(String logoImgUrl) {
        this.logoImgUrl = logoImgUrl;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getShareImgUrl() {
        return shareImgUrl;
    }

    public void setShareImgUrl(String shareImgUrl) {
        this.shareImgUrl = shareImgUrl;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(int consumeAmount) {
        this.consumeAmount = consumeAmount;
    }
}
