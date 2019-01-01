package org.netsharp.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.netsharp.core.NotImplementException;
import org.netsharp.util.StringManager;

@WebServlet(name = "DownloadServlet", urlPatterns = { "/download" })
public class DownloadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String contentType = "application/x-msdownload";

	public DownloadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String osName = System.getProperties().getProperty("os.name");
		String filepath = request.getParameter("path");
		String filename = request.getParameter("name");

		String fullFilePath = getServletContext().getInitParameter("lunix_upload_dir") + filepath;

		if (StringManager.indexOf(osName, "windows", true) != -1) {
			fullFilePath = this.getServletConfig().getServletContext().getRealPath("/")
					+ getServletContext().getInitParameter("windows_upload_dir") + filepath.replace("/", "\\");
		}

		//
		File file = new File(fullFilePath);
		if (!file.exists()) {
			return;
		}

		String prefix = filepath.substring(filepath.lastIndexOf(".") + 1);
		String new_filename = URLEncoder.encode(filename, "UTF8");

		response.reset();
		response.setContentType(contentType);
		response.addHeader("Content-Disposition", "attachment; filename=\"" + new_filename + "." + prefix + "\"");
		int fileLength = (int) file.length();
		response.setContentLength(fileLength);
		/* 如果文件长度大于0 */
		if (fileLength != 0) {
			/* 创建输入流 */
			InputStream inStream = new FileInputStream(file);
			byte[] buf = new byte[4096];
			/* 创建输出流 */
			ServletOutputStream servletOS = response.getOutputStream();
			int readLength;
			while (((readLength = inStream.read(buf)) != -1)) {
				servletOS.write(buf, 0, readLength);
			}
			inStream.close();
			servletOS.flush();
			servletOS.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		throw new NotImplementException();
	}

}
