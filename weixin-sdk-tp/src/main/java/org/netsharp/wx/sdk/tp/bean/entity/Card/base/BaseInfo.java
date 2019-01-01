package org.netsharp.wx.sdk.tp.bean.entity.Card.base;



import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 卡券公共信息-基础信息.
 *
 * @author vioao
 */
@Data
@Builder
public class BaseInfo {

    /**
     * 卡券的商户logo，建议像素为300*300。<br>
     * 添加必填
     */
    private String logoUrl;

    /**
     * 商户名字,字数上限为12个汉字。<br>
     * 添加必填，不支持修改
     */
    private String brandName;

    /**
     * "CODE_TYPE_TEXT"，文本；<br>
     * "CODE_TYPE_BARCODE"，一维码； <br>
     * "CODE_TYPE_QRCODE"，二维码；<br>
     * "CODE_TYPE_ONLY_QRCODE"，二维码无code显示；<br>
     * "CODE_TYPE_ONLY_BARCODE"，一维码无code显示；<br>
     * "CODE_TYPE_NONE"，不显示code和条形码类型<br>
     * 添加必填
     */
    private String codeType;

    /**
     * 卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)。<br>
     * 添加必填
     */
    private String title;

    /**
     * 券名，字数上限为18个汉字。
     */
    private String subTitle;

    /**
     * 券颜色。按色彩规范标注填写Color010-Color100<br>
     * 添加必填
     */
    private String color;

    /**
     * 卡券使用提醒，字数上限为16个汉字。<br>
     * 添加必填
     */
    private String notice;

    /**
     * 客服电话。
     */
    private String servicePhone;

    /**
     * 卡券使用说明，字数上限为1024个汉字。<br>
     * 添加必填
     */
    private String description;

    /**
     * 使用日期，有效期的信息。<br>
     * 添加必填
     */
    private DateInfo dateInfo;

    /**
     * 商品信息。<br>
     * 添加必填，不支持修改
     */
    private Sku sku;

    /**
     * 每人可领券的数量限制,不填写默认为50。
     */
    private Integer getLimit;

    /**
     * 是否自定义Code码。 填写true或false，默认为false。 <br>
     * 通常自有优惠码系统的开发者选择自定义Code码，并在卡券投放时带入 <br>
     * 不支持修改
     */
    private Boolean useCustomCode;

    /**
     * 是否指定用户领取，填写true或false。默认为false。 <br>
     * 通常指定特殊用户群体投放卡券或防止刷券时选择指定用户领取。<br>
     * 不支持修改
     */
    private Boolean bindOpenid;

    /**
     * 卡券领取页面是否可分享。
     */
    private Boolean canShare;

    /**
     * 卡券是否可转赠。
     */
    private Boolean canGiveFriend;

    /**
     * 门店位置poiid。<br>
     * 调用POI门店管理接口获取门店位置poiid。<br>
     * 具备线下门店的商户为必填。<br>
     */
    private List<Integer> locationIdList;

    /**
     * 卡券顶部居中的按钮，如“立即使用”，仅在卡券状态正常(可以核销)时显示
     */
    private String centerTitle;

    /**
     * 显示在入口下方的提示语，如“立即享受优惠”，仅在卡券状态正常(可以核销)时显示。
     */
    private String centerSubTitle;

    /**
     * 顶部居中的url，仅在卡券状态正常(可以核销)时显示。
     */
    private String centerUrl;

    /**
     * 自定义跳转外链的入口名字， 如“立即使用”。详情见活用自定义入口
     */
    private String customUrlName;

    /**
     * 自定义跳转的URL。
     */
    private String customUrl;

    /**
     * 显示在入口右侧的提示语。如“更多惊喜”。
     */
    private String customUrlSubTitle;

    /**
     * 营销场景的自定义入口名称。如，“产品介绍”。
     */
    private String promotionUrlName;

    /**
     * 入口跳转外链的地址链接。
     */
    private String promotionUrl;

    /**
     * 显示在营销入口右侧的提示语。如，“卖场大优惠。”。
     */
    private String promotionUrlSubTitle;

    /**
     * 第三方来源名，例如同程旅游、大众点评。<br>
     * 不支持修改
     */
    private String source;
    
    

    public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DateInfo getDateInfo() {
		return dateInfo;
	}

	public void setDateInfo(DateInfo dateInfo) {
		this.dateInfo = dateInfo;
	}

