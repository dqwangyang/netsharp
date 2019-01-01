System.Declare("org.netsharp.web");

org.netsharp.web.PluginListPart = org.netsharp.panda.commerce.ListPart.Extends({
	
    sql : function(){
        var item = this.getSelectedItem();
        if(item==null){
        	alert("请选择资源节点记录");
        }
    	
    	var pars = [];
    	pars.push("id="+item.id);
    	pars.push("type=plugin");
    	var url = "/nav/panda-platform/sqlGenerator?" + pars.join("&") ;
		window.open(url);
    },
    
    install : function(){
    	window.alert("暂不支持插件安装");
    },
    
    uninstall : function(){
    	window.alert("暂不支持插件卸载");
    }
});
