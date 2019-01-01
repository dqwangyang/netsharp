package org.netsharp.wx.sdk.mp.mch.redpack;
//package org.netsharp.wx.pa.sdk.mch.redpack;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URISyntaxException;
//import java.security.KeyManagementException;
//import java.security.KeyStore;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.security.UnrecoverableKeyException;
//import java.security.cert.CertificateException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLContexts;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.netsharp.core.NetsharpException;
//import org.netsharp.wx.pa.sdk.mch.MchRequest;
//import org.netsharp.wx.pa.sdk.mch.SdkHelper;
//import org.netsharp.wx.pa.sdk.pay.PayHelp;
//
//import com.google.zxing.common.StringUtils;
//import com.sun.net.ssl.SSLContext;
//import org.netsharp.util.DateUtil;
//import org.netsharp.util.JsonUtil;
//
///**
// * @author kxh
// */
//public class RedPacketRequest extends MchRequest<RedPacketResponse> 
//{
//	
//    private static Log           logger = LogFactory.getLog(RedPacketRequest.class);
//    private static AtomicInteger ai     = new AtomicInteger(0);
//
//    private RedPacket redPacket;
//    private String      openId; // 接收红包的微信用户的openId
//
//    public RedPacketRequest() {
//        this.responseType = RedPacketResponse.class;
//        initMchInfo();
//    }
//
//    public RedPacketRequest(RedPacket redPacket, String openId) {
//        this.redPacket = redPacket;
//        this.openId = openId;
//        this.responseType = RedPacketResponse.class;
//        initMchInfo();
//    }
//
//    // 商户参数初始化
//    private void initMchInfo() {
//        this.setAppId(RedPacketConstant.getAppId());
//        this.setMchId(RedPacketConstant.getMchId());
//        this.setPaySignKey(RedPacketConstant.getPartnerKey());
//    }
//
//    @Override
//    public String getUrl() {
//        return RedPacketConstant.getRedPacketApiUrl();
//    }
//
//    @Override
//    protected void doValidate() {
//
//    }
//
//    @Override
//    protected RedPacketResponse doResponse()  {
//        Map<String, String> postData = getRequestParams();
//        logger.info("调用微信红包接口的请求参数：\n" + JsonManage.serialize2(postData));
//
//        // 发送红包
//        RedPacketResponse response = null;
//        try {
//            response = this.securityHttpPost(postData);
//            //logger.info("调用微信发送红包接口的返回结果：\n" + JsonManage.serialize2(response));
//
//            // 返回的结果状态
//            String resultCode = response.getResult_Code();
//
//            // 如果发送红包失败
//            if (ResultCodeEnum.FAIL.name().equals(resultCode)) {
//                // 返回的错误码
//                String errorCode = response.getError_code();
//                // TODO 系统繁忙，请再试，可用同一商户单号再次调用，只会収放一个红包
//                if ("SYSTEMERROR".equals(errorCode)) {
//                    // 暂时只重试一次
//                    response = this.securityHttpPost(postData);
//                }
//            }
//        } catch (Exception ex) {
//            logger.error("", ex);
//        }
//        return response;
//    }
//
//    /* 组合请求参数 */
//    private Map<String, String> getRequestParams() {
//        Map<String, String> postData = new HashMap<String, String>();
//
//        // 模拟随机红包金额
//        String money = getRandomMoney(redPacket.getMinValue(), redPacket.getMaxValue()) + "";
//
//        postData.put("mch_billno", this.getUniqBillNo());
//        postData.put("mch_id", this.getMchId());
//        postData.put("wxappid", this.getAppId());
//        postData.put("nick_name", redPacket.getNickname());
//        postData.put("send_name", redPacket.getSendName());
//        postData.put("re_openid", this.getOpenId());
//        postData.put("total_amount", money);
//        postData.put("min_value", money);
//        postData.put("max_value", money);
//        postData.put("total_num", redPacket.getTotalNum() + "");
//        postData.put("wishing", StringUtils.trimToEmpty(redPacket.getWishing()));
//        postData.put("client_ip", StringUtils.trimToEmpty(redPacket.getClientIp()));
//        postData.put("act_name", StringUtils.trimToEmpty(redPacket.getActName()));
//        postData.put("remark", StringUtils.trimToEmpty(redPacket.getRemark()));
//        postData.put("logo_imgurl", StringUtils.trimToEmpty(redPacket.getLogoImgUrl()));
//        postData.put("share_content", StringUtils.trimToEmpty(redPacket.getShareContent()));
//        postData.put("share_url", StringUtils.trimToEmpty(redPacket.getShareUrl()));
//        postData.put("share_imgurl", StringUtils.trimToEmpty(redPacket.getShareImgUrl()));
//        postData.put("nonce_str", PayHelp.getNonceString());
//
//        this.addSign(postData);
//
//        return postData;
//    }
//
//
//    public static int getRandomMoney(int min, int max) {
//        min = Math.min(min, max);
//        max = Math.max(min, max);
//        if (min <= 0) {
//            throw new NetsharpException("红包的最小金额是1块钱");
//        }
//        if (min == max) {
//            return max;
//        }
//        int off = max - min;
//
//        return new Random().nextInt(off) + min;
//    }
//
//    private KeyStore getKeyStore() throws KeyStoreException, URISyntaxException, IOException, NoSuchAlgorithmException, CertificateException {
//        KeyStore keyStore = KeyStore.getInstance("PKCS12");
//        InputStream instream = this.getClass().getResourceAsStream(RedPacketConstant.getApiclientCert());
//        ;//放退款证书的路径
//        try {
//            keyStore.load(instream, RedPacketConstant.getMchId().toCharArray());
//        } finally {
//            instream.close();
//        }
//        return keyStore;
//    }
//
//    private RedPacketResponse securityHttpPost(Map<String, String> postdata) throws Exception {
//        String xml = SdkHelper.writeXml(postdata);
//        CloseableHttpClient httpclient = getSecurityHttpClient();
//        RedPacketResponse result = null;
//        try {
//
//            HttpPost httpPost = new HttpPost(this.getUrl());
////            httpPost.setHeader("Content-Type","text/html; charset=UTF-8");
//            httpPost.setEntity(new StringEntity(xml, "UTF-8"));
////            System.out.println("executing request" + httpPost.getRequestLine());
//
//            CloseableHttpResponse response = httpclient.execute(httpPost);
//            try {
//                HttpEntity entity = response.getEntity();
//
////                System.out.println("----------------------------------------");
////                System.out.println(response.getStatusLine());
//                if (entity != null) {
////                    System.out.println("Response content length: " + entity.getContentLength());
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
//                    String text;
//                    StringBuilder resp = new StringBuilder();
//                    while ((text = bufferedReader.readLine()) != null) {
//                        resp.append(text);
//                    }
//                    logger.info("微信响应发红包的消息：" + resp.toString());
//                    result = this.deserialize(resp.toString());
//                }
//                EntityUtils.consume(entity);
//            } catch (Exception ex) {
//                logger.error("", ex);
//            } finally {
//                response.close();
//            }
//        } catch (Exception ex) {
//            logger.error("", ex);
//        } finally {
//            httpclient.close();
//        }
//
//        return result;
//    }
//
//    private CloseableHttpClient getSecurityHttpClient() throws KeyStoreException, URISyntaxException, IOException, NoSuchAlgorithmException, CertificateException, KeyManagementException, UnrecoverableKeyException {
//        KeyStore keyStore = getKeyStore();
//
//        // Trust own CA and all self-signed certs
//        SSLContext sslcontext = SSLContexts.custom()
//                                           .loadKeyMaterial(keyStore, RedPacketConstant.getMchId().toCharArray())
//                                           .build();
//        // Allow TLSv1 protocol only
//        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//                sslcontext,
//                new String[]{"TLSv1"},
//                null,
//                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
//        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
//    }
//
//    /**
//     * 生成商户唯一订单号
//     *
//     * @return
//     */
//    private String getUniqBillNo() {
//        String billNo = RedPacketConstant.getMchId() + DateUtil.toInteger(new Date());
//
//        String str = DateUtil.toStdDateTimeXStr(new Date());
//        String no = ai.getAndAdd(1) + "";
//        billNo += str.substring(10).replaceAll("[:\\.]", "") + no.substring(no.length() - 1);
//
//        return billNo;
//    }
//
//    public void setRedPacket(RedPacket redPacket) {
//        this.redPacket = redPacket;
//    }
//
//    public RedPacket getRedPacket() {
//        return redPacket;
//    }
//
//    public String getOpenId() {
//        return openId;
//    }
//
//    public void setOpenId(String openId) {
//        this.openId = openId;
//    }
//}