	public Sku getSku() {
		return sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	public Integer getGetLimit() {
		return getLimit;
	}

	public void setGetLimit(Integer getLimit) {
		this.getLimit = getLimit;
	}

	public Boolean getUseCustomCode() {
		return useCustomCode;
	}

	public void setUseCustomCode(Boolean useCustomCode) {
		this.useCustomCode = useCustomCode;
	}

	public Boolean getBindOpenid() {
		return bindOpenid;
	}

	public void setBindOpenid(Boolean bindOpenid) {
		this.bindOpenid = bindOpenid;
	}

	public Boolean getCanShare() {
		return canShare;
	}

	public void setCanShare(Boolean canShare) {
		this.canShare = canShare;
	}

	public Boolean getCanGiveFriend() {
		return canGiveFriend;
	}

	public void setCanGiveFriend(Boolean canGiveFriend) {
		this.canGiveFriend = canGiveFriend;
	}

	public List<Integer> getLocationIdList() {
		return locationIdList;
	}

	public void setLocationIdList(List<Integer> locationIdList) {
		this.locationIdList = locationIdList;
	}

	public String getCenterTitle() {
		return centerTitle;
	}

	public void setCenterTitle(String centerTitle) {
		this.centerTitle = centerTitle;
	}

	public String getCenterSubTitle() {
		return centerSubTitle;
	}

	public void setCenterSubTitle(String centerSubTitle) {
		this.centerSubTitle = centerSubTitle;
	}

	public String getCenterUrl() {
		return centerUrl;
	}

	public void setCenterUrl(String centerUrl) {
		this.centerUrl = centerUrl;
	}

	public String getCustomUrlName() {
		return customUrlName;
	}

	public void setCustomUrlName(String customUrlName) {
		this.customUrlName = customUrlName;
	}

	public String getCustomUrl() {
		return customUrl;
	}

	public void setCustomUrl(String customUrl) {
		this.customUrl = customUrl;
	}

	public String getCustomUrlSubTitle() {
		return customUrlSubTitle;
	}

	public void setCustomUrlSubTitle(String customUrlSubTitle) {
		this.customUrlSubTitle = customUrlSubTitle;
	}

	public String getPromotionUrlName() {
		return promotionUrlName;
	}

	public void setPromotionUrlName(String promotionUrlName) {
		this.promotionUrlName = promotionUrlName;
	}

	public String getPromotionUrl() {
		return promotionUrl;
	}

	public void setPromotionUrl(String promotionUrl) {
		this.promotionUrl = promotionUrl;
	}

	public String getPromotionUrlSubTitle() {
		return promotionUrlSubTitle;
	}

	public void setPromotionUrlSubTitle(String promotionUrlSubTitle) {
		this.promotionUrlSubTitle = promotionUrlSubTitle;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Data
    @Builder
    public static class DateInfo {
        /**
         * 使用时间的类型，旧文档采用的1和2依然生效。<br>
         * DATE_TYPE_FIX_TIME_RANGE： 表示固定日期区间； <br>
         * DATE_TYPE_FIX_TERM：表示固定时长<br>
         * 添加必填
         */
        private String type;

        /**
         * type为DATE_TYPE_FIX_TIME_RANGE时专用，表示起用时间。<br>
         * 从1970年1月1日00:00:00至起用时间的秒数，<br>
         * 最终需转换为字符串形态传入。（东八区时间，单位为秒） <br>
         * 添加必填
         */
        private Integer beginTimestamp;

        /**
         * 表示结束时间，建议设置为截止日期的23:59:59过期。（东八区时间，单位为秒）<br>
         * 可用于DATE_TYPE_FIX_TERM时间类型，表示卡券统一过期时间，建议设置为截止日期的23:59:59过期。（东八区时间，单位为秒），<br>
         * 设置了fixed_term卡券，当时间达到end_timestamp时卡券统一过期<br>
         * 添加必填
         */
        private Integer endTimestamp;

        /**
         * type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天内有效，不支持填写0。 <br>
         * 添加必填，不支持修改
         */
        private Integer fixedTerm;

        /**
         * type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天开始生效，领取后当天生效填写0。（单位为天）<br>
         * 添加必填，不支持修改
         */
        private Integer fixedBeginTerm;
    }

    @Data
    @Builder
    public static class Sku {
        /**
         * 卡券库存的数量，上限为100000000。<br>
         * 添加必填
         */
        private Integer quantity;
    }
}
