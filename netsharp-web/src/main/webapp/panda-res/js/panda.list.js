/// <reference path="core.js"/>
/// <reference path="panda.js"/>
/// <reference path="pandasd.js"/>

org.netsharp.panda.commerce.ListPart = org.netsharp.panda.core.View.Extends({

	ctor : function() {
		this.base();
		this.queryModel = new org.netsharp.panda.QueryController();
		// -----------------------
		// EasyUI datagrid 对象
		// ----------------------
		this.datagrid = null;

		this.Sd = new org.netsharp.tools.SdDatagrid(this);
		
		//缓存当前控制器方便弹出窗体使用
		window.listController = this;
		
		this.cmenu = null;
	},
    onHeaderContextMenu: function(e, field){
    	
    	var me = this;
        e.preventDefault();
        if (this.cmenu == null){
        	me.createColumnMenu();
        }
        this.cmenu.menu('show', {
            left:e.pageX,
            top:e.pageY
        });
    },
	createColumnMenu:function (){
		
		var me = this;
		this.cmenu = $('<div/>').appendTo('body');
		this.cmenu.menu({
            onClick: function(item){
                if (item.iconCls == 'fa fa-check'){
                	me.datagrid.datagrid('hideColumn', item.name);
                	me.cmenu.menu('setIcon', {
                        target: item.target,
                        iconCls: 'icon-empty'
                    });
                } else {
                	me.datagrid.datagrid('showColumn', item.name);
                	me.cmenu.menu('setIcon', {
                        target: item.target,
                        iconCls: 'fa fa-check'
                    });
                }
            }
        });
        var fields = me.datagrid.datagrid('getColumnFields');
        for(var i=0; i<fields.length; i++){
            var field = fields[i];
            var col = me.datagrid.datagrid('getColumnOption', field);
            me.cmenu.menu('appendItem', {
                text: col.title,
                name: field,
                iconCls: 'fa fa-check'
            });
        }
    },
	// -----------------------
	// 获取列表选择的行记录
	// -----------------------
	getSelections : function() {

		var rows = $("#" + this.context.id).datagrid('getSelections');
		return rows;
	},

	// -----------------------
	// 获取已选择行的ID
	// -----------------------
	getSelectionIds : function() {

		var rows = this.getSelections();
		var ids = new Array();
		for ( var i = 0; i < rows.length; i++) {
			var row = rows[i];
			ids.push(row.id);
		}

		return ids.join('_');
	},

	getSelectionCount : function() {

		return this.getSelections().length;
	},

	getSelectedItem : function() {

		var items = this.getSelections();

		if (items.length == 0) {
			
			IMessageBox.info("您没有选择记录!");
			return null;
		} else if (items.length > 1) {

			IMessageBox.info("只能选择一条记录!");
			return null;
		} else {
			return items[0];
		}
	},

	setRelationItem : function(relationItem) {

		this.relationItem = relationItem;
		
		if(this.relationItem!=null){
			var relationId = this.relationItem.id;
			var filter = this.context.relationRole + "='" + relationId + "'";// OR	
			this.doQuery(filter);
		}
	},

	doubleClickRow : function(index, row) {

		var editLength = $("a[code='edit']").length;
		var viewLength = $("a[code='view']").length;
		if(editLength>0 || viewLength>0){
			
			this.edit(row.id);
		}
	},
	onLoadSuccess:function(data){

	},
	onSelect : function(rowIndex, rowData) {

	},

	// -----------------------
	// 根据对象打开详情页面
	// ----------------------
	open : function(obj, id) {

		var rows = this.getSelections();
		if (rows.length > 1) {
			IMessageBox.warning("只能选择一条记录！");
			return false;
		}

		var me = null;
		if (obj == undefined) {

			me = this;
			var rows = this.getSelections();
			if (rows.length == 0) {

				IMessageBox.warning("请选择记录！");
				return;
			} else {
				obj = rows[0];
			}
		} else {

			me = this;
		}

		var formUrl = me.context.formUrl;
		if (System.isnull(formUrl)) {
			return;
		}

		if (System.isnull(id)) {
			id = obj.id;
		}

		var url = System.Url.join(formUrl, "id=" + id);
		url = System.Url.getUrl(url);

		if(this.context.openMode == OpenType.window){
			
			url = System.Url.join(url, "openType="+OpenType.window);
			IMessageBox.open("编辑", url, this.context.windowWidth, this.context.windowHeight, function() {
				me.reload();
			});
			
		} else if(this.context.openMode == OpenType.redirect){
			
			url = System.Url.join(url, "openType="+OpenType.redirect);
			window.location.href = url;
		} else {
			
			url = System.Url.join(url, "openType="+OpenType.open);
			window.open(url);
		}

	},

	onAdding : function() {
		return true;
	},
	// -----------------------
	// 刷新
	// -----------------------
	reload : function() {
		
		$("#" + this.context.id).datagrid('unselectAll').datagrid('load');
	},
	
	detail:function(id){

		var formUrl = this.context.formUrl;
		if (System.isnull(formUrl)) {
			return;
		}
		var rows = this.getSelections();
		if(System.isnull(id)){

			if (rows.length > 1) {
				IMessageBox.warning("只能选择一条记录！");
				return;
			}
			
			if (rows.length ==0) {
				IMessageBox.warning("请选择一条记录！");
				return;
			}
			
			id = rows[0].id;
		}
		var url = System.Url.join(formUrl, "id=" + id);
		url = System.Url.getUrl(url);
		if(this.context.openMode == OpenType.window){

			var me = this;
			url = System.Url.join(url, "openType="+OpenType.window);
			IMessageBox.open("详情", url, this.context.windowWidth, this.context.windowHeight, function() {

				me.reload();
			});
			
		} else if(this.context.openMode == OpenType.redirect){
			url = System.Url.join(url, "openType="+OpenType.redirect);
			window.location.href = url;
		} else {
			url = System.Url.join(url, "openType="+OpenType.open);
			window.open(url);
		}
	},
	
	// -----------------------
	// 新增
	// -----------------------
	add : function() {

		if (!this.onAdding()) {
			return;
		}

		var fks = [];
      //wangyand 2015年10月9日 14:34:04注释 
		//if (this.context.relationRole != null && this.relationItem!=null&& this.context.relationRole != "parentId") {
	    if (this.context.relationRole != null && this.relationItem!=null) {
			fks.push(this.context.relationRole + ":" + this.relationItem.id);
		}

		var parentId = this.queryString("parentId");
		if (parentId != null && parentId != "") {
			fks.push("parentId:" + parentId);
		}
		
		if(fks.length>0){

			this.doAdd("fk=" + fks.join(";"));
		}else{
			this.doAdd();
		}

	},

	doAdd : function(queryString) {

		if (System.isnull(this.context.formUrl)) {
			IMessageBox.info("暂不支持表单!");
			return;
		}

		var me = this;
		var url = System.Url.getUrl(this.context.formUrl);
		if (!System.isnull(queryString)) {
			url = System.Url.join(url, queryString);
		}
		if(this.context.openMode == OpenType.window){
			
			url = System.Url.join(url, "openType="+OpenType.window);
			IMessageBox.open("新增", url, this.context.windowWidth, this.context.windowHeight, function() {
				
				me.reload();
			});
			
		} else if(this.context.openMode == OpenType.redirect){
			
			url = System.Url.join(url, "openType="+OpenType.redirect);
			window.location.href = url;
		} else {
			
			url = System.Url.join(url, "openType="+OpenType.open);
			window.open(url);
		}
	},

	// -----------------------
	// 编辑
	// -----------------------
	edit : function(id) {
		
		var formUrl = this.context.formUrl;
		if (System.isnull(formUrl)) {
			return;
		}

		if(System.isnull(id)){
			
			var row = this.getSelectedItem();
			if(row == null){
				return;
			}
			id = row.id;
		}

		var url = System.Url.join(formUrl, "id=" + id);
		url = System.Url.getUrl(url);

		if(this.context.openMode == OpenType.window){

			var me = this;
			url = System.Url.join(url, "openType="+OpenType.window);
			IMessageBox.open("编辑", url, this.context.windowWidth, this.context.windowHeight, function() {

				me.reload();
			});
			
		} else if(this.context.openMode == OpenType.redirect){
			url = System.Url.join(url, "openType="+OpenType.redirect);
			window.location.href = url;
		} else {
			url = System.Url.join(url, "openType="+OpenType.open);
			window.open(url);
		}
	},
	
	// -----------------------
	// 删除
	// -----------------------
	remove : function(id) {

		if(!System.isnull(id)){
			
			this.doRemove(id);
		}else{
			
			var count = this.getSelectionCount();
			if (count <= 0) {
				IMessageBox.info("请选择要删除的记录！");
				return;
			}
			var ids = this.getSelectionIds();
			this.doRemove(ids);
		}

	},

	doRemove : function(ids) {

		var me = this;
		IMessageBox.confirm("确认要删除选中的记录吗？", function(istrue) {

			if (istrue) {
				me.invokeService("delete", [ids], function(data) {

					me.reload();
					IMessageBox.toast("删除成功！");
				});
			}
		});
	},

	// -----------------------
	// 审核
	// -----------------------
	audit : function() {

		var count = this.getSelectionCount();
		if (count <= 0) {

			IMessageBox.warning("请选择要审核的记录！");
			return;
		}

		var ids = this.getSelectionIds();
		var me = this;
		this.invokeService("audit", [ids], function(data) {

			me.reload();
			IMessageBox.toast("审核成功！");
		});
	},

	// -----------------------
	// 启用，停用
	// -----------------------
	disabled : function(isDisabled) {

		var count = this.getSelectionCount();
		if (count <= 0) {

			IMessageBox.warning("请选择要操作的记录！");
			return;
		}

		var me = this;
		var ids = this.getSelectionIds();
		this.invokeService("disabled", [ids,isDisabled], function(data) {

			me.reload();
			IMessageBox.toast("操作成功！");
		});
	},

	// -----------------------
	// 排序
	// ----------------------
	sort : function(sortType) {

		var rows = this.getSelections();
		if (rows.length > 1) {
			IMessageBox.warning("只能选择一条记录！");
			return false;
		}

		if (rows.length == 0) {
			IMessageBox.warning("请选择记录！");
			return;
		}

		var me = this;
		this.invokeService("sort", [rows[0].id,sortType], function(data) {
			me.reload();
		});
	},

	// -----------------------
	// 附件
	// -----------------------
	attachment : function() {

		var count = this.getSelectionCount();
		if (count <= 0) {
			IMessageBox.info("请选择要上传附件的记录！");
			return;
		}
		if (count > 1) {
			IMessageBox.info("请选择一条记录进行上传附件！");
			return;
		}

		var ids = this.getSelectionIds();
		var url = '/panda-platform/attachment/attachment-list.jsp?foreignKey={0}&entityId={1}'.format(ids,this.context.entityId);
		var me = this;
		IMessageBox.open("附件", url, 1000, 600, function() {
			me.reload();
		}, true);
	},

	// -----------------------
	// 查询
	// -----------------------
	query : function() {

		this.queryModel.collectControl();
		var qpc = this.queryModel.getQueryParameters();
		var queryParams= this.queryModel.getFilterParameters();
		var filters = [];
		if(qpc === false){
			return;
		}
		
//		if(this.context.lazy&&qpc == false){
//			return;
//		}
		
		for ( var i = 0; i < qpc.length; i++) {

			filters.push(qpc[i].Filter);
		}
		var filter = filters.join(" AND ");
		this.doQuery(filter,queryParams);
		this.logQuery(filter);
	},

	doQuery : function(filter,queryParams) {

		var urls = this.getFilters(filter);
		var url = urls.join("&");
		url = System.Url.getUrl(url);
		url = encodeURI(url);
		this.resetUrl(url,queryParams);
		// this.reload();
	},
	
	getFilters:function(filter){
		
		var filters = [];
		if (!System.isnull(this.context.defaultFilter)) {
			filters.push(this.context.defaultFilter);
		}
		
		if (!System.isnull(this.context.urlFilter)) {
			filters.push(this.context.urlFilter);
		}

		if (!System.isnull(filter)) {
			filters.push(filter);
		}

		var urls = [this.context.queryUrl];
		if (filters.length > 0) {

			var where = filters.join(" AND ");
			urls.push("filter=" + where.replace(/=/g, "|"));
		}
		this.addExtraParams(urls);//添加额外的查询参数,如果客户端重写这个参数，那么控制器里需要重写一个方法
		return urls;
	},
	resetUrl : function(url,queryParams) {
		
		var qps = JSON.stringify(queryParams);;
		$("#" + this.context.id).datagrid({
			url : url,
			queryParams:{
				qps:qps
			}
		});
		this.setStyle();
	},
	logQuery : function (filter) {
	    try {
	        var controller;	        
	        var pars=[];	        
	        pars.push("查询:"+this.context.name);
	        pars.push(this.workspaceId);
	        pars.push(this.context.formUrl);
	        pars.push(this.context.entityId);
	        pars.push(filter);
	        var serviceLocator = new org.netsharp.core.JServiceLocator();
	        serviceLocator.invoke("org.netsharp.log.web.NLogController", "logQuery", pars, null, null, true);
	    } catch (Error) {

	    }
	},

	addExtraParams:function(urls){//添加额外的查询参数
		//return url;
	},
	// 导出excel
	exportExcel:function() {

		var opts = $("#" + this.context.id).datagrid('options');
		var url = opts.url.replace("query", "export");
		var id = "controller" + this.context.code + "exportExcel";
		$("#" + id).attr("target", "_blank");
		$("#" + id).attr("href", url);
	},
	
	//此注释的这段是平台批量导入和生成模板的脚本，请勿删除。caojianming,2016/4/14 PM
	//生成模板
	downTemplate:function(){

		var opts = $("#" + this.context.id).datagrid('options');
		var url = opts.url.replace("query", "downTemplate");
		var id = "controller" + this.context.code + "downTemplate";
		$("#" + id).attr("target", "_blank");
		$("#" + id).attr("href", url);
	
	},

	importExcel:function(){
		var me = this;
		$("#file1").remove();
		var fileHtml = '<input type="file" id="file1" name="file" />';
		$("body").append(fileHtml);
		$("#file1").bind("change",function(){

			var url = "/import?vid="+me.context.vid;
            $.ajaxFileUpload({
                    url: url,
                    secureuri: false,
                    fileElementId: 'file1',
                    dataType: 'JSON',
                    success: function (data, status)
                    {
						IMessageBox.info("导入成功！", function() {

							me.reload();
						});
//						if (data.data == true) {
//
//							IMessageBox.info(data.message, function() {
//
//								me.reload();
//							});
//						} else {
//							IMessageBox.error(data.message);
//						}
                    	
                    },
                    error: function (data, status, e)
                    {
                    	IMessageBox.error(e);
                    }
            });
            return false;
			
		});
		return  $("#file1").click();
	},
	setSelectedItem : function(rowindex, item) {
		
		var grid = $("#" + this.context.id);
		grid.datagrid('updateRow', {
			index : rowIndex,
			row : item
		});
	},
	getRowIndex : function(dataItem) {
		
		var grid = $("#" + this.context.id);
		var rowIndex = grid.datagrid('getRowIndex', item);
		return rowIndex;
	},
	onload : function() {
		
		this.bindKeyupEvent();
		this.setStyle();
	},
	bindKeyupEvent:function(){
		
		var me = this;
		$(document).keyup(function (e) {
		    if (e.keyCode == 13) {

		    	me.query();
		    }  
		});  
	},
	setStyle : function() {

		var toolbarHeight = 0;
		var queryFromHeight=0;
		var toolbar = $('#datagrid').find('.toolbar');
		var queryFrom = $('#queryFrom');
		if(toolbar.length>0){

			toolbarHeight = toolbar.height()+10;
		}
		if(queryFrom.length>0){

			queryFromHeight = queryFrom.height()+15;
		}

		var height = $('body').height() - 60 - toolbarHeight - queryFromHeight;
		$("#" + this.context.id).datagrid('resize', {
			height:height,
		});

		$('.easyui-combobox,.easyui-combogrid').combobox("initClearBtn");
	},
	showAdvancedQuery:function(queryId,datagridId){
		
		var me = this;
		var height = 450;
		var width = 850;
		var url = "/panda/advanced/query?id="+queryId+"&datagridId="+datagridId;
    	if($("#pandaWindow").length ==0){

			$(document.body).append("<div id='pandaWindow'></div>");
    	}
        $('#pandaWindow').dialog({
            title: "高级查询",
            width: width,
            height: height,
            content: '<iframe id="queryFrame" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:'+(height-83)+'px;overflow-y: auto; "></iframe>',
            modal: true,
            maximized:false,
            minimizable: false,
            maximizable: false,
            shadow: true,
            cache: false,
            closed: false,
            collapsible: false,
            resizable: false,
            inline: true,
            loadingMessage: '正在加载，请稍等片刻......',
            buttons: [{
                text:'查询',
                iconCls:'fa fa-search',
                handler:function(){

                	var queryController = window.frames["queryFrame"].contentWindow.queryController;
                	queryController.collectControl();
            		var qpc = queryController.getQueryParameters();
            		var filters = [];
            		for ( var i = 0; i < qpc.length; i++) {

            			filters.push(qpc[i].Filter);
            		}
            		var filter = filters.join(" AND ");
                	me.doQuery(filter);
                	$('#pandaWindow').dialog("close");
                }
            },{
                text:'关闭',
                iconCls:'fa fa-close',
                handler:function(){
                   
                	$('#pandaWindow').dialog("close");
                }
            }],
            onClose: function(){
            	
				$("#pandaWindow").parent("div.window").remove();
            }
        });
	},
	  
	getOptionToolbarItem:function(value,row,index,controller,config){

	  if(config == null){
		
		  return '';
	  }
	  
	  var builder = new System.StringBuilder();
	  var ss = config.split(';');
	  $(ss).each(function(i,s){
		  
		  var s2 = s.split(',');
		  var code = s2[0];
		  var css = s2[1];
		  var text = s2[2];
		  var btn = '<a class="grid-btn" href="javascript:{1}.{2}(\'{3}\');">{4}</a>'.format(css,controller,code,row.id,text);
		  builder.append(btn);
	  });
	  return builder.toString();
	},
	resetQuery:function(){
		
		this.queryModel.reset();
		this.query();
	}
});


//改变表格的宽度
window.onresize = function(){
	
//	console.log("宽度："+document.documentElement.clientWidth);
//	console.log("高度："+document.documentElement.clientHeight);
	
	$('.easyui-datagrid').datagrid('resize',{
		height:409,
		width:'100%'
	});
}