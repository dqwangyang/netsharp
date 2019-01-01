package org.netsharp.wx.sdk.tp;

/**
 * 微信相关常量.
 *
 * @author vioao
 */
public class Const {
    public interface Uri {
        String API_URI_HTTP = "http://api.weixin.qq.com";
        String API_URI = "https://api.weixin.qq.com";
        String FILE_URI = "http://file.api.weixin.qq.com";
        String MP_URI = "https://mp.weixin.qq.com";
        String MCH_URI = "https://api.mch.weixin.qq.com";
        String OPEN_URI = "https://open.weixin.qq.com";
    }

    public interface Charset {
        String UTF_8 = "UTF-8";
        String ISO_8859_1 = "iso-8859-1";
    }

    public interface Lang {
        String ZH_CN = "zh_CN";
    }

    public enum InfoType {
        /** 票据 **/
        component_verify_ticket,
        /** 授权 **/
        authorized,
        /** 取消授权 **/
        unauthorized,
        /** 更新权限 **/
        updateauthorized
    }

    public enum  MsgType{
        event
    }

    public enum  Event{
        /** 审核成功 **/
        weapp_audit_success,
        /** 审核失败 **/
        weapp_audit_fail;
    }
}
