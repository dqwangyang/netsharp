package org.netsharp.wx.sdk.tp.bean.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO
 * @date 2018/7/17 10:49
 */
public class BaseBean {
    private boolean success = true;
    private String message;
    private @JsonProperty("watermark")
    Watermark watermark;

    public static class Watermark {
        private @JsonProperty("timestamp")
        long timestamp;
        private @JsonProperty("appid")
        String appid;

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }
    }

    public BaseBean() {
    }

    public BaseBean(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Watermark getWatermark() {
        return watermark;
    }

    public void setWatermark(Watermark watermark) {
        this.watermark = watermark;
    }
}
