package org.netsharp.servlet;

import javax.servlet.annotation.WebServlet;

import org.netsharp.panda.servlet.NetsharpServlet;

@WebServlet(name="nav", urlPatterns = "/nav/*")
public class NavServlet extends NetsharpServlet {

	private static final long serialVersionUID = -2276767163425773331L;

	public NavServlet() {
		super();
	}
}