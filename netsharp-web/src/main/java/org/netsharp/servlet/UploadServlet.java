package org.netsharp.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.comunication.HtmlWriter;
import org.netsharp.panda.core.comunication.ServletRequest;
import org.netsharp.panda.core.comunication.ServletResponse;
import org.netsharp.panda.servlet.invoke.rest.Result;
import org.netsharp.panda.servlet.invoke.rest.Result.Type;
import org.netsharp.panda.session.PandaSessionPersister;
import org.netsharp.util.JsonManage;
import org.netsharp.util.StringManager;

@WebServlet(name = "UploadServlet", urlPatterns = { "/upload" })
public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = -4642377214925791948L;
	public UploadServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	}
	
	@SuppressWarnings("rawtypes")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Result<String> result = Result.newOne();
		{
			result.setType(Type.info);
			result.setMessage("导入成功！");
		}

		this.getHttpCurrent(request, response);
		
		if(!PandaSessionPersister.logined()){			
			result.setType(Type.error);
			result.setMessage("未登录，上传失败！");
		    String json = JsonManage.serialize2(result);
	    	response.getWriter().write(json);
		}
		
		//未完成功能：根据系统配置，有些是选择第三方存储，如：七牛

		response.setHeader("content-type", "text/html;charset=UTF-8");
		String osName = System.getProperties().getProperty("os.name");//系统类型
		String fileSeparator = System.getProperties().getProperty("file.separator");//路径分隔符
		
	    request.setCharacterEncoding("UTF-8");//设置接收的编码格式    
	    Date date = new Date();//获取当前时间
	    SimpleDateFormat sdfFileName = new SimpleDateFormat("yyyyMMddHHmmss"); 
	    SimpleDateFormat sdfFolder = new SimpleDateFormat("yyMM");    
	    String newfileName = sdfFileName.format(date);//文件名称    
	    String fileRealPath = "";//文件存放真实地址    

//	    String name = request.getParameter("name");//文件原名称  界面编码 必须 和request 保存一致..否则乱码    
	    String folder = request.getParameter("folder");
	    String firstFileName = "";
	    String osPath = ""; // 获得容器中上传文件夹所在的物理路径    
	    
	    String relativePath = "";
	    if(StringManager.indexOf(osName, "windows", true)!=-1){
	    	relativePath =  getServletContext().getInitParameter("windows_upload_dir");
	    	osPath = this.getServletConfig().getServletContext().getRealPath("/") + relativePath;
	    }else{
	    	osPath = relativePath = getServletContext().getInitParameter("lunix_upload_dir");
	    }
	    
	    //分类文件夹
	    String categoryFolderPath = "";
	    if(folder==null){
	    	categoryFolderPath = "";
	    }else{
	    	categoryFolderPath = folder + fileSeparator;
	    }

	    //创建文件夹
	    String savePath = osPath + categoryFolderPath + sdfFolder.format(date) + fileSeparator;
	    File file = new File(savePath);    
	    if (!file.isDirectory()) {    
	        file.mkdirs();
	    }
	
	    try {
			DiskFileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fac);
	        upload.setHeaderEncoding("UTF-8");
	        List files = upload.parseRequest(request);
	        @SuppressWarnings("unchecked")
			Iterator<FileItem> it = files.iterator();    
	        while (it.hasNext()) {
	            Object obit = it.next();  
	            if(obit instanceof DiskFileItem){	            	
	                DiskFileItem item = (DiskFileItem) obit; //获得文件名及路径
	                String fileName = item.getName();    
	                if (fileName != null) {
	                    firstFileName=item.getName().substring(item.getName().lastIndexOf(fileSeparator)+1);    
	                    String formatName = firstFileName.substring(firstFileName.lastIndexOf("."));//获取文件后缀名    
	                    fileRealPath = savePath + newfileName+ formatName;//文件存放真实地址
	                    BufferedInputStream in = new BufferedInputStream(item.getInputStream());// 获得文件输入流    
	                    BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(new File(fileRealPath)));// 获得文件输出流    
	                    Streams.copy(in, outStream, true);// 开始把文件写到你指定的上传文件夹  
	                }     
	            }  
	        }     
	    } catch (org.apache.commons.fileupload.FileUploadException ex) {
	        ex.printStackTrace();
			result.setType(Type.error);
			result.setMessage("没有上传文件");
	       return;    
	    }

	    String path = fileSeparator + relativePath + folder + fileSeparator + sdfFolder.format(date) + fileSeparator + fileRealPath.substring(fileRealPath.lastIndexOf(fileSeparator)+1);
	    result.data = path;
	    String json = JsonManage.serialize2(result);
    	response.getWriter().write(json);
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
