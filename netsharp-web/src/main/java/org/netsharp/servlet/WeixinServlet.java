package org.netsharp.servlet;

import javax.servlet.annotation.WebServlet;

import org.netsharp.wx.pa.WxServlet;

@WebServlet(name = "weixin", urlPatterns = "/wx")
public class WeixinServlet extends WxServlet {

	private static final long serialVersionUID = -7679520216665506335L;

	public WeixinServlet() {
		super();
	}

}
