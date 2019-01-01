package org.netsharp.wx.sdk.mp.mch;

/** 
 p12证书需要预先导入系统  暂不清楚多个证书是不是存在冲突
*/
public class RefundRequest// extends MchRequest<MchResponse>
{
//	private String privateTransId;
//	public final String getTransId()
//	{
//		return privateTransId;
//	}
//	public final void setTransId(String value)
//	{
//		privateTransId = value;
//	}
//
//	private String privateOutTradeCode;
//	public final String getOutTradeCode()
//	{
//		return privateOutTradeCode;
//	}
//	public final void setOutTradeCode(String value)
//	{
//		privateOutTradeCode = value;
//	}
//
//	private String privateOutRefTradeCode;
//	public final String getOutRefTradeCode()
//	{
//		return privateOutRefTradeCode;
//	}
//	public final void setOutRefTradeCode(String value)
//	{
//		privateOutRefTradeCode = value;
//	}
//
//	private java.math.BigDecimal privateAmount = new java.math.BigDecimal(0);
//	public final java.math.BigDecimal getAmount()
//	{
//		return privateAmount;
//	}
//	public final void setAmount(java.math.BigDecimal value)
//	{
//		privateAmount = value;
//	}
//
//	private java.math.BigDecimal privateRefundAmount = new java.math.BigDecimal(0);
//	public final java.math.BigDecimal getRefundAmount()
//	{
//		return privateRefundAmount;
//	}
//	public final void setRefundAmount(java.math.BigDecimal value)
//	{
//		privateRefundAmount = value;
//	}
//
//	private String privateUserId;
//	public final String getUserId()
//	{
//		return privateUserId;
//	}
//	public final void setUserId(String value)
//	{
//		privateUserId = value;
//	}
//
//	private byte[] privateCret;
//	public final byte[] getCret()
//	{
//		return privateCret;
//	}
//	public final void setCret(byte[] value)
//	{
//		privateCret = value;
//	}
//
//	@Override
//	protected String getUrl()
//	{
//		return "https://api.mch.weixin.qq.com/secapi/pay/refund";
//	}
//
//	@Override
//	protected MchResponse DoResponse()
//	{
//		java.util.Map<String, String> dic = this.CreatePostData();
//
//		dic.put("out_trade_no", this.getOutTradeCode());
//		dic.put("out_refund_no", this.getOutRefTradeCode());
//		dic.put("total_fee", PayHelp.ToWxPayAmount(this.getAmount()));
//		dic.put("refund_fee", PayHelp.ToWxPayAmount(this.getRefundAmount()));
//		dic.put("op_user_id", this.getUserId());
//		Sign(dic);
//
//		return this.HttpPost(dic);
//
//	}
//
//
//	@Override
//	protected void DoValidate()
//	{
//		super.DoValidate();
//
//
//		if (DotNetToJavaStringHelper.isNullOrEmpty(this.getOutRefTradeCode()))
//		{
//			throw new ArgumentNullException("out refund trade no");
//		}
//
//		if (this.getRefundAmount().compareTo(this.getAmount()) > 0)
//		{
//			throw new WxException("退款金额超过整单金额");
//		}
//
//		if (DotNetToJavaStringHelper.isNullOrEmpty(this.getOutTradeCode()))
//		{
//			throw new ArgumentNullException("out trade no");
//		}
//
//		if (DotNetToJavaStringHelper.isNullOrEmpty(this.getUserId()))
//		{
//			throw new ArgumentNullException("userid");
//		}
//
//		if (this.getCret().length == 0)
//		{
//			throw new ArgumentNullException("cert");
//		}
//
//	}
//
//	@Override
//	protected String OnPosting(String xml)
//	{
//		ServicePointManager.ServerCertificateValidationCallback = new RemoteCertificateValidationCallback(CheckValidationResult);
//
//		X509Certificate cer = new X509Certificate(this.getCret(), this.getMchId());
//		HttpWebRequest webrequest = (HttpWebRequest)HttpWebRequest.Create(this.getUrl());
//		webrequest.ClientCertificates.Add(cer);
//		webrequest.Method = "POST";
//
////C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
//		var s = webrequest.GetRequestStream();
//		StreamWriter sw = new StreamWriter(s);
//		sw.Write(xml);
//		sw.Flush();
//		s.Flush();
//		//s.Close();
//
//		HttpWebResponse webreponse = (HttpWebResponse)webrequest.GetResponse();
//		Stream stream = webreponse.GetResponseStream();
//		String resp = "";
////C# TO JAVA CONVERTER NOTE: The following 'using' block is replaced by its Java equivalent:
////		using (StreamReader reader = new StreamReader(stream))
//		StreamReader reader = new StreamReader(stream);
//		try
//		{
//			resp = reader.ReadToEnd();
//		}
//		finally
//		{
//			reader.dispose();
//		}
//
//		System.out.println(resp);
//
//		return resp;
//	}
//
//	private boolean CheckValidationResult(Object sender, X509Certificate certificate, X509Chain chain, SslPolicyErrors errors)
//	{
//		if (errors == SslPolicyErrors.None)
//		{
//			return true;
//		}
//		return false;
//	}

}