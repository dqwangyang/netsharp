package org.netsharp.wx.pa.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

/**
 * 领取红包的用户
 *
 * @author kxh
 */
@Table(name="wx_pa_red_packet_user")
public class RedPacketUser extends WeixinEntity{

	private static final long serialVersionUID = 1743221641972989998L;

	// 关联红包的id
    @Column(name = "redpacket_id")
    long redPacketId;

    // 微信用户和公众号绑定的id
    @Column(name = "wx_openid", size = 32)
    String wxOpenId;

    // 用户的id
    @Column(name = "user_id")
    Long userId;

    // 粉丝的id
    @Column(name = "fans_id")
    Long fansId;

    // 收到的红包金额，单位分
    @Column(name = "total_amount")
    int totalAmount;

	public long getRedPacketId() {
		return redPacketId;
	}

	public void setRedPacketId(long redPacketId) {
		this.redPacketId = redPacketId;
	}

	public String getWxOpenId() {
		return wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFansId() {
		return fansId;
	}

	public void setFansId(Long fansId) {
		this.fansId = fansId;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

}
