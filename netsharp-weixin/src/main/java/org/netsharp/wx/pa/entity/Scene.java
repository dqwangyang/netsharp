package org.netsharp.wx.pa.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

/*微信持久化的个性二维码共有10w个，此处记录分配的个性二维码的sceneId*/
@Table(name = "wx_pa_scene", header = "参数二维码")
public class Scene extends BizEntity {

	private static final long serialVersionUID = 291588035442418467L;
    @Column(name="weixin_scene_id")
	private Integer weixinSceneId; //自定义的个性二维码处理器
    @Column(name="qr_code_url",size = 200)
    private String  qrCodeUrl;
    @Column(name="json_string",size = 500)
    private String  jsonString;

    private Boolean disabled = false;//停用

    @Column(name = "expire_seconds", defaultValue = "0")
    private int expireSeconds;  // 过期时间，单位秒，为0表示永久有效

    @Column(name="code_type")
    private String  codeType;

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public Integer getWeixinSceneId() {
        return weixinSceneId;
    }

    public void setWeixinSceneId(Integer weixinSceneId) {
        this.weixinSceneId = weixinSceneId;
    }

    public String getQRCodeUrl() {
        return qrCodeUrl;
    }

    public void setQRCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }
}