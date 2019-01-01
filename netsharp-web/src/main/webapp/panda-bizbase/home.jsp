<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home</title>
	<link href='/package/font-awesome/css/font-awesome.min.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/material/easyui.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/easyui.extend.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/color.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/icon.css' rel='stylesheet' type='text/css' />
	<link href='/panda-bizbase/home.css' rel='stylesheet' type='text/css' />
</head>
<body>
	<div class="page-content page-index">
		<jsp:include page="/panda-bizbase/account-info.jsp"></jsp:include>
		<jsp:include page="portal-statistic.jsp"></jsp:include>
         <!-- 
        <div class="row" style="height:300px;">
        	<div class="cell cell-7">
	        	<div class="easyui-panel" title="基本介绍" style="padding:10 20px;" data-options="fit:true,border:false">
			        <p>NetSharp 是一个基于最新 Web技术的企业级通用管理系统快速开发框架，可以帮助企业极大的提高工作效率，节省开发成本，提升品牌形象。<p>
					<p>您可以 NetSharp 为基础，快速开发各种MIS系统，如CMS、OA、CRM、ERP、POS等。<p>
					<p>NetSharp 紧贴业务特性，涵盖了大量的常用组件和基础功能，最大程度上帮助企业节省时间成本和费用开支。<p>
			    </div>
        	</div>
        	<div class="cell cell-5">
	        	<div class="easyui-panel" title="系统公告" data-options="fit:true,border:false">

			    </div>
        	</div>
        </div>
        <div class="row" style="height:150px;">
        	<div class="cell cell-12">
	        	<div class="easyui-panel" title="业务流程" data-options="fit:true,border:false">
			    </div>
        	</div>
        </div>
         <div class="row" style="height:200px;">
        	<div class="cell cell-2">
        		<div class="test_cell">公众号</div>
        	</div>
        	<div class="cell cell-2">
        		<div class="test_cell">企业号</div>
        	</div>
        	<div class="cell cell-2">
        		<div class="test_cell">钉钉</div>
        	</div>
        	<div class="cell cell-2">
        		<div class="test_cell">微博</div>
        	</div>
        	<div class="cell cell-2">
        		<div class="test_cell">支付宝</div>
        	</div>
        	<div class="cell cell-2">
        		<div class="test_cell">地图</div>
        	</div>        	
        </div>
        
        
         <div class="row" style="height:200px;">
        	<div class="cell cell-4">
        		<div class="test_cell">A</div>
        	</div>
        	<div class="cell cell-4">
        		<div class="test_cell">B</div>
        	</div>
        	<div class="cell cell-4">
        		<div class="test_cell">C</div>
        	</div>       	
        </div>
         <div class="row" style="height:200px;">
        	<div class="cell cell-6">
        		<div class="test_cell">A</div>
        	</div>
        	<div class="cell cell-6">
        		<div class="test_cell">B</div>
        	</div>    	
        </div>   
         <div class="row" style="height:200px;">
        	<div class="cell cell-3">
        		<div class="test_cell">A</div>
        	</div>
        	<div class="cell cell-9">
        		<div class="test_cell">B</div>
        	</div>    	
        </div>    -->
	</div>
</body>
<script src='/package/easyui/jquery.min.js'></script>
<script src='/package/layer/layer.js'></script>
<script src='/package/easyui/jquery.easyui.min.js'></script>
<script src='/package/easyui/locale/easyui-lang-zh_CN.js'></script>
<script src='/package/easyui/jquery.easyui.extend.js'></script>

<script src='/panda-res/js/system.js'></script>
<script src='/panda-res/js/panda.core.js'></script>
<script src='/gsb/supplier/home/js/portal-statistic.js'></script>

<script>
		//销售简报
		var brief = new com.gongsibao.crm.web.home.Briefing();
		function getBriefingCount(type){
			 brief.briefingCountPars1('getNewCustomerCount',type,function(count){
				$("#briefing p").eq(0).find('span').eq(0).text("新增客户数：" + count + "个");
			});
			brief.briefingCountPars2('getNewTasksCount',1,type,function(count){
				$("#briefing p").eq(0).find('span').eq(1).text("新增任务数：" + count + "个");
			});
			brief.briefingCountPars1('getUndistributed',type,function(count){
				$("#briefing p").eq(0).find('span').eq(2).text("未分配任务数：" + count + "个");
			});
			brief.briefingCountPars2('getUnStartTasksCount',1,type,function(count){
				$("#briefing p").eq(0).find('span').eq(3).text("未启动任务数：" + count + "个");
			});
			
			brief.briefingCountPars1('getNotTaskCount',type,function(count){
				$("#briefing p").eq(1).find('span').eq(0).text("无任务的客户数：" + count + "个");
			});
			brief.briefingCountPars1('getDefeatedCount',type,function(count){
				$("#briefing p").eq(1).find('span').eq(1).text("无法签单任务数：" + count + "个");
			});
			brief.briefingCountPars1('getCheckAbnormalCount',type,function(count){
				$("#briefing p").eq(1).find('span').eq(2).text("抽查异常任务数：" + count + "个");
			});
			brief.briefingCountPars2('getHighSeasCount',1,type,function(count){
				$("#briefing p").eq(1).find('span').eq(3).text("公海：" + count + "个");
			});
		};
		 $(function() {
			 getBriefingCount(1);
		}); 
	</script>
</html>
