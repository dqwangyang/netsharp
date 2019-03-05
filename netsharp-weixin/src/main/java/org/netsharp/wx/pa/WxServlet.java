package org.netsharp.wx.pa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.comunication.HtmlWriter;
import org.netsharp.panda.core.comunication.ServletRequest;
import org.netsharp.panda.core.comunication.ServletResponse;
import org.netsharp.wx.sdk.mp.message.WeixinMessageDispatcher;
import org.netsharp.wx.sdk.mp.sdk.WeixinRequestParameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class WxServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(WxServlet.class.getSimpleName());

	public WxServlet() {
		super();
	}

	// http://121.40.86.55/wx?oid=gh_befcc6d4c40d
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");

		logger.error("微信开发者配置验证开始");

		response.getWriter().write("");

		WeixinRequestParameters par = this.getWeixinParameter(request);

		try {
			WeixinMessageDispatcher mc = new WeixinMessageDispatcher();
			mc.validate(par);
			response.getWriter().write(par.Echostr);
		} catch (Exception ex) {
			logger.error("servlet 微信get异常", ex);
		}

		logger.error("微信开发者配置验证结束");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setCharacterEncoding("utf-8");

		this.createContext(request,response);

		WeixinRequestParameters par = this.getWeixinParameter(request);
		InputStream stream = request.getInputStream();
//		String xml = FileManager.read(stream);
//		logger.error("[收到微信消息]：" + xml);

		try {
			WeixinMessageDispatcher mc = new WeixinMessageDispatcher();
			String content = mc.processRequest(par, stream);

			logger.error("[回复消息]：" + content);

			response.getWriter().write(content);
		} catch (Exception ex) {
			logger.error("servlet 微信post异常", ex);
		}
	}

    private void createContext(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpContext ctx = new HttpContext();
        {
            ctx.setRequest(new ServletRequest(request, response));
            ctx.setResponse(new ServletResponse(response));
            ctx.setContext(this.getServletContext());
            ctx.setWriter(new HtmlWriter(response.getWriter()));
        }
        HttpContext.setCurrent(ctx);
    }

	private WeixinRequestParameters getWeixinParameter(HttpServletRequest request) {

		WeixinRequestParameters par = new WeixinRequestParameters();
		{
			par.Signature = request.getParameter("signature");
			par.Timestamp = request.getParameter("timestamp");
			par.Nonce = request.getParameter("nonce");
			par.Echostr = request.getParameter("echostr");
			par.Oid = request.getParameter("oid");
		}

		logger.error(par.toString());

		return par;
	}
}
