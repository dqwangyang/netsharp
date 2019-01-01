package org.netsharp.wx.sdk.tp.api.pay;

import org.netsharp.wx.sdk.tp.bean.entity.pay.Signature;
import org.netsharp.wx.sdk.tp.utils.MD5Util;
import org.netsharp.wx.sdk.tp.utils.RandomStringGenerator;

/**
 * @borball on 12/29/2016.
 */
public class Signatures {

//    private static Logger logger = LoggerFactory.getLogger(Signatures.class);

    /**
     * 创建供公众号或者小程序调用的签名
     * @param prepayId
     * @return
     */
    public Signature createJsSignature(String prepayId, String appId, String key) {
        String pack = "prepay_id=" + prepayId;

        long timestamp = System.currentTimeMillis() / 1000;
        String nonce = RandomStringGenerator.getRandomStringByLength(16);
        String sortString = String.format("appId=%s&nonceStr=%s&package=%s&signType=MD5&timeStamp=%s",
                appId, nonce, pack, timestamp);

        return createSignature(pack, timestamp, nonce, appId, key, sortString);
    }

    private Signature createSignature(String pack, long timestamp, String nonce, String appId, String key,
                                      String sortString) {
        String signature = MD5Util.MD5Encode(sortString + "&key=" + key, "UTF-8").toUpperCase();
        Signature sign = new Signature();
        sign.setAppId(appId);
        sign.setNonce(nonce);
        sign.setTimestamp(timestamp);
        sign.setSignature(signature);
        sign.setPack(pack);
        return sign;
    }

}
