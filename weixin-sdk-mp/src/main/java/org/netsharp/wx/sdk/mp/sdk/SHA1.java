/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 * 
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package org.netsharp.wx.sdk.mp.sdk;

import java.security.MessageDigest;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * SHA1 class
 *
 * 计算公众平台的消息签名接口.
 */
public class SHA1 {
	
	/**
	 * 用SHA1算法生成安全签名
	 * @param token 票据
	 * @param timestamp 时间戳
	 * @param nonce 随机字符串
	 * @return 安全签名
	 * @throws AesException 
	 */
	public static String getSHA1(WeixinRequestParameters par) throws AesException
	{
		String[] array = new String[] { par.Token, par.Timestamp, par.Nonce };
		
		return getSHA1(array);
	}

	/**
	 * 用SHA1算法生成安全签名
	 * @param token 票据
	 * @param timestamp 时间戳
	 * @param nonce 随机字符串
	 * @param encrypt 密文
	 * @return 安全签名
	 * @throws AesException 
	 */
	public static String getSHA1(String token, String timestamp, String nonce, String encrypt) throws AesException
	{
		String[] array = new String[] { token, timestamp, nonce, encrypt };
		
		return getSHA1(array);
	}
	
	public static String getSHA1(String[] array) throws AesException
	{
		try {
			
			StringBuffer sb = new StringBuffer();
			// 字符串排序
			Arrays.sort(array);
			for (int i = 0; i < array.length; i++) {
				sb.append(array[i]);
			}
			String str = sb.toString();
			// SHA1签名生成
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes());
			byte[] digest = md.digest();
		
			StringBuffer hexstr = new StringBuffer();
			String shaHex = "";
			for (int i = 0; i < digest.length; i++) {
				shaHex = Integer.toHexString(digest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexstr.append(0);
				}
				hexstr.append(shaHex);
			}
			return hexstr.toString();
		} catch (Exception e) {
			
			Log logger = LogFactory.getLog(SHA1.class);
			logger.error(null,e);
			
			throw new AesException(AesException.ComputeSignatureError);
		}
	}
}
