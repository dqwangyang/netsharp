package org.netsharp.wx.sdk.tp.bean.entity.component.wxa;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 适用于需要的码数量极多的业务场景
 * @date 2018/6/27 20:23
 */
public class GetWxaCodeUnlimit {
    private String scene;
    private String page;
    private Integer width;
    private Boolean autoColor;
    private LineColor lineColor;
    private Boolean isHyaline;

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Boolean getAutoColor() {
        return autoColor;
    }

    public void setAutoColor(Boolean autoColor) {
        this.autoColor = autoColor;
    }

    public LineColor getLineColor() {
        return lineColor;
    }

    public void setLineColor(LineColor lineColor) {
        this.lineColor = lineColor;
    }

    public Boolean getIsHyaline() {
        return this.isHyaline;
    }

    public void setIsHyaline(Boolean hyaline) {
        this.isHyaline = hyaline;
    }
}
