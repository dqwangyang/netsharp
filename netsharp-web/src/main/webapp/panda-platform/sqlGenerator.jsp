<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="org.netsharp.base.IPersistableService"%>
<%@ page import="org.netsharp.panda.base.IPPartService"%>
<%@ page import="org.netsharp.panda.entity.PPart"%>
<%@ page import="org.netsharp.script.ScriptGenerator"%>
<%@ page import="org.netsharp.util.StringManager"%>
<%@ page import="org.netsharp.communication.ServiceFactory"%>
<%@ page import="org.netsharp.resourcenode.IResourceNodeService"%>
<%@ page import="org.netsharp.resourcenode.entity.ResourceNode"%>
<%@ page import="org.netsharp.panda.base.IPluginService"%>
<%
	String type = (String)request.getParameter("type");
	String sql = "";
	
	if(type.equals("part")){

	    String vid = (String)request.getParameter("vid");
		IPPartService partService = ServiceFactory.create(IPPartService.class);
		PPart part = partService.byId(vid);
		ScriptGenerator generator = new ScriptGenerator();
		List<String> sqls = generator.generateComposite(part);
		sql = StringManager.join("<br/>", sqls);
		
		//表单方案
		
		//表格方案
		
		//查询方案
		
	}else if(type.equals("resourcenode")){
		
		String id = (String)request.getParameter("id");
		Long resourceNodeId = Long.valueOf(id);
		IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);
		List<String> sqls = service.export(resourceNodeId);
		sql = StringManager.join("<br/>", sqls);
		
	}else if(type.equals("plugin")){
		
		String id = (String)request.getParameter("id");
		Integer pluginId = Integer.valueOf(id);
		IPluginService service = ServiceFactory.create(IPluginService.class);
		List<String> sqls = service.export(pluginId);
		sql = StringManager.join("<br/>", sqls);
	}else if(type.equals("entity")){
		
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>脚本导出</title>
</head>
<body style="font-size:9px;">
	<%=sql %>
</body>
</html>