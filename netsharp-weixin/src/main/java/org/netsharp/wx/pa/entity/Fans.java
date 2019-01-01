package org.netsharp.wx.pa.entity;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.util.StringManager;
import org.netsharp.wx.pa.dic.FansStatus;

/*微信粉丝*/
@Table(name = "wx_pa_fans", header = "粉丝")
public class Fans extends WeixinEntity {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -3423906119590934504L;

	@Column(name = "openid", header = "openId")
    private String openId;
    
	@Column(name = "nickname", header = "昵称")
    private String nickname;
    
	@Column(name = "sex", header = "标题")
    private String sex;
    
	@Column(name = "country", header = "国家")
    private String country;
    
	@Column(name = "province", header = "省份")
    private String province;
    
	@Column(name = "city", header = "城市")
    private String city;
    
    @Column(name = "head_img_url",size = 1000, header = "头像")
    private String headImgUrl;
    
    @Column(name = "privilege",size = 500, header = "privilege")
    private String privilege;

    @Column(name = "phone", header = "手机号")
    private String phone;
    
    @Column(name = "mac", header = "mac")
    private String mac;
    
    @Column(name = "ip", header = "ip")
    private String ip;
    
    @Column(name = "address", size = 1000,header = "详细地址")
    private String address;
    
    @Column(name = "email", header = "邮箱")
    private String email;
    
    @Column(name = "pwd", header = "密码")
    private String pwd;
    
    @Column(name = "status", header = "状态")
    private FansStatus status = FansStatus.unsubscribe;
    
    @Column(name = "subscribe_date", header = "首次关注时间")
    private Date    subscribeDate;
    
    @Column(name = "last_subscribe_date", header = "最后关注时间")
    private Date    lastSubscribeDate;
    
    @Column(name = "un_subscribe_date", header = "最近一次取消关注的日期")
    private Date    unSubscribeDate;
    
    @Column(name = "id_card", header = "身份证号码")
    private String  idCard;
    
    @Column(name = "user_id", header = "用户Id")
    private Integer userId;
    
    @Column(name = "sub_member", header = "是否会员")
    private Boolean subMember;
    
    @Column(name = "scene_id", header = "个性二维码")
    private Integer sceneId;
    
    @Column(name = "parent_scene_id", header = "上次场景Id")
    private Integer parentSceneId;
    
    @Column(name = "parent_id", header = "parentId")
    private Integer parentId = null;
    
    @Column(name = "member", header = "是否是会员")
    private Boolean member = false; 
    
    @Column(name = "lbs_latitude", header = "地理位置维度")
    private Double lbsLatitude;
    
    @Column(name = "lbs_Integeritude", header = "地理位置经度")
    private Double lbsIntegeritude;
    
    @Column(name = "lbs_precision", header = "地理位置精度")
    private Double lbsPrecision;
    
    @Column(name = "lbs_address",size = 500, header = "地理位置地址")
    private String lbsAddress;
    
    @Column(name = "lbs_date_time", header = "地理位置时间")
    private Date   lbsDateTime;
    
    @Column(name = "unionid", header = "编号")
    private String unionid;
    
    @Column(name = "language", header = "语言")
    private String language;

    // 用户最后一次扫码的场景值
    @Column(name = "last_scan_sceneid", defaultValue = "0")
    private int  lastScanSceneId;
    
    @Column(name = "last_scan_date", defaultValue = "0000-00-00 00:00:00")
    private Date lastScanDate;
    
    // 用户最近一次扫码关注时的场景值，如果不存在则记录0
    @Column(name = "last_subscribe_sceneid", defaultValue = "0")
    private int  lastSubscribeSceneId;

    // 邀请码，如果不存在为空
    @Column(name = "invite_code", defaultValue = "")
    private String inviteCode = "";

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        nickname = StringManager.filterEmojiChars(nickname, ".");
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FansStatus getStatus() {
        return status;
    }

    public void setStatus(FansStatus status) {
        this.status = status;
    }

    public Date getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(Date subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    public Integer getParentSceneId() {
        return parentSceneId;
    }

    public void setParentSceneId(Integer parentSceneId) {
        this.parentSceneId = parentSceneId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getLastSubscribeDate() {
        return lastSubscribeDate;
    }

    public void setLastSubscribeDate(Date lastSubscribeDate) {
        this.lastSubscribeDate = lastSubscribeDate;
    }

    public int getLastSubscribeSceneId() {
        return lastSubscribeSceneId;
    }

    public void setLastSubscribeSceneId(int lastSubscribeSceneId) {
        this.lastSubscribeSceneId = lastSubscribeSceneId;
    }

    public Date getUnSubscribeDate() {
        return unSubscribeDate;
    }

    public void setUnSubscribeDate(Date unSubscribeDate) {
        this.unSubscribeDate = unSubscribeDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getLbsAddress() {
        return lbsAddress;
    }

    public void setLbsAddress(String lbsAddress) {
        this.lbsAddress = lbsAddress;
    }

    public Date getLbsDateTime() {
        return lbsDateTime;
    }

    public void setLbsDateTime(Date lbsDateTime) {
        this.lbsDateTime = lbsDateTime;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getLbsLatitude() {
        return lbsLatitude;
    }

    public void setLbsLatitude(Double lbsLatitude) {
        this.lbsLatitude = lbsLatitude;
    }

    public Double getLbsIntegeritude() {
        return lbsIntegeritude;
    }

    public void setLbsIntegeritude(Double lbsIntegeritude) {
        this.lbsIntegeritude = lbsIntegeritude;
    }

    public Double getLbsPrecision() {
        return lbsPrecision;
    }

    public void setLbsPrecision(Double lbsPrecision) {
        this.lbsPrecision = lbsPrecision;
    }

    public Date getLastScanDate() {
        return lastScanDate;
    }

    public void setLastScanDate(Date lastScanDate) {
        this.lastScanDate = lastScanDate;
    }

    public int getLastScanSceneId() {
        return lastScanSceneId;
    }

    public void setLastScanSceneId(int lastScanSceneId) {
        this.lastScanSceneId = lastScanSceneId;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

	public Boolean getSubMember() {
		return subMember;
	}

	public void setSubMember(Boolean subMember) {
		this.subMember = subMember;
	}

	public Boolean getMember() {
		return member;
	}

	public void setMember(Boolean member) {
		this.member = member;
	}
}
