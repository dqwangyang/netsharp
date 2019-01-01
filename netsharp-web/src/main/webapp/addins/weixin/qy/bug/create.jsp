<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="org.netsharp.wx.sdk.ep.user.UserInfoRequest" %>
<%@page import="org.netsharp.wx.sdk.ep.user.UserInfoResponse" %>
<%@page import="org.netsharp.util.JsonManage" %>
<%@page import="org.netsharp.wx.sdk.ep.accesstoken.AccessToken" %>
<%@page import="org.netsharp.wx.sdk.ep.accesstoken.AccessTokenManage" %>
<!DOCTYPE html>
<html>
<head>
    <title>创建BUG</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no,minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="msapplication-tap-highlight" content="no">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="Expires" CONTENT="0">
    <meta http-equiv="Cache-Control" CONTENT="no-cache">
    <meta http-equiv="Pragma" CONTENT="no-cache">
    <link rel="stylesheet" href="//cdn.bootcss.com/weui/1.1.1/style/weui.min.css">
	<link rel="stylesheet" href="//cdn.bootcss.com/jquery-weui/1.0.1/css/jquery-weui.min.css">
</head>
<body style="text-align:center;font-size:16px;">
	
	<%

		String code = (String)request.getParameter("code");
		AccessToken token = AccessTokenManage.get("wwdd3357de81c63f09","fjObxFjo_v2di8LbLXLYNKS9dLGDxe_nPxmBxvBvKaM");
		UserInfoRequest userInfoRequest = new UserInfoRequest();{
			userInfoRequest.setCode(code);
			userInfoRequest.setToken(token);
		}
		UserInfoResponse userInfoResponse = userInfoRequest.getResponse();
   %>
	<div><%=code %></div>
	<div><%=token.getAccess_token() %></div>
</body>
</html>