package org.netsharp.wx.sdk.tp.bean.response.template;

import java.util.List;

import org.netsharp.wx.sdk.tp.bean.entity.template.Template;
import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

/**
 * 模板列表.
 *
 * @author vioao
 */
public class TemplateListResponse extends BaseResponse {
    private List<Template> templateList;

    public List<Template> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<Template> templateList) {
        this.templateList = templateList;
    }

    @Override
    public String toString() {
        return "TemplateListResponse{"
                + "templateList=" + templateList
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
