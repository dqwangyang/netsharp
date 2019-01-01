package org.netsharp.wx.sdk.tp.bean.xml.in;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author vioao
 */
@XmlRootElement(name = "xml")
public class InMessage {

    ///////////////////////
    // 以下都是微信推送过来的消息的xml的element所对应的属性.
    ///////////////////////
    @XmlElement(name = "ToUserName")
    private String toUserName;
    @XmlElement(name = "FromUserName")
    private String fromUserName;
    @XmlElement(name = "CreateTime")
    private Long createTime;
    @XmlElement(name = "MsgType")
    private String msgType;
    @XmlElement(name = "MsgId")
    private String msgId;
    @XmlElement(name = "Event")
    private String event;
    @XmlElement(name = "EventKey")
    private String eventKey;
    @XmlElement(name = "content")
    private String content;
    @XmlElement(name = "PicUrl")
    private String picUrl;
    @XmlElement(name = "MediaId")
    private String mediaId;
    @XmlElement(name = "Format")
    private String format;
    @XmlElement(name = "ThumbMediaId")
    private String thumbMediaId;
    @XmlElement(name = "LocationX")
    private String locationX;
    @XmlElement(name = "LocationY")
    private String locationY;
    @XmlElement(name = "Scale")
    private String scale;
    @XmlElement(name = "Label")
    private String label;
    @XmlElement(name = "Title")
    private String title;
    @XmlElement(name = "Description")
    private String description;
    @XmlElement(name = "Url")
    private String url;
    @XmlElement(name = "Ticket")
    private String ticket;
    @XmlElement(name = "Latitude")
    private String latitude;
    @XmlElement(name = "Longitude")
    private String longitude;
    @XmlElement(name = "Precision")
    private String precision;
    @XmlElement(name = "Recognition")
    private String recognition;
    @XmlElement(name = "MenuId")
    private String menuId;
    @XmlElement(name = "Reason")
    private String reason;//失败原因 - 审核
    @XmlElement(name = "SuccTime")
    private long succTime;//审核成功

    ///////////////////////////////////////
    // 群发消息返回的结果
    ///////////////////////////////////////

    // 群发的结果
    private String status;

    // group_id下粉丝数；或者openid_list中的粉丝数.
    private Integer totalCount;

    // 过滤（过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的过滤）后，准备发送的粉丝数
    // 原则上，filterCount = sentCount + errorCount
    private Integer filterCount;
    // 发送成功的粉丝数
    private Integer sentCount;

    // 发送失败的粉丝数
    private Integer errorCount;

    ///////////////////////////////////////
    // 客服会话管理相关事件推送
    ///////////////////////////////////////

    // 创建或关闭客服会话时的客服帐号
    private String kfAccount;

    // 转接客服会话时的转入客服帐号
    private String toKfAccount;

    // 转接客服会话时的转出客服帐号
    private String fromKfAccount;

    ///////////////////////////////////////
    // 门店审核事件推送
    ///////////////////////////////////////

    // 商户自己内部ID，即字段中的sid
    private String storeUniqId;

    // 微信的门店ID，微信内门店唯一标示ID
    private String poiId;

    // 审核结果，成功succ 或失败fail
    private String result;

    // 成功的通知信息，或审核失败的驳回理由
    private String msg;

    ///////////////////////////////////////
    // 微信认证事件推送
    ///////////////////////////////////////
    /**
     * ExpiredTime
     * 资质认证成功/名称认证成功: 有效期 (整形)，指的是时间戳，将于该时间戳认证过期.
     * 年审通知: 有效期 (整形)，指的是时间戳，将于该时间戳认证过期，需尽快年审.
     * 认证过期失效通知: 有效期 (整形)，指的是时间戳，表示已于该时间戳认证过期，需要重新发起微信认证.
     */
    private Long expiredTime;

    // 失败发生时间 (整形)，时间戳
    private Long failTime;

    // 认证失败的原因
    private String failReason;
    @XmlTransient
    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }
    @XmlTransient
    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }
    @XmlTransient
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    @XmlTransient
    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
    @XmlTransient
    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    @XmlTransient
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
    @XmlTransient
    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
    @XmlTransient
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @XmlTransient
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    @XmlTransient
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    @XmlTransient
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
    @XmlTransient
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
    @XmlTransient
    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }
    @XmlTransient
    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }
    @XmlTransient
    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }
    @XmlTransient
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    @XmlTransient
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @XmlTransient
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @XmlTransient
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @XmlTransient
    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    @XmlTransient
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    @XmlTransient
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    @XmlTransient
    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }
    @XmlTransient
    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }
    @XmlTransient
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    @XmlTransient
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @XmlTransient
    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    @XmlTransient
    public Integer getFilterCount() {
        return filterCount;
    }

    public void setFilterCount(Integer filterCount) {
        this.filterCount = filterCount;
    }
    @XmlTransient
    public Integer getSentCount() {
        return sentCount;
    }

    public void setSentCount(Integer sentCount) {
        this.sentCount = sentCount;
    }
    @XmlTransient
    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }
    @XmlTransient
    public String getKfAccount() {
        return kfAccount;
    }

    public void setKfAccount(String kfAccount) {
        this.kfAccount = kfAccount;
    }
    @XmlTransient
    public String getToKfAccount() {
        return toKfAccount;
    }

    public void setToKfAccount(String toKfAccount) {
        this.toKfAccount = toKfAccount;
    }
    @XmlTransient
    public String getFromKfAccount() {
        return fromKfAccount;
    }

    public void setFromKfAccount(String fromKfAccount) {
        this.fromKfAccount = fromKfAccount;
    }
    @XmlTransient
    public String getStoreUniqId() {
        return storeUniqId;
    }

    public void setStoreUniqId(String storeUniqId) {
        this.storeUniqId = storeUniqId;
    }
    @XmlTransient
    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }
    @XmlTransient
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    @XmlTransient
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    @XmlTransient
    public Long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
    }
    @XmlTransient
    public Long getFailTime() {
        return failTime;
    }

    public void setFailTime(Long failTime) {
        this.failTime = failTime;
    }
    @XmlTransient
    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
    @XmlTransient
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    @XmlTransient
    public long getSuccTime() {
        return succTime;
    }

    public void setSuccTime(long succTime) {
        this.succTime = succTime;
    }
}
