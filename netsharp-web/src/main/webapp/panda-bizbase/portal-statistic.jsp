<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<div>
		<div class="row" style="height:190px;">
        	<div class="cell cell-12">
	        	<div class="easyui-panel" title="销售简报" data-options="fit:true,border:false">
	        		<div>
	        			<a href="#" class="easyui-linkbutton" onclick="getBriefingCount(1)" data-options="toggle:true,group:'g2',plain:true,selected:true">今日</a>
						<a href="#" class="easyui-linkbutton" onclick="getBriefingCount(2)" data-options="toggle:true,group:'g2',plain:true">本周</a>
						<a href="#" class="easyui-linkbutton" onclick="getBriefingCount(3)" data-options="toggle:true,group:'g2',plain:true">本月</a>
						<a href="#" class="easyui-linkbutton" onclick="getBriefingCount(4)" data-options="toggle:true,group:'g2',plain:true">本年</a>
	        		</div>
	        		<div id="briefing" style="padding-top: 15px;">
	        			<p>
	        				<span>新增客户数：0个</span>
	        				<span class="padding-15">新增任务数：0个</span>
	        				<span class="padding-15">未分配任务数：0个</span>
	        				<span class="padding-15">未启动任务数：0个</span>
	        			</p>
	        			<p>
	        				<span>无任务的客户数：0个</span>
	        				<span class="padding-15">无法签单任务数：0个</span>
	        				<span class="padding-15">抽查异常任务数：0个</span>
	        				<span class="padding-15">公海：0个</span>
	        			</p>
	        		</div>
			    </div>
        	</div>	
        </div>
</div>
