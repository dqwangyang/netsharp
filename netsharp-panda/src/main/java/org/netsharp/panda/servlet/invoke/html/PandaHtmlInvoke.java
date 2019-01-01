package org.netsharp.panda.servlet.invoke.html;

import org.netsharp.core.NetsharpException;
import org.netsharp.panda.authorization.AuthorizationException;
import org.netsharp.panda.authorization.LoginException;
import org.netsharp.panda.core.HtmlPage;
import org.netsharp.panda.core.HtmlPageFactory;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.panda.core.comunication.IRequest;
import org.netsharp.panda.core.comunication.IResponse;
import org.netsharp.panda.servlet.filters.LogFilter;
import org.netsharp.panda.servlet.invoke.PandaContext;
import org.netsharp.panda.servlet.invoke.PandaInvoke;
import org.netsharp.panda.servlet.invoke.PandaInvokeType;

public class PandaHtmlInvoke extends PandaInvoke {

	public PandaHtmlInvoke() {

		this.filters.add(new LogFilter(PandaInvokeType.html));
		this.filters.add(new AuthorizationFilter());
	}

	public void doProcessRequest(PandaContext pandaContext) {

		HttpContext ctx = HttpContext.getCurrent();
		IRequest request = ctx.getRequest();
		IResponse response = ctx.getResponse();

		String subhost = request.getContextPath();
		String url = request.getRequestURI().replace(subhost + "/panda", "");
		String toLoginScript = "<script>window.top.location.href='/nav/panda-bizbase/authorization/login';</script>";
		try {

			IHtmlWriter writer = HttpContext.getCurrent().getWriter();
			HtmlPage page = HtmlPageFactory.create(url);
			{
				page.preInitialize();
				page.initialize();
				page.render(writer);
				writer.clearWriteHtml();
			}

		} catch (LoginException ex) {

			response.write(toLoginScript);

		} catch (AuthorizationException ex) {

			response.sendRedirect("/nav/panda-bizbase/authorization/nopermission");
		} catch (NetsharpException e) {

			throw e;
		} catch (Exception e) {

			// 临时处理刷新报错 直接跳转login 页面
            response.write(toLoginScript);
		}
	}
}
