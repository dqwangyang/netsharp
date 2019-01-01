package org.netsharp.wx.sdk.mp.api.jssdk;

import org.netsharp.wx.sdk.mp.api.Request;

/**
 * 获取Jsapi Ticket
 */
public class JsApiTicketRequest extends Request<JsApiTicketResponse> {

    public JsApiTicketRequest() {
        super();
        this.responseType = JsApiTicketResponse.class;
    }

    @Override
    protected JsApiTicketResponse doResponse()  {
        return  this.executeHttpGet();
    }

    @Override
    public String getUrl() {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%1$s&type=jsapi";
        url = String.format(url, this.getAccessToken());

        return url;
    }

    public static void main(String[] args) {
        String              json = "{\"errcode\":0,\"errmsg\":\"ok\",\"ticket\":\"sM4AOVdWfPE4DxkXGEs8VMJMEEszFe5DB7zoIUBto5uy0a_atvVKxyPMxQXPgYR4zmTaarxDDGLTrdJMG694IA\",\"expires_in\":7200}";
        JsApiTicketResponse o    = new JsApiTicketRequest().deserialize(json);

        System.out.println(o.toString());
    }
}