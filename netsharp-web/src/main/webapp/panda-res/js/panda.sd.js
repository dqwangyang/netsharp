/// <reference path="core.js"/>
/// <reference path="system.js"/>
/// <reference path="workbench.js"/>

System.Declare("org.netsharp.tools");

org.netsharp.tools.Sd = System.Object.Extends({
    ctor: function (view) {

        this.view = view;
        this.service = "org.netsharp.panda.core.SdService";
    },
    setPart:function(){
    	
		var workspaceId = this.view.workspaceId;
		var url = "/panda/platform/workspace/form?id="+workspaceId;
		window.open(url);
    },
    setToolbar: function () {

		var toolbarId = this.view.context.toolbarId;
    	if(toolbarId){

    		var url = "/panda/platform/toolbar/form?id="+toolbarId;
    		window.open(url);
    	}
    },
    exportSql : function(){

    	var pars = [];
    	pars.push("vid="+this.view.context.vid);
    	pars.push("type=part");
    	var url = "/nav/panda-platform/sqlGenerator?" + pars.join("&") ;
		window.open(url);
    },
    cleanCache: function () {

    	IMessageBox.info('暂未实现');
    }
});

org.netsharp.tools.SdDatagrid = org.netsharp.tools.Sd.Extends({
	
    setFields : function(){
    	
		var datagridId = this.view.context.datagridId;
		var url = "/panda/platform/datagrid/form?id="+datagridId;
		window.open(url);
    },
    setQuery:function(){
    	
		var queryprojectId = this.view.context.queryprojectId;
		var url = "/panda/platform/queryproject/form?id="+queryprojectId;
		window.open(url);
    }
});

org.netsharp.tools.SdForm = org.netsharp.tools.Sd.Extends({

    setFields : function(){
    	
		var formId = this.view.context.formId;
		var url = "/panda/platform/form/form?id="+formId+"&openType=open";
		window.open(url);
    },
    exportEntitySql:function(){
    	
    	IMessageBox.info('暂未实现');
    }
});

org.netsharp.tools.SdDetailPart = org.netsharp.tools.SdDatagrid.Extends({
	
});

org.netsharp.tools.SdSummaryReport = org.netsharp.tools.Sd.Extends({

});


