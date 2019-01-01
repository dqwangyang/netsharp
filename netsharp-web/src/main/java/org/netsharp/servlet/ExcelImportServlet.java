package org.netsharp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.netsharp.panda.commerce.ExcelImport;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.comunication.HtmlWriter;
import org.netsharp.panda.core.comunication.ServletRequest;
import org.netsharp.panda.core.comunication.ServletResponse;
import org.netsharp.panda.servlet.invoke.rest.Result;
import org.netsharp.panda.servlet.invoke.rest.Result.Type;
import org.netsharp.util.ExceptionUtil;
import org.netsharp.util.JsonManage;

@WebServlet(name = "ExcelImportServlet", urlPatterns = { "/import" })
public class ExcelImportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExcelImportServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Result<?> result = Result.newOne();
		{
			result.setType(Type.info);
			result.setMessage("导入成功！");
		}
		try {
			response.setHeader("content-type", "text/html;charset=UTF-8");
			this.getHttpCurrent(request, response);
			ExcelImport excelImportService = new ExcelImport(request, response);
			excelImportService.start();
		} catch (Exception e) {
			result.setType(Type.error);
			result.setMessage(ExceptionUtil.extractMsg(e));
		}
		String json = JsonManage.serialize2(result);
		HttpContext context = HttpContext.getCurrent();
		context.getWriter().write(json);
	}

	private void getHttpCurrent(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpContext ctx = new HttpContext();
		{
			ctx.setRequest(new ServletRequest(request, response, false));
			ctx.setResponse(new ServletResponse(response));
			ctx.setContext(this.getServletContext());
			ctx.setWriter(new HtmlWriter(response.getWriter()));
		}

		HttpContext.setCurrent(ctx);
	}
}
