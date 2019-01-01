/// <reference path="core.js"/>

org.netsharp.panda = {};
org.netsharp.panda.core = {};
org.netsharp.panda.commerce = {};

//MenuItemClick拦截
org.netsharp.panda.core.MenuItemClick = function () {

    try {
    	
        if (event.ctrlKey && event.altKey){
            debugger;
        }
        var controller;
        if(window.listController){
        	controller=window.listController;
        }else{
        	controller=window.formController;
        }
        var pars=[];
        pars.push("按钮:"+event.target.textContent);
        pars.push(controller.workspaceId);
        if(controller.context.formUrl!=null){
        	
        	pars.push(controller.context.formUrl);
        }else{
        	pars.push("");
        }
        pars.push(controller.context.entityId);
        var serviceLocator = new org.netsharp.core.JServiceLocator();
        serviceLocator.invoke("org.netsharp.log.web.NLogController", "log", pars, null, null, true);
    }
    catch (Error) {

    }
};


org.netsharp.panda.core.Workspace = System.Object.Extends({
    ctor: function () {
    	
        this.parts = new System.Dictionary();
        this.partName=null;
    },
    addpart: function (key, part) {

        this.parts.add(key, part);
    },
    getpart: function (key) {

        return this.parts.byKey(key);
    },

    onTabSelect: function (title, index) {

        var ps = this.parts.innerValues;
        for (var i = 0; i < ps.length; i++) {
        	
            var part = ps[i].value;
            if (System.isnull(part.context) || System.isnull(part.context.name)) {     	
                continue;
            }

            if (part.context.name == title) {       	
                part.onTabSelect(title, index);
            }
        }
    }
});

//------------------------------------------------------------------------------------------------------------------------------------------
// View
//------------------------------------------------------------------------------------------------------------------------------------------
org.netsharp.panda.core.View = System.Object.Extends({
    ctor: function () {
        this.context = new Object();
        this.statusControls = [];
        this.parent = null;
        this.subs = [];
        this.relationItem = null;
        this.currentItem = null;
        this.Sd = undefined;
        this.header = null;
        this.toolbar = null;
    },
    notifyCurrentItemChanged: function () {

        var currentItem = this.getCurrentItem();
        for (var i = 0; i < this.subs.length; i++) {
        	
            var sub = this.subs[i];
            sub.setRelationItem(currentItem);
        }
    },

    getCurrentItem: function () {

        return this.currentItem;
    },

    setCurrentItem: function (currentItem) {

        this.currentItem = currentItem;
    },

    getRelationItem: function () {

        return this.relationItem;
    },

    setRelationItem: function (relationItem) {

        this.relationItem = relationItem;
    },

    collectStatusControl: function (ids) {

        for (var i = 0; i < ids.length; i++) {

            var id = this.context.instanceName+ids[i];
            var control = document.getElementById(id);
            this.statusControls.push(control);
        }
    },

    setState: function () {
 
        for (var i = 0; i < this.statusControls.length; i++) {
        	
            var uielement = this.statusControls[i];
            if (uielement == undefined || uielement == null) {

                return;
            }

            //var name = uielement.id;        
            var name = $(uielement).attr("code");
            var methodName = "get" + name + "State";
            var state = null;
            var expression = "if (this." + methodName + " != null && this." + methodName + " != undefined) {state=this." + methodName + "();}";
            eval(expression);
            if (state != null) {

                this.setElementState(uielement, state);
            }
        }
    },

    setElementState: function (uielement, state) {

        if (state == UiElementState.Hide) {
        	$(uielement).hide();

        }else if (state == UiElementState.Disable) {

        	$(uielement).linkbutton("disable");
        }else if (state == UiElementState.Enable) {

        	$(uielement).linkbutton("enable");
        }else {
        	$(uielement).show();
        }
    },

    invokeService: function (method, pars, callback, isAsyn, errorCallback) {

    	IMessageBox.loading.show();
        var serviceLocator = new org.netsharp.core.JServiceLocator();
        var me = this;
        var thisCallback = function (data) {
        	
        	IMessageBox.loading.hide();
            if (!System.isnull(callback)) {
            	
                callback(data);
            }
            me.setState();
        };
        serviceLocator.invoke(this.context.service, method, pars, thisCallback, this.context.vid, isAsyn, errorCallback);
    },
    queryString: function (name,callback) {

        var result = location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
        if (result == null || result.length < 1) {

            return "";
        }
        return result[1];
    },

    onResizeColumn: function (datagridId, field, width) {

        this.invokeService("resizeColumn", [datagridId, field, width], null, true);
    },
    onTabSelect: function (title, index) {
    	
    },
    onload: function () {
    	
    },
    disableToolbarItems:function(itemIds){
    	
    	$(itemIds).each(function(i,ti){
    		
    		$("#"+ti).linkbutton("disable");
    	});
    },
    enableToolbarItems:function(itemIds){
    	
    	$(itemIds).each(function(i,ti){
    		
    		$("#"+ti).linkbutton("enable");
    	});
    },

	/******************************************
	 * 
	 * 栏目设置
	 * 
	 */	
	fields : function() {
		
		var url = "/panda/platform/datagrid/form?id="+this.context.datagridId+"&openType=open";
		window.open(url);
	},
	// -----------------------
	// 查询方案
	// -----------------------
	fields_query : function() {
		
		var queryprojectId = this.context.queryprojectId;
		if(queryprojectId == undefined || queryprojectId == null){

			IMessageBox.info("当前列表未创建查询方案！");
			return;
		}
		
		var url = "/panda/platform/queryproject/form?id="+queryprojectId+"&openType=open";
		window.open(url);
	},	
	toolbar:function(){
		
		IMessageBox.toast('工具栏设置！');
	},
    cleanCache:function(){
    	
    	IMessageBox.toast('清除成功！');
    }
});

//------------------------------------------------------------------------------------------------------------------------------------------
//CustomCtrl 自定义控制器
//------------------------------------------------------------------------------------------------------------------------------------------
org.netsharp.panda.core.CustomCtrl = System.Object.Extends({
    ctor: function () {

    	this.service = null;
    },
    invokeService: function (method, pars, callback, isAsyn, errorCallback) {

        var serviceLocator = new org.netsharp.core.JServiceLocator();
        var me = this;
        var thisCallback = function (data) {
        	
            if (!System.isnull(callback)) {
            	
                callback(data);
            }
        };
        serviceLocator.invoke(this.service, method, pars, thisCallback, null, isAsyn, errorCallback);
    },
    queryString: function (name) {

        var result = location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
        if (result == null || result.length < 1) {

            return "";
        }
        return result[1];
    }
});