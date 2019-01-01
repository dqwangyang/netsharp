package org.netsharp.wx.sdk.mp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.NetsharpException;


public class WebClient {

    private static Log    logger      = LogFactory.getLog(WebClient.class);
    private        int    timspan     = 30000; // 连接读取超时30秒
    private static String encoding    = "UTF-8";
    private        String contentType = "application/x-www-form-urlencoded";

    public String downloadString(String url)  {

        try {

            HttpURLConnection http = this.createConnection(url, HttpMethod.GET);

            InputStream input = http.getInputStream();

            String message = this.read(input);

//			http.disconnect();

            return message;

        } catch (Exception e) {
            throw new NetsharpException(e);
        }
    }

    private String read(InputStream input) throws IOException {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int c;
        while ((c = input.read()) >= 0) {
            buffer.write(c);
        }
        buffer.close();
        byte[] bytes = buffer.toByteArray();

        String body = new String(bytes, encoding);

        input.close();

        return body;
    }

    public String uploadString(String url, String str) {
        try {
            logger.info("request_url:" + url);
            logger.info("request_post:" + str);
            HttpURLConnection http = this.createConnection(url, HttpMethod.POST);

            OutputStream output = http.getOutputStream();
            {
                output.write(str.getBytes(encoding));
                output.flush();
                output.close();
            }

            InputStream input = http.getInputStream();
            String message = this.read(input);

            return message;

        } catch (Exception e) {
            throw new NetsharpException(e);
        }
    }

    public byte[] downloadData(String url, String str)  {

        try {

            HttpURLConnection http = this.createConnection(url, HttpMethod.POST);

			http.connect();

            if (str != null) {
                OutputStream output = http.getOutputStream();
                {
                    output.write(str.getBytes(encoding));
                    output.flush();
                    output.close();
                }
            }

            InputStream input = http.getInputStream();
            int size = input.available();
            byte[] buffers = new byte[size];
            input.read(buffers);
            input.close();

			http.disconnect();

            return buffers;

        } catch (Exception e) {
            throw new NetsharpException(e);
        }
    }

    private HttpURLConnection createConnection(String url, HttpMethod method) throws IOException {

        URL urlGet = new URL(url);

        HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
        {
            http.setRequestMethod(method.toString());
            http.setRequestProperty("Content-Type", this.contentType);
            http.setDoOutput(true);
            http.setDoInput(true);
        }

        System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(timspan));
        System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(timspan));

        return http;
    }

    public static String UrlEncode(String url) {

        try {
            url = URLEncoder.encode(url, encoding);

        } catch (UnsupportedEncodingException e) {
            logger.error("url编码失败", e);
        }

        return url;
    }

    public static String UrlDecode(String url) {

        try {
            url = URLDecoder.decode(url, encoding);

        } catch (UnsupportedEncodingException e) {
            logger.error("url解码失败", e);
        }

        return url;
    }

    public enum HttpMethod {
        GET, POST
    }
}
