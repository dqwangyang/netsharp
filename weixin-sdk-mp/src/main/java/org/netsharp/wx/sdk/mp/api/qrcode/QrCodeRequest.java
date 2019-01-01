package org.netsharp.wx.sdk.mp.api.qrcode;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.netsharp.core.NetsharpException;
import org.netsharp.wx.sdk.mp.api.Request;
import org.netsharp.wx.sdk.mp.message.WeixinValidation;


//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//[Api("生成带参数二维码")]
public class QrCodeRequest extends Request<QrCodeResponse> {
    private static String QR_CODE_API_CREATE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%1$s";
    private static String QR_CODE_API_SHOW   = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%1$s";

    // senceId 和 senceStr 任选其一
    private Integer senceId;
    private String  senceStr;
    // 临时二维码有效时间，以秒为单位，最大不超过7天；永久二维码为0
    private Integer expireSeconds = 0;

    public QrCodeRequest() {
        super();
        this.setExpireSeconds(null);
        this.responseType = QrCodeResponse.class;
    }

    public Integer getSenceId() {
        return senceId;
    }

    public void setSenceId(Integer senceId) {
        this.senceId = senceId;
    }

    public final Integer getExpireSeconds() {
        return expireSeconds;
    }

    public final void setExpireSeconds(Integer value) {
        expireSeconds = value;
    }

    public String getSenceStr() {
        return senceStr;
    }

    public void setSenceStr(String senceStr) {
        this.senceStr = senceStr;
    }

    @Override
    public String getUrl() {
        return String.format(QR_CODE_API_CREATE, this.getAccessToken());
    }

    @Override
    protected WeixinValidation doValidate() {
        String senceStr = this.getSenceStr();
        if (StringUtils.isBlank(senceStr) && getSenceId() == null) {
            throw new NetsharpException("缺失场景sence参数，senece_str或sence_id至少要存在一个");
        }
        if (StringUtils.isNotBlank(senceStr) && senceStr.trim().length() > 64) {
            throw new NetsharpException("场景sence_str参数不合法，只能是64位以内的字符串：" + senceStr);
        } else {
            int expireSeconds = getExpireSeconds() == null ? 0 : getExpireSeconds();
            int sceneId = getSenceId() == null ? 0 : getSenceId();

            if (expireSeconds <= 0 && (sceneId > 100000 || sceneId <= 0)) {
                return WeixinValidation.create(false, "sceneId 合法值为 1--100000，当前值：" + getSenceId() + "");
            }
        }

        return super.doValidate();
    }

    @Override
    protected QrCodeResponse doResponse() {
        String         json = this.CreateJson();
        QrCodeResponse qr   = this.executeHttpPost(json);

        qr.setQrCodeUrl(String.format(QR_CODE_API_SHOW, qr.getTicket()));

        //下载会报错，实际也没有用到data，先注掉试下
//        WebClient client = new WebClient();
//        qr.setQrCodeData(client.downloadData(qr.getQrCodeUrl(), null));

        return qr;
    }

    private String CreateJson() {
        Map<String, Object> data = new HashMap<String, Object>();
        if (this.getExpireSeconds() != null && this.getExpireSeconds() > 0) {
            data.put("expire_seconds", this.getExpireSeconds());
            if (StringUtils.isNotBlank(this.getSenceStr())) {
                //携带str参数
                data.put("action_name","QR_STR_SCENE");
            }else{
                data.put("action_name", "QR_SCENE");
            }
        } else {
            data.put("action_name", "QR_LIMIT_SCENE");
        }

        Map<String, Object> scene = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(this.getSenceStr())) {
            scene.put("scene_str", this.getSenceStr());
        } else {
            scene.put("scene_id", this.getSenceId());
        }

        Map<String, Object> action = new HashMap<String, Object>();
        action.put("scene", scene);

        data.put("action_info", action);

        return this.serialize(data);
    }
}