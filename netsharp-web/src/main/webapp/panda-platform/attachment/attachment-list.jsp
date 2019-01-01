<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>附件</title>
	<link href="/package/font-awesome/css/font-awesome.min.css" rel='stylesheet' type='text/css' />
	<link href="/package/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
	<link href="/package/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
	<link href="/package/easyui/themes/color.css" rel="stylesheet" type="text/css" />
	<link href="/package/easyui/themes/easyui.extend.css" rel="stylesheet" type="text/css"/>
	<link href="/panda-res/css/panda.form.css" rel="stylesheet" type="text/css"/>
</head>
<body id="bodyLayout" class="easyui-layout">

       <div id="toolbar" class="toolbar">
		 <a iconCls="fa fa-cloud-upload" plain="true" id="btn_upload" class="easyui-linkbutton">上传</a>
		</div>

	<table id="attachment_grid"></table>
	
 	<script src="/package/easyui/jquery.min.js"></script>
	<script src="/package/easyui/jquery.easyui.min.js"></script>
	<script src="/package/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script src="/package/easyui/jquery.easyui.extend.js"></script>
	<script src='/package/qiniu/plupload.full.min.js'></script>
	<script src="/panda-res/js/system.js"></script>
	<script src="/package/layer/layer.js"></script>
	<script src="/panda-res/js/panda.core.js"></script>
	<script src="/panda-res/js/panda.controls.js"></script>
	<script src="/panda-platform/attachment/attachmentListPart.js"></script>
	<script>
    	var listController = new org.netsharp.core.attachmentListController();
		$(function(){

			listController.init();
		});
    </script>
</body>
</html>