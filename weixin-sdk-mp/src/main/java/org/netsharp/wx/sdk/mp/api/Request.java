package org.netsharp.wx.sdk.mp.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.NetsharpException;
import org.netsharp.util.JsonManage;
import org.netsharp.util.StringManager;
import org.netsharp.wx.sdk.mp.WebClient;
import org.netsharp.wx.sdk.mp.WeixinException;
import org.netsharp.wx.sdk.mp.api.accesstoken.AccessToken;
import org.netsharp.wx.sdk.mp.api.accesstoken.AccessTokenManage;
import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;


public abstract class Request<T extends Response> {
    protected Log logger;
    protected Class<?> responseType = Response.class;
    private AccessToken tokenInfo;

    public Request() {
        this.logger = LogFactory.getLog(this.getClass().getSimpleName());
    }

    public T getResponse() {
    	
        this.setTokenInfo(AccessTokenManage.getTokenByAppId(this.getTokenInfo().getAppId()));

        WeixinValidation valudateResult = this.validate();

        if (!valudateResult.Succeed) {
            throw new WeixinException(valudateResult.Message);
        }

        try {
            T t = doResponse();
            return t;
        } catch (NetsharpException ne) {
            throw new WeixinException(ne);
        } catch (Exception e) {
            throw new WeixinException(e);
        }
    }

    public WeixinValidation validate() {
        if (StringHelper.isNullOrEmpty(this.getAccessToken())) {
            return WeixinValidation.create(false, "AccessToken is null");
        }

        return doValidate();
    }

    protected T executeHttpGet() {
        WebClient client = new WebClient();

        try {
            logger.info("request 执行httpget调用...");
            return this.doExecuteHttpGet(client);
        } catch (WeixinException we) {
            logger.info("request 执行httpget调用失败！");

            String errorCode = we.getErrorCode();
            if (errorCode.equals("42001") || errorCode.equals("40014") || "40001".equals(errorCode)) {
                logger.info("强制刷新token...");
                this.setTokenInfo(AccessTokenManage.refreshToken(this.getTokenInfo().getAppId()));

                return this.doExecuteHttpGet(client);
            } else {
                this.logger.error(we);
                throw we;
            }
        }
    }

    private T doExecuteHttpGet(WebClient client) {

        String url = this.getUrl();
        logger.info("调用weixin api:" + url);
        String json = client.downloadString(url);

        logger.info("json:" + json);
        this.onHttpGetting(client);

        T o = this.deserialize(json);

        return o;
    }

    protected T executeHttpPost(String json) {
        WebClient client = new WebClient();

        this.onHttpPosting(client);

        try {
            return this.doExecuteHttpPost(client, json);
        } catch (WeixinException we) {
            //42001
            String errorCode = we.getErrorCode();
            if (errorCode.equals("42001") || errorCode.equals("40014") || "40001".equals(errorCode)) {
                logger.info("强制刷新token...");
                AccessToken token = AccessTokenManage.refreshToken(this.getTokenInfo().getAppId());
                this.setTokenInfo(token);

                return this.doExecuteHttpPost(client, json);
            } else {
                this.logger.error(we);
                throw we;
            }
        }
    }

    private T doExecuteHttpPost(WebClient client, String json) {

        String url = this.getUrl();

        json = client.uploadString(url, json);
        T response = this.deserialize(json);
        return response;
    }

    @SuppressWarnings("unchecked")
    public T deserialize(String json) throws WeixinException {
        T response;
        try {
            response = (T) JsonManage.deSerialize2(responseType,json);
        } catch (WeixinException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new WeixinException("反序列化异常", ex);
        }
        response.setJsonString(json);

        String errorCode = response.getErrcode();
        String errmsg    = response.getErrmsg();

        if (!StringManager.isNullOrEmpty(errorCode) && !errorCode.equals("0")) {
            String errorMessage = ApiErrors.GetError(errorCode);
           
            throw new WeixinException(errorCode, errmsg,errorMessage);
        }
        return response;
    }

    public String serialize(Object request) throws WeixinException {
        try {
            String json = JsonManage.serialize(request);

            return json;
        } catch (Exception ex) {
            logger.error("反序列化Response异常", ex);
            throw new WeixinException(ex);
        }
    }

    protected WeixinValidation doValidate() {
        return WeixinValidation.create(true, "");
    }

    public abstract String getUrl();

    protected abstract T doResponse();

    protected void onHttpPosting(WebClient webClient) {

    }

    protected void onHttpGetting(WebClient webClient) {

    }

    protected String getAccessToken() {
        if (this.getTokenInfo() != null) {
            return this.getTokenInfo().getToken();
        }

        throw new WeixinException("Request.TokenInfo is Null");
    }

    public final AccessToken getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(AccessToken value) {
        tokenInfo = value;
    }
}