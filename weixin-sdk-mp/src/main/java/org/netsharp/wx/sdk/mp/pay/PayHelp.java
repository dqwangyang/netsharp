package org.netsharp.wx.sdk.mp.pay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import org.netsharp.util.Encodings;
import org.netsharp.util.StringManager;
import org.netsharp.wx.sdk.mp.WeixinException;
import org.netsharp.wx.sdk.mp.util.MD5Util;

public class PayHelp {
    public static String getMd5Sign(Map<String, String> kvs, String key) {
        SortedMap<String, String> sdic = new TreeMap<String, String>(kvs);

        ArrayList<String> sb = new ArrayList<String>();

        for (String k : sdic.keySet()) {

            String value = sdic.get(k);
            if (StringManager.isNullOrEmpty(value)) {
                continue;
            }

            sb.add(k.trim() + "=" + value.trim());
        }

        String sortedValues = StringManager.join("&", sb);
        sortedValues = sortedValues + "&key=" + key;
//        System.out.println(sortedValues);
        return md5(sortedValues).toUpperCase();
    }

    public static String md5(String s) {
        String result = MD5Util.MD5Encode(s, Encodings.UTF8);
        return result;
    }

    private static final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String getNonceString() {
        String res = "";
        Random rd  = new Random();
        for (int i = 0; i < 16; i++) {
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
    }

    public static String getTs() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /*转换金额为分*/
    public static String ToWxPayAmount(BigDecimal d) {
        if (d.doubleValue() <= 0) {
            throw new WeixinException("金额超出合法范围");
        }

        BigDecimal fee = d.setScale(2, BigDecimal.ROUND_HALF_UP);

        Integer feeint = fee.multiply(BigDecimal.valueOf(100)).intValue();

        return feeint.toString();
    }
}