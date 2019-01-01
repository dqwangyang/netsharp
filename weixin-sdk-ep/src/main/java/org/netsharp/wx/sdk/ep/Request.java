package org.netsharp.wx.sdk.ep;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.NetsharpException;
import org.netsharp.util.JsonManage;
import org.netsharp.util.StringManager;
import org.netsharp.util.WebClient;
import org.netsharp.wx.sdk.ep.accesstoken.AccessToken;
import org.netsharp.wx.sdk.ep.accesstoken.AccessTokenManage;
import org.netsharp.wx.sdk.ep.message.WeixinValidation;

public abstract class Request<T extends Response> {

	protected Log logger;
	protected Class<?> responseType = Response.class;
	private AccessToken token;

	public Request() {
		this.logger = LogFactory.getLog(this.getClass().getSimpleName());
	}

	public T getResponse() {

		// this.token =
		// AccessTokenManage.get(this.token.getCorpid(),this.token.getCorpsecret());

		WeixinValidation valudateResult = this.validate();
		if (!valudateResult.Succeed) {
			throw new WeixinEnterpriseAccountException(valudateResult.Message);
		}

		try {
			T t = doResponse();
			return t;
		} catch (NetsharpException ne) {
			throw new WeixinEnterpriseAccountException(ne);
		} catch (Exception e) {
			throw new WeixinEnterpriseAccountException(e);
		}
	}

	public WeixinValidation validate() {
		if (StringManager.isNullOrEmpty(this.getAccessToken())) {
			return WeixinValidation.create(false, "AccessToken is null");
		}

		return doValidate();
	}

	protected T executeHttpGet() {
		WebClient client = new WebClient();

		try {
			logger.info("request 执行httpget调用...");
			return this.doExecuteHttpGet(client);
		} catch (WeixinEnterpriseAccountException we) {
			logger.info("request 执行httpget调用失败！");

			String errorCode = we.getErrorCode();
			if (errorCode.equals("42001") || errorCode.equals("40014") || "40001".equals(errorCode)) {
				logger.info("强制刷新token...");
				this.token = AccessTokenManage.refresh(this.token.getCorpid(), this.token.getCorpsecret());

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

		logger.info("==================>>>:" + json);
		this.onHttpGetting(client);

		T o = this.deserialize(json);

		return o;
	}

	protected T executeHttpPost(String json) {
		WebClient client = new WebClient();

		this.onHttpPosting(client);

		try {
			return this.doExecuteHttpPost(client, json);
		} catch (WeixinEnterpriseAccountException we) {
			// 42001
			String errorCode = we.getErrorCode();
			if (errorCode.equals("42001") || errorCode.equals("40014") || "40001".equals(errorCode)) {
				logger.info("强制刷新token...");
				this.token = AccessTokenManage.refresh(this.token.getCorpid(), this.token.getCorpsecret());

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
	public T deserialize(String json) {
		T response;
		try {
			response = (T) JsonManage.deSerialize2(responseType, json);
		} catch (WeixinEnterpriseAccountException ex) {
			throw ex;
		} catch (Exception ex) {
			logger.error("反序列化异常:" + json);
			throw new WeixinEnterpriseAccountException("反序列化异常", ex);
		}
		response.setJsonString(json);

		String errorCode = response.getErrcode();

		if (!StringManager.isNullOrEmpty(errorCode) && !StringManager.equals(errorCode, "0")) {

			String message = ResponseCodes.get(errorCode);

			message = this.getClass().getName() + "调用异常，编码：" + errorCode + ",错误信息：" + message;

			throw new WeixinEnterpriseAccountException(message, errorCode);

		}
		return response;
	}

	public String serialize(Object request) {
		try {
			String json = JsonManage.serialize(request);

			return json;
		} catch (Exception ex) {
			logger.error("反序列化Response异常", ex);
			throw new WeixinEnterpriseAccountException(ex);
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
		if (this.getToken() != null) {
			return this.getToken().getAccess_token();
		}

		throw new WeixinEnterpriseAccountException("Request.TokenInfo is Null");
	}

	public final AccessToken getToken() {
		return token;
	}

	public void setToken(AccessToken value) {
		token = value;
	}
}