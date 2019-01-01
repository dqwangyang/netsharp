package org.netsharp.wx.sdk.ep.accesstoken;

import org.netsharp.util.JsonManage;
import org.netsharp.util.StringManager;
import org.netsharp.util.WebClient;
import org.netsharp.wx.sdk.ep.Request;
import org.netsharp.wx.sdk.ep.WeixinException;

//
public class AccessTokenRequest extends Request<AccessTokenResponse> {

	public AccessTokenRequest() {
		super();
		this.responseType = AccessTokenResponse.class;
	}

	@Override
	public AccessTokenResponse getResponse() {

		return this.doResponse();
	}

	@Override
	public AccessTokenResponse doResponse() {

		WebClient client = new WebClient();

		String json = client.downloadString(this.getUrl());

		this.logger.trace(json);

		AccessTokenResponse o = this.deserialize(json);

		return o;
	}

	public AccessTokenResponse deserialize(String json) throws WeixinException {
		AccessTokenResponse response;
		try {
			response = (AccessTokenResponse) JsonManage.deSerialize2(responseType,json);
		} catch (WeixinException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new WeixinException("反序列化异常", ex);
		}
		response.setJsonString(json);

		String errorCode = response.getErrcode();

		if (!StringManager.isNullOrEmpty(errorCode) && !errorCode.equals("0")) {
			// String errorMessage = ApiErrors.GetError(errorCode);
			// logger.info("反序列化类型：" + responseType.getName());
			// logger.info("反序列化JSON：" + json);
			// throw new WeixinException(errorCode, errorMessage);
		}
		return response;
	}

	public String getUrl() {

		AccessToken token = this.getToken();

		String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + token.getCorpid() + "&corpsecret=" + token.getCorpsecret();

		return url;
	}
}
