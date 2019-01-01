System.Declare("org.netsharp.cache.plugin.web");
org.netsharp.cache.plugin.web.CachePluginListPart = org.netsharp.panda.commerce.ListPart.Extends({

	createButton : function (value, row, index) {
		var deleteButton = '<a href="#" onclick="' + this.context.instanceName + '.delteByKey(\'' + row.fullKey + '\')">清除</a>';
		var viewButton = '<a href="#" onclick="' + this.context.instanceName + '.viewByKey(\'' + row.fullKey + '\')">查看</a>';
		return deleteButton + '&nbsp;' + viewButton;
	},

	delteByKey : function (fullKey){
		
		var me = this;
		IMessageBox.confirm('确定要清除此缓存吗？', function(istrue) {

			if (istrue) {
				me.invokeService("delteByKey", [fullKey], function(data){
					if(data){
						me.reload();
						IMessageBox.info("清除成功！");
					}else{
						me.reload();
						IMessageBox.error("清除失败！");
					}
				});
			}
		});
	},
	
	viewByKey : function (fullKey){
		
		var me = this;
		this.invokeService("viewByKey", [fullKey], function(data){
			if(data){
				me.showDialog("查看","fa fa-file-text-o",800,400,data);
			}else{
				IMessageBox.error("查看失败！");
			}
		});
	},
	
	flushDB : function(){
		
		var me = this;
		IMessageBox.confirm('确定要清空缓存吗？', function(istrue) {

			if (istrue) {
				me.invokeService("flushDB", [], function(data){
					if(data){
						me.reload();
						IMessageBox.info("清空成功！");
					}else{
						me.reload();
						IMessageBox.error("清空失败！");
					}
				});
			}
		});
	},
	
	showDialog:function(title,icon,width,height,content){
		
    	if($("#pandaWindow").length ==0){

			$(document.body).append("<div id='pandaWindow'></div>");
    	}
		$("#pandaWindow").dialog({
            title: title,
            iconCls:icon, 
            content:content,
            width: width,
            height: height,
            modal: true,
            maximized:false,
            minimizable: false,
            maximizable: false,
            shadow: true,
            cache: false,
            closed: false,
            collapsible: false,
            resizable: false,
            doSize:true,
            inline: true,
            top:$(document).scrollTop() + ($(window).height()-height) * 0.5,
			left:$(document).scrollLeft() + ($(window).width()-width) * 0.5,
            loadingMessage: "数据加载中..."
        });
	},
})