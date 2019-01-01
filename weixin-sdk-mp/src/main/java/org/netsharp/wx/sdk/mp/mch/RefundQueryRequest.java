package org.netsharp.wx.sdk.mp.mch;

import java.lang.reflect.Field;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.core.convertor.TypeConvertorFactory;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;
import org.netsharp.util.XmlManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RefundQueryRequest extends MchRequest<RefundQueryResponse> {
    private String transactionId = "";
    private String outTradeNo    = "";
    private String refundId      = "";
    private String outRefundNo   = "";

    public RefundQueryRequest() {
        this.responseType = RefundQueryResponse.class;
    }

    @Override
    protected String getUrl() {
        return "https://api.mch.weixin.qq.com/pay/refundquery";
    }

    @Override
    protected RefundQueryResponse doResponse() {
        java.util.Map<String, String> dic = this.createPostData();

        dic.put("refund_id", this.getRefundId());
        dic.put("out_trade_no", this.getOutTradeNo());
        dic.put("out_refund_no", this.getOutRefundNo());
        dic.put("transaction_id", this.getTransactionId());

        addSign(dic);

        return this.httpPost(dic);
    }

    @Override
    protected void doValidate() {
        if (StringManager.isNullOrEmpty(this.getOutTradeNo())) {
            throw new IllegalArgumentException("查询订单号不能为空");
        }
    }

    // 退款查询结果解析
    @Override
    protected RefundQueryResponse deserialize(String xml) {
        System.out.println(xml);

        RefundQueryResponse resp = new RefundQueryResponse();
        Map<String, RefundForm> refundForms = new TreeMap<String, RefundForm>();

        Document doc = XmlManager.parseXml(xml);
        Element root = doc.getDocumentElement();

        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            String fieldName = node.getNodeName();
            if (fieldName.equals("#text")) {
                continue;
            }
            String fieldValue = node.getTextContent();

            // 解析子退款单
            boolean isOk = (fieldName.startsWith("out_refund_no_"));
            isOk = isOk || (fieldName.startsWith("refund_id_"));
            isOk = isOk || (fieldName.startsWith("refund_fee_"));
            isOk = isOk || (fieldName.startsWith("refund_status_"));
            if (isOk) {
                String no = fieldName.substring(fieldName.lastIndexOf("_") + 1);
                String fname = fieldName.replace("_" + no, "");
                RefundForm form = refundForms.get(no);
                if (form == null) {
                    form = new RefundForm();
                    refundForms.put(no, form);
                }
                Field f = ReflectManager.getField(RefundForm.class, fname);
                if (f == null) {
                    continue;
                }
                f.setAccessible(true);
                ITypeConvertor tc = TypeConvertorFactory.create(f.getType());
                Object value = tc.fromString(fieldValue);
                ReflectManager.set(f, form, value);
                continue;
            }

            Field f = ReflectManager.getField(resp.getClass(), fieldName);
            if (f == null) {
                continue;
            }
            f.setAccessible(true);

            ITypeConvertor tc = TypeConvertorFactory.create(f.getType());
            Object value = tc.fromString(fieldValue);

            ReflectManager.set(f, resp, value);
        }

        for (Map.Entry<String, RefundForm> iter : refundForms.entrySet()) {
            resp.getRefundDetails().add(iter.getValue());
        }

        return resp;
    }


    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public CloseableHttpClient createHttpsClient() throws Exception {
        X509TrustManager x509mgr = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] xcs, String string) {
            }
            @Override
            public void checkServerTrusted(X509Certificate[] xcs, String string) {
            }
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[] { x509mgr }, null);
        @SuppressWarnings("deprecation")
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }
}