package org.netsharp.panda.core.comunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.core.comunication.session.ISession;
import org.netsharp.panda.core.comunication.session.SesssionFactory;
import org.netsharp.util.StringManager;

public class ServletRequest implements IRequest {

	protected static final Log logger = LogFactory.getLog(ServletRequest.class.getName());

	public HttpServletRequest request = null;
	public HttpServletResponse response = null;
	private HashMap<String, String> map = new HashMap<String, String>();
	private String body = null;
	private ISession session = null;

	public ServletRequest(HttpServletRequest request, HttpServletResponse response) {

		this.request = request;
		this.response = response;
		this.session = SesssionFactory.create(request);

		this.intiailize();
	}

	public ServletRequest(HttpServletRequest request, HttpServletResponse response, boolean initialize) {

		this.request = request;
		this.response = response;
		this.session = SesssionFactory.create(request);

		if (initialize) {
			this.intiailize();
		}
	}

	private void intiailize() {

		try {
			this.doInitialize();
		} catch (IOException ex) {
			throw new PandaException("Panda的ServletRequest初始化异常", ex);
		}
	}

	private void doInitialize() throws IOException {
		request.setCharacterEncoding("UTF-8");
		BufferedReader reader = request.getReader();
		StringBuffer requstBody = new StringBuffer();

		String input = null;
		while ((input = reader.readLine()) != null) {
			requstBody.append(input).append(StringManager.NewLine);
		}

		this.body = requstBody.toString();
		if (body != null) {
			body = StringManager.trimEnd(body, '\n');
			body = StringManager.trimEnd(body, '\r');
			body = StringManager.trimEnd(body, '\n');
		}

		String queryString = request.getQueryString();
		this.parse(queryString);

		String contentType = request.getContentType();
		if (contentType != null && !contentType.toLowerCase().contains("json")) {
			this.parse(body);
		}
	}

	private void parse(String url) {
		if (StringManager.isNullOrEmpty(url)) {
			return;
		}

		String[] ss = url.split("&+");

		for (String s : ss) {
			String[] pars = s.split("=");

			if (pars.length < 2) {
				continue;
			}

			String key = StringManager.trim(pars[0], ' ');
			String value = StringManager.trim(pars[1], ' ');

			map.put(key, value);
		}
	}

	@Override
	public void sendRedirect(String url) {

		try {
			if (url.startsWith("http:") || url.startsWith("www.")) {
				this.response.sendRedirect(url);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			throw new PandaException("url redirect exception " + url, e);
		}
	}

	@Override
	public String getParameter(String key) {

		return this.map.get(key);

	}

	public Object getSession(String key) {

		return this.session.getSession(key);
	}

	@Override
	public void removeSession(String key) {

		this.session.removeSession(key);
	}

	public void setSession(String key, Object obj) {
		this.session.setSession(key, obj);
	}

	public long getSessionCreateTime() {
		return this.request.getSession().getCreationTime();
	}

	public String getUserAgent() {
		return request.getHeader("user-agent");
	}

	/**
	 * 获取用户真实IP地址
	 * 不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 * 参考文章： http://developer.51cto.com/art/201111/305181.htm
	 * 
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * 
	 * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
	 * 192.168.1.100
	 * 
	 * 用户真实IP为： 192.168.1.110
     */
	public String getClientIp() {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public String getHeader(String headerAttributeName) {
		String fromSource = headerAttributeName;
		String ip = request.getHeader(fromSource);
		return ip;
	}

	public String getSessionId() {
		return this.session.getId();
	}

	public String getCookie(String cookieName) {

		for (Cookie cookie : this.request.getCookies()) {
			if (cookieName.equalsIgnoreCase(cookie.getName())) {
				return cookie.getValue();
			}
		}

		return null;
	}

	@Override
	public String getContextPath() {
		return this.request.getContextPath();
	}

	@Override
	public String getRequestURL() {
		return this.request.getRequestURL().toString();
	}

	@Override
	public String getRequestURI() {
		return this.request.getRequestURI();
	}

	@Override
	public String getServletPath() {
		return this.request.getServletPath();
	}

	@Override
	public String getQueryString() {

		return this.request.getQueryString();
	}

	public String getBody() {

		return this.body;
	}

	/**
	 * Specifies the time, in seconds, between client requests before the servlet
	 * container will invalidate this session.
	 *
	 * <p>
	 * An <tt>interval</tt> value of zero or less indicates that the session should
	 * never timeout.
	 *
	 * @param interval An integer specifying the number of seconds
	 */
	public void setsetMaxInactiveInterval(int interval) {
		this.request.getSession().setMaxInactiveInterval(interval);
	}
}
