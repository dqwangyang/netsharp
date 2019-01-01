<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title>dbm</title>

		<link href='/package/easyui/themes/gray/easyui.css' rel='stylesheet' type='text/css' />
		<link href='/package/easyui/themes/icon.css' rel='stylesheet' type='text/css' />
		<link href='/package/easyui/themes/color.css' rel='stylesheet' type='text/css' />
		<!-- <link href='/panda-res/css/panda.css' rel='stylesheet' type='text/css' /> -->
		<script src='/package/easyui/jquery.min.js'></script>
		<script src='/package/easyui/jquery.easyui.min.js'></script>
		<script src='/package/easyui/locale/easyui-lang-zh_CN.js'></script>
		<script src='/package/easyui/jquery.easyui.extend.js'></script>
		<script src='/panda-res/js/system.js'></script>
		<script src='/panda-res/js/panda.core.js'></script>
		<script src='/panda-res/js/panda.js'></script>
		<script src='/addins/dbm/dbm.js'></script>

	</head>

	<body class="easyui-layout" style="height:100%;background-color:red;">
		<div id="north" style="height: 200px;" data-options="region:'north',split:true" data-options="region:'south',split:true">
			<div id="partCode">
				<div id="toolbar" class="toolbar">
					<a iconCls="icon-add" onclick="dbm.executeSql();" plain="true" id="btnExecute" class="easyui-linkbutton" code="add">执行</a>
				</div>
				<textarea id="txt" style="width: 98%; height: 145px; margin: 5px;"></textarea>
			</div>
		</div>

		<div id="center" data-options="region:'center',split:true">
			<div id="tabs" class="easyui-tabs">
				<div id="partDatagrid" title="结果">
					<table id="datagrid" style="width:98%;height:400px;margin:5px;border-width:1px;background-color:red;" pageSize="0" singleSelect="true" rownumbers="true" id="datagridparticipants" class="easyui-datagrid" data-options="nowrap:false,height:0,selectOnCheck:false,checkOnSelect:false,remoteSort:false,striped:false,showFooter:false"></table>
				</div>

				<div id="partMessage" title="消息">
					<textarea id="txtMessage" style="width: 98%; height: 145px; margin: 5px;" readonly="readonly"></textarea>
				</div>
			</div>
	</body>

</html>