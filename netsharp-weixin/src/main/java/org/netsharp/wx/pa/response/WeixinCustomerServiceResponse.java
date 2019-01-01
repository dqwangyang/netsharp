package org.netsharp.wx.pa.response;

import org.netsharp.wx.pa.IWeixinResponsor;
import org.netsharp.wx.sdk.mp.message.RequestMessage;
import org.netsharp.wx.sdk.mp.message.ResponseMessage;
import org.netsharp.wx.sdk.mp.message.response.McsResponse;

/*客服系统处理*/
public class WeixinCustomerServiceResponse implements IWeixinResponsor {
	
//	private PublicAccount pa = null;

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public void setKey(String value) {
	}

	@Override
	public boolean validate(RequestMessage request) {
		
		//暂时不启用这个功能
		return false;
		
//		pa = PublicAccountManager.getInstance().get(request.getToUserName()).getAccount();
//		if(pa.getPublicAccountType()!=PublicAccountType.service){
//			return false;
//		}
//		
//		ICustomService customService = ServiceFactory.create(ICustomService.class);
//		int count = customService.getOnlineKfNums(pa.getOriginalId());
//		
//		return count>0;
	}

	@Override
	public ResponseMessage response(RequestMessage request){
		
        // 将用户消息转发到多客服系统
        return new McsResponse(request);
	}

}
