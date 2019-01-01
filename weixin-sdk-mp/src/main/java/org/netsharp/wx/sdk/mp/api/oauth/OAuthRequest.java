package org.netsharp.wx.sdk.mp.api.oauth;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.util.StringManager;
import org.netsharp.wx.sdk.mp.WebClient;
import org.netsharp.wx.sdk.mp.WeixinException;
import org.netsharp.wx.sdk.mp.api.Request;
import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

/**
 * 如果是要用户确认界面，此过程需要两次调用
 * 1.设置参数后调用GetAuthUrl 用户确认操作后 跳转到RedirectUrl
 * 2.在 RedirectUrl请求中重新构造当前对象  并取得Url中的Code参数  再调用 GerResponse得到信息
 * <p/>
 * 如果没有用户确认界面；
 */
//[Api("OAuth授权")]
public class OAuthRequest extends Request<OAuthResponse> {
    private String appId;
    private String appSecret;
    private OauthScope scope = OauthScope.snsapi_base;
    private String code;
    private String redirectUrl;
    private String state;

    public OAuthRequest() {
        super();
        this.responseType = OAuthResponse.class;
    }

    @Override
    public OAuthResponse getResponse()  {
        WeixinValidation valudateResult = this.validate();

        if (!valudateResult.Succeed) {
            throw new WeixinException(valudateResult.Message);
        }

        return this.doResponse();
    }

    @Override
    protected OAuthResponse doResponse()  {
        this.logger.info("获取access_token，同时得到openid");
        //this.logger.info("scop:"+this.getScope());

        OAuthResponse response = this.executeHttpGet();
        if (response.getScope().equals(OauthScope.snsapi_base.name())) {
            this.logger.info("openid:" + response.getOpenid());

            return response;
        }

        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=%1$s&openid=%2$s&lang=zh_CN";
        url = String.format(url, response.getAccess_token(), response.getOpenid());

        this.logger.info("开始拉取用户信息:" + url);

        WebClient client = new WebClient();
        String    json   = client.downloadString(url);

        this.logger.info("拉取用户结果:" + json);

        response = this.deserialize(json);

        return response;
    }

    /*第一步：微信OAuth授权时得到授权调用的页面，调用此方法，需要设置appid,redirect_uri,state*/
    public String getAuthUrl() throws WeixinException {
        if (StringManager.isNullOrEmpty(this.getAppId())) {
            throw new WeixinException("appid不能为空");
        }

        if (StringManager.isNullOrEmpty(this.getRedirectUrl())) {
            throw new WeixinException("redirect_uri不能为空");
        }

        if (this.getScope() == null) {
            throw new WeixinException("scop不能为空");
        }

        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?";

        List<String> ss = new ArrayList<String>();
        ss.add("appid=" + this.getAppId());
        ss.add("redirect_uri=" + WebClient.UrlEncode(this.getRedirectUrl()));
        ss.add("response_type=" + this.getResponseType());
        ss.add("scope=" + this.getScope());
        ss.add("state=" + this.getState() + "#wechat_redirect"); // 发布者

        url = url + StringManager.join("&", ss);

        return url;
    }

    /*第二步：粉丝授权通过之后，根据openId得到用户的信息*/
    @Override
    public String getUrl() {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%1$s&secret=%2$s&code=%3$s&grant_type=%4$s";
        url = String.format(url, this.getAppId(), this.getAppSecret(), this.getCode(), this.getGrantType());

        return url;
    }

    @Override
    public WeixinValidation validate() {
        if (StringHelper.isNullOrEmpty(this.getAppId())) {
            return WeixinValidation.create(false, "AppId is null");
        }

        if (StringHelper.isNullOrEmpty(this.getAppSecret())) {
            return WeixinValidation.create(false, "AppSecret is null");
        }

        if (StringHelper.isNullOrEmpty(this.getCode())) {
            return WeixinValidation.create(false, " Code is null");
        }

        return super.doValidate();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String value) {
        appId = value;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String value) {
        appSecret = value;
    }

    public String getGrantType() {
        return "authorization_code";
    }

    public String getResponseType() {
        return "code";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        code = value;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String value) {
        redirectUrl = value;
    }

    public OauthScope getScope() {
        return scope;
    }

    public void setScope(OauthScope scope) {
        this.scope = scope;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public enum OauthScope {

        snsapi_base,     //不弹出授权界面，只能获取openid
        snsapi_userinfo  //弹出授权界面
    }
}