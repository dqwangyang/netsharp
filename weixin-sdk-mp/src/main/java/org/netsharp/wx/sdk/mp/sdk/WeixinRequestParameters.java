package org.netsharp.wx.sdk.mp.sdk;

import org.netsharp.util.StringManager;

/// <summary>
/// 请求参数
/// </summary>
public class WeixinRequestParameters {
	
    /// <summary>
    /// 微信加密签名
    /// </summary>
    public String Signature;

    /// <summary>
    /// 时间戳
    /// </summary>
    public String Timestamp;

    /// <summary>
    /// 随机数
    /// </summary>    
    public String Nonce;

    /// <summary>
    /// 随机字符串
    /// </summary>
    public String Echostr;

    /// <summary>
    /// Token Key
    /// </summary>
    public String Token;

    public String Oid;
    
    @Override
    public String toString(){
    	
    	StringBuilder builder=new StringBuilder("微信签名信息："+StringManager.NewLine);
    	
    	builder.append("Signature: ").append(this.Signature).append(StringManager.NewLine);
    	builder.append("Timestamp: ").append(this.Timestamp).append(StringManager.NewLine);
    	builder.append("Nonce: ").append(this.Nonce).append(StringManager.NewLine);
    	builder.append("Token: ").append(this.Token).append(StringManager.NewLine);
    	builder.append("Echostr: ").append(this.Echostr).append(StringManager.NewLine);
    	builder.append("Oid: ").append(this.Oid).append(StringManager.NewLine);
    	
    	return builder.toString();
    }
}
