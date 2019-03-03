<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>

<%

/* 	CommonsMultipartFile orginalFile = (CommonsMultipartFile) request.getFile("file");
	Map<String, String> map = new HashMap<>();
	String path = "/upload/froala_editor";	
	String url = "";
	String fileType=FileUtil.getFileNameFullExtension(orginalFile.getOriginalFilename());
	try {
		url = FileUtil.uploadFileAllType(orginalFile, path,fileType);	
		
		map.put("link", url);
	} catch (Exception e) {
				
	}
	return map; */
%>