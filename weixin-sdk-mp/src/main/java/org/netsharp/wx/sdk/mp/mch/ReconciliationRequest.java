package org.netsharp.wx.sdk.mp.mch;

// 下载对帐单
public class ReconciliationRequest// extends MchRequest<ReconciliationResponse>
{
//	@Override
//	protected String getUrl()
//	{
//		return "https://api.mch.weixin.qq.com/pay/downloadbill";
//	}
//
//	private DateTimeOffset privateBillDate;
//	public final DateTimeOffset getBillDate()
//	{
//		return privateBillDate;
//	}
//	public final void setBillDate(DateTimeOffset value)
//	{
//		privateBillDate = value;
//	}
//
//	@Override
//	protected ReconciliationResponse DoResponse()
//	{
//		java.util.Map<String, String> dic = this.CreatePostData();
//
//		dic.put("bill_date", getBillDate().ToString("yyyyMMdd"));
//		dic.put("bill_type", "ALL");
//		Sign(dic);
//
//		return this.HttpPost(dic);
//	}
//
//	@Override
//	protected void DoValidate()
//	{
//		if (this.getBillDate() == null)
//		{
//			throw new ArgumentNullException("bill date");
//		}
//	}
}