package org.netsharp.wx.sdk.mp.api.accesstoken;

import org.netsharp.core.NetsharpException;
import org.netsharp.wx.sdk.mp.WebClient;
import org.netsharp.wx.sdk.mp.WeixinException;
import org.netsharp.wx.sdk.mp.api.Request;
import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

public class AccessTokenRequest extends Request<AccessTokenResponse> {
    private String grantType = "client_credential";

    public AccessTokenRequest() {
        super();
        this.responseType = AccessTokenResponse.class;
    }

    public String getTokenStr() {
        AccessTokenResponse response = this.getResponse();
        return response.getAccess_token();
    }

    /*正常的请求会处理access_token，调用父类会死循环*/
    @Override
    public AccessTokenResponse getResponse()  {
        WeixinValidation valudateResult = this.validate();

        if (!valudateResult.Succeed) {
            throw new WeixinException(valudateResult.Message);
        }

        try {
            return doResponse();
        } catch (NetsharpException ne) {
            throw new WeixinException("获取AccessToken异常", ne);
        } catch (Exception e) {
            throw new WeixinException("获取AccessToken异常", e);
        }
    }

    @Override
    protected AccessTokenResponse doResponse()  {
        WebClient client = new WebClient();

        String json = client.downloadString(this.getUrl());

        this.logger.trace(json);

        AccessTokenResponse o;

        o = this.deserialize(json);

        return o;
    }

    @Override
    public WeixinValidation validate() {
        if (StringHelper.isNullOrEmpty(this.getAppId())) {
            return WeixinValidation.create(false, "AppId is null");
        }

        if (StringHelper.isNullOrEmpty(this.getAppSecret())) {
            return WeixinValidation.create(false, "AppSecret is null");
        }

        return WeixinValidation.create(true, "");
    }

    @Override
    public String getUrl() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=%1$s&appid=%2$s&secret=%3$s";
        url = String.format(url, this.getGrantType(), this.getAppId(), this.getAppSecret());

        return url;
    }

    @Override
    protected WeixinValidation doValidate() {
        return null;
    }

    protected final String getAppId() {
        if (this.getTokenInfo() == null) {
            return null;
        }
        return this.getTokenInfo().getAppId();
    }

    protected final String getAppSecret() {
        if (this.getTokenInfo() == null) {
            return null;
        }
        return this.getTokenInfo().getAppSecret();
    }

    public final String getGrantType() {
        return grantType;
    }
}