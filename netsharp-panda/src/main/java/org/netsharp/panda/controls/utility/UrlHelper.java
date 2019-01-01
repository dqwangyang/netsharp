package org.netsharp.panda.controls.utility;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;

import org.netsharp.appconfig.AppConfigHelper;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.core.comunication.IRequest;
import org.netsharp.util.StringManager;

/*URL辅助类*/
public class UrlHelper {

	static Long version = AppConfigHelper.getStaticResourceVersion();

	/* 如果当前网站有二级域名，根据没有二级域名的地址得到带有二级域名的地址 */
	public static String getUrl(String url) {
		return url;
	}

	public static String getFullUrl(String url) {
		if (StringManager.isNullOrEmpty(url)) {
			return null;
		}

		if (StringManager.startWith(url, "http:", true)) {
			return url;
		}

		if (StringManager.startWith(url, "https:", true)) {
			return url;
		}

		if (StringManager.startWith(url, "www.", true)) {
			return url;
		}

		String host = getHost();
		if (StringManager.isNullOrEmpty(host)) {
			return url;
		} else {
			return host + url;
		}
	}

	public static String getHost() {
		HttpContext ctx = HttpContext.getCurrent();
		if (ctx == null) {
			throw new PandaException("当前环境HttpContext.getCurrent()为空");
		}

		IRequest request = ctx.getRequest();

		String url = request.getRequestURL();
		String serveltPath = request.getServletPath();

		int index = url.indexOf(serveltPath);
		if (index <= 0) {
			return url;
		}

		String host = url.substring(0, index);

		return host;
	}

	public static String join(String url, String queryString) {
		if (StringManager.isNullOrEmpty(url)) {
			return url;
		}

		if (url.indexOf('?') > 0) {
			url += "&";
		} else {
			url += "?";
		}

		url += queryString;

		return url;
	}

	/* 得到有版本声明的脚本引用 */
	public static String getVersionScript(String url) {
		return getVersionScript(url, false);
	}

	/* 得到有版本声明的脚本引用 */
	public static String getVersionScript(String url, boolean random) {
		if (random) {
			String v = null;
			if (url.indexOf('?') >= 0) {
				v = "&v=" + new Random().nextInt();
			} else {
				v = "?v=" + new Random().nextInt();
			}

			url = getUrl(url) + v;
		} else {
			url = getUrl(url);
		}
		return "<script src='" + url + "?v=" + version + "'></script>";
	}

	/* 得到有版本声明的css引用 */
	public static String getVersionCss(String url) {
		url = getUrl(url);
		return "<link href='" + url + "?v=" + version + "' rel='stylesheet' type='text/css' />";
	}

	public static String encode(String str) {

		try {
			str = URLEncoder.encode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new PandaException("url编码异常：" + str, e);
		}

		return str;
	}

	public static String decode(String str) {

		try {
			str = URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new PandaException("url解码异常：" + str, e);
		}

		return str;
	}
}
