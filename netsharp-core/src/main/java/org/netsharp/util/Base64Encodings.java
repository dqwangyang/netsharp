//package org.netsharp.core.util;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//
//public class Base64Encodings {
//	/**
//	 * 解码
//	 * 
//	 * @param str
//	 * @return string
//	 */
//	public static String decode(String str) {
//		String result = "";
//		byte[] bt = null;
//		try {
//			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
//			bt = decoder.decodeBuffer(str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		try {
//			result = new String(bt, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
//	}
//}
