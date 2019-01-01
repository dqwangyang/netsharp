<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@page import="org.netsharp.wx.pa.base.IFansService" %>
<%@page import="org.netsharp.communication.ServiceFactory" %>
<%@page import="org.netsharp.wx.pa.entity.Fans" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <meta name="format-detection" content="telephone=no" />
    <title>oauth测试页面</title>
  </head>
  <script>
      window.alert("hello");
  </script>
  <body > 
    
    <%
        String originalId = (String)request.getParameter("originalId");
        response.getWriter().write("originalId:"+originalId+"<br/>");
        
        String code = (String)request.getParameter("code");
        response.getWriter().write("code:"+code+"<br/>");
        
        //oauth中必须根据code才能得到粉丝信息
        IFansService fansService = ServiceFactory.create(IFansService.class);
        
        Fans fans = fansService.attachByOpenId(code, originalId);
        
        
        response.getWriter().write("openId:"+fans.getOpenId()+"<br/>");
        response.getWriter().write("nickname:"+fans.getNickname()+"<br/>");
        response.getWriter().write("img:<img src='"+fans.getHeadImgUrl()+"' style='width:60px;height:60px' /><br/>");
        
    %>
  </body>
</html>
