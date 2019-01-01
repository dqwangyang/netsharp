package org.netsharp.rest.tm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.netsharp.json.JacksonObjectMapper;
import org.netsharp.util.WebClient.HttpMethod;

public class Spider {

	private static final Log logger = LogFactory.getLog(Spider.class.getName());
	
	private int timspan = 100000;
	private Map<String, String> requestProperties = new HashMap<String, String>();
	private int timeoutCount=0;

	public void setRequestProperty(String key, String value) {
		this.requestProperties.put(key, value);
	}

	public <T> T downLoad(String url, HttpMethod method, TypeReference<T> ref) throws IOException {
		
		//java.io.IOException: Server returned HTTP response code: 502 
		
		T obj = null;
		try {
			obj = this.doDownLoad(url, method, ref);
		}catch(SocketTimeoutException ex) {
			this.timeoutCount++;
			if(this.timeoutCount>5) {
				throw ex;
			}
			
			logger.info("time out " + this.timeoutCount);
			obj = this.downLoad(url, method, ref);
		}
		catch(IOException ex) {
			this.timeoutCount++;
			if(this.timeoutCount>5) {
				throw ex;
			}
			
			logger.info("time out " + this.timeoutCount);
			obj = this.downLoad(url, method, ref);
		}
		
//		T obj = this.doDownLoad(url, method, ref);
		
		
		return obj;
	}
	
	public <T> T doDownLoad(String url, HttpMethod method, TypeReference<T> ref) throws IOException {

		logger.debug(url);

		URL uri = new URL(url);

		HttpURLConnection http = (HttpURLConnection) uri.openConnection();
		{
			http.setRequestMethod(HttpMethod.POST.toString());
			http.setDoOutput(true);
			http.setDoInput(true);

			for (String key : this.requestProperties.keySet()) {
				http.setRequestProperty(key, this.requestProperties.get(key));
			}
		}

		System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(timspan));
		System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(timspan));

		String encoding = http.getContentEncoding();
		InputStream input = http.getInputStream();//次处会报超时异常
		InputStream httpInput = input;

		if (encoding != null && encoding.contains("gzip")) {
			input = new GZIPInputStream(http.getInputStream());
		}

		ObjectMapper mapper = new JacksonObjectMapper();
		T obj = mapper.readValue(input, ref);

		input.close();
		if(httpInput!=input) {
			httpInput.close();
		}
		
		http.disconnect();

		return obj;
	}

	public String downLoadString(String url, HttpMethod method) throws IOException {

		logger.debug(url);

		URL uri = new URL(url);

		HttpURLConnection http = (HttpURLConnection) uri.openConnection();
		{
			http.setRequestMethod(HttpMethod.POST.toString());
			http.setDoOutput(true);
			http.setDoInput(true);

			for (String key : this.requestProperties.keySet()) {
				http.setRequestProperty(key, this.requestProperties.get(key));
			}
		}

		System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(timspan));
		System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(timspan));

		String encoding = http.getContentEncoding();
		InputStream input = http.getInputStream();

		if (encoding != null && encoding.contains("gzip")) {
			// 首先判断服务器返回的数据是否支持gzip压缩，
			// 如果支持则应该使用GZIPInputStream解压，否则会出现乱码无效数据
			input = new GZIPInputStream(http.getInputStream());
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String body="";
		String line;
		while ((line = reader.readLine()) != null) {
			body += line;
		}

		reader.close();
		input.close();

		return body;
	}
}
