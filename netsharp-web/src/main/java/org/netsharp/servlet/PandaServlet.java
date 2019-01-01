package org.netsharp.servlet;

import javax.servlet.annotation.WebServlet;

import org.netsharp.panda.servlet.NetsharpServlet;

@WebServlet(name = "panda", urlPatterns = "/panda/*")
public class PandaServlet extends NetsharpServlet {

	private static final long serialVersionUID = -6975050727625380423L;

	public PandaServlet() {
		super();
	}
}
