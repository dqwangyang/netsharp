package org.netsharp.wx.sdk.tp.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.netsharp.wx.sdk.tp.Const;

/**
 * @author vioao
 */
public class UrlUtils {
    public static String encode(String str) {
        try {
            str = URLEncoder.encode(str, Const.Charset.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return str;
    }
}
