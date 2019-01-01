package org.netsharp.wx.sdk.tp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class HttpUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    private final static int CONNECT_TIMEOUT = 5000; // in milliseconds
    private final static String DEFAULT_ENCODING = "UTF-8";
    private static Registry<ConnectionSocketFactory> socketFactoryRegistry = null;
    private static Log log = LogFactory.getLog(HttpUtils.class);

    static {
        try {
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            X509HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
        } catch (Exception e) {
            log.error(e);
        }
    }

    private static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
    private static CloseableHttpClient client = HttpClients.custom().setConnectionManager(cm).setDefaultCookieStore(new BasicCookieStore()).build();

    public static String postData(String urlStr, String data) {
        return postData(urlStr, data, null);
    }

    public static String postData(String urlStr, String data, String contentType) {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(CONNECT_TIMEOUT);
            if (contentType != null) {
                conn.setRequestProperty("content-type", contentType);
            }
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);
            if (data == null) {
                data = "";
            }
            writer.write(data);
            writer.flush();
            writer.close();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");
            }
            return sb.toString();
        } catch (IOException e) {
            logger.error("Error connecting to " + urlStr + ": " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
        return null;
    }

    public static String get(String url) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        String content = null;
        try {
            HttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            content = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public static String post(String url, List<NameValuePair> nvps) {
        String content = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        try {
            if (nvps != null) {
                post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            }
            // 执行请求用execute方法，content用来帮我们附带上额外信息
            HttpResponse response = httpClient.execute(post);
            // 得到相应实体、包括响应头以及相应内容
            HttpEntity entity = response.getEntity();
            // 得到response的内容
            content = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public static String httpPost(String url, Map<String, String> paramMap) {
        String charset = "utf-8";
        String result = null;
        HttpPost post = new HttpPost(url);
        post.addHeader("accept", "application/json");
        post.addHeader("content-type", "application/json");

        String body = JsonUtils.objectToJson(paramMap);
        post.setEntity(new StringEntity(body, charset));
        try {
            CloseableHttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, charset);
            log.debug("httppost respose=" + result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != post) {
                post.releaseConnection();
            }
        }
        return result;
    }

    /**
     * 表单提交
     *
     * @param url
     * @param paramMap
     * @return
     */
    public static String httpPostData(String url, Map<String, Object> paramMap) {
        String charset = "utf-8";
        String result = null;
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        StringEntity se = new StringEntity(getSignature(paramMap), charset);
        post.setEntity(se);
        try {
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, charset);
            log.debug("表单提交Post:httppost respose=" + result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != post) {
                post.releaseConnection();
            }
        }
        return result;
    }

    public static String getSignature(Map<String, Object> params) {
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, Object> sortedParams = new TreeMap(params);
        Set<Map.Entry<String, Object>> set = sortedParams.entrySet();

        // 遍历排序后的字典，将所有参数按"key=value&"格式拼接在一起
        StringBuilder baseString = new StringBuilder();
        for (Map.Entry<String, Object> param : set) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                String value = "";
                Object paramValue = param.getValue();

                if (paramValue instanceof String
                        || paramValue instanceof Long
                        || paramValue instanceof Integer
                        || paramValue instanceof Boolean
                        || paramValue instanceof Double
                        || paramValue instanceof Float
                        || paramValue instanceof BigDecimal
                        || paramValue instanceof BigInteger
                        || paramValue instanceof Short
                        || paramValue instanceof Character
                        || paramValue instanceof Timestamp) {
                    value = paramValue.toString();
                } else if (paramValue instanceof byte[]) {
                    try {
                        value = new String((byte[]) paramValue, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        value = mapper.writeValueAsString(param.getValue());
                    }
                } else if (paramValue instanceof char[]) {
                    value = String.valueOf((char[]) paramValue);
                } else if (paramValue instanceof Date) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    value = simpleDateFormat.format((Date) paramValue);
                } else {
                    value = mapper.writeValueAsString(param.getValue());
                }
                baseString.append(param.getKey()).append("=").append(value).append("&");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        }

        String result = baseString.toString();
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }
}
