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
	<style>
		#files{
			padding:0 5px;
		}
		
		#files .fa{
			color:#1E7CB5;
			cursor: pointer;
			margin-left: 2px;
			margin-right:5px;
		}
		
		#files .fa:hover{
			color:red;
		}
		
	</style>
</head>
<body id="bodyLayout" class="easyui-layout" style="background-color:#fff;">

	<table cellpadding="5" cellspacing="10" class="form-panel" style="margin-top: 10px;">
		<tbody>
			<tr>
				<td class="title" style="width:70px;text-align:right">反馈标题</td>
				<td><input id="name" class="nsInput easyui-validatebox" style="width: 450px;" ></td>
			</tr>
			<tr>
				<td class="title" style="width:70px;text-align:right">反馈内容</td>
				<td><textarea id="content" style="width: 450px; height: 200px;" class="easyui-validatebox"></textarea></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<a id="button_upload" class="easyui-linkbutton">上传附件</a>
					<span id="files"></span>
				</td>
			</tr>
		</tbody>
	</table>
	
 	<script src="/package/easyui/jquery.min.js"></script>
	<script src="/package/easyui/jquery.easyui.min.js"></script>
	<script src="/package/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script src="/package/easyui/jquery.easyui.extend.js"></script>
	<script src='/package/qiniu/plupload.full.min.js'></script>
	<script src="/panda-res/js/system.js"></script>
	<script src="/package/layer/layer.js"></script>
	<script src="/panda-res/js/panda.core.js"></script>
	<script src="/panda-res/js/panda.controls.js"></script>
	<script src="/panda-platform/feedback/feedback.ctrl.js"></script>
	<script>
    	var ctrl = new org.netsharp.core.FeedbackCtrl();
		$(function(){

			ctrl.init();
		});
    </script>
</body>
</html>
