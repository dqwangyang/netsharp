<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.netsharp.scrum.entity.Roadmap" %>
<%@page import="org.netsharp.util.JsonManage" %>
<%@page import="org.netsharp.scrum.base.IRoadmapService" %>
<%@page import="org.netsharp.communication.ServiceFactory" %>
<%
	String id = (String)request.getParameter("id");
	IRoadmapService service = ServiceFactory.create(IRoadmapService.class);
	Roadmap roadmap = service.byId(id);
	String json = JsonManage.serialize2( roadmap );
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><%= roadmap.getName() %>--路线规划图</title>
    <meta http-equiv="Pragram" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    
    <link href='/addins/scrum/roadmap.css' rel='stylesheet' type='text/css' />
    
</head>
<body style="background:#F5F5F5">
	<section> 
	    <div class="roadmap" id="roadmap">  </div>
	</section>
</body>

<script>
	window.onload = function() {
        
        var roadmap =<%= json %>;
		
		function createPhase(roadmap) {
			var html = [];
			html.push('<div class="phase">');
			html.push('<span class="name t1">' + roadmap.name + '</span>');
			html.push('<div class="man txt">');
			for (var i = 0; i < roadmap.details.length; i++) {
				var details = roadmap.details[i];
				html.push('<div class="history">');
				html.push('<div class="date">' + details.date + '</div>');
				html.push('<div class="dot" style="background: '+getColor(details.status)+';"></div><span class="name t2">' + details.name + '</span><br>');
				//html.push('<span class="t3">' + details.date + '</span><br>');
				html.push('<span>' + details.memoto + '</span>');
				html.push('</div>');
			}
			html.push('</div>');
			html.push('</div>');
			
			document.getElementById('roadmap').innerHTML = html.join('');
		}
		
		function getColor(status){
			
			var list = ['green','red','blue','gray'];
			return list[status+1];
		}
		
		createPhase(roadmap);
	}
</script>
</html>