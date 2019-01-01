System.Declare("org.netsharp.core");
org.netsharp.core.attachmentListController = System.Object.Extends({
	ctor: function() {
		this.jServiceLocator = null;
		this.service = "org.netsharp.attachment.AttachmentListPart";
		this.entityId = "";
		this.foreignKey = 0;
	},
	getSelections : function() {

		var rows = $("#attachment_grid").datagrid('getSelections');
		return rows;
	},
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
    queryString: function (name) {

        var result = location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
        if (result == null || result.length < 1) {

            return "";
        }

        return result[1];
    },
	invoke : function(month, pars, callback) {
		
		if (this.jServiceLocator == null) {
			
			this.jServiceLocator = new org.netsharp.core.JServiceLocator();
		}
		this.jServiceLocator.invoke(this.service, month, pars, callback);
	},
	init:function(){

		this.initDataGrid();
		this.initUpload();
	},
	initDataGrid:function(){
		
		this.entityId = this.queryString("entityId");
		this.foreignKey = this.queryString("foreignKey");
		
		var me = this;
		var pars = [];
		pars.push(this.entityId);
		pars.push(this.foreignKey);
		this.invoke("query", pars, function(data) {

			$("#attachment_grid").datagrid({
				height:570,
				rownumbers:true,
				 columns:[[
				           {field:'name',title:'名称',width:550},
				           {field:'fileExtend',title:'扩展名',width:50,align:"right"},
				           {field:'downLoadCount',title:'下载次数',width:60,align:"center"},
				           {field:'alias',title:'操作',width:60,align:"center",formatter:function(value,rowData,rowIndex){
				        	   //return "<a href='/download?name="+rowData.name+"&path="+rowData.path+"' onclick='listController.updateDownLoadCount("+rowData.id+");'>下载</a>";
				        	   
				        	   return "<a target='_blank' href='"+rowData.path+"' onclick='listController.updateDownLoadCount("+rowData.id+");'>下载</a>";
				           }},
				           {field:'creator',title:'上传人',width:60,align:"center"},
				           {field:'createTime',title:'上传时间',width:130,align:"center"}				           
				       ]],
				data:data
			});
		});
	},
	initUpload:function(){

		var upload = new org.netsharp.controls.AttachmentUpload();
		upload.parent = this;
		upload.init();
	},
	
    save: function (path,fileType,fileName) {

    	var me = this;
    	var entity = {
			name:fileName,
			fileExtend:fileType,
			path:path,
			viewCount:0,
			downLoadCount:0,
			foreignKey:me.foreignKey,
			entityId:me.entityId,
			entityState:EntityState.New
		};
        this.invoke("save", [entity], function (data) {
        	
        	me.initDataGrid();
        });
    },
    
    updateDownLoadCount:function(id){
    	
    	var me = this;
		this.invoke("updateDownLoadCount", [id], function(data) {
			me.initDataGrid();
		});
    },
    
	remove:function(){
		
		var count = this.getSelectionCount();

		if (count <= 0) {

			IMessageBox.warning("请选择要删除的记录！");

			return;
		}

		var me = this;
		IMessageBox.confirm("确认要删除选中的记录吗？", function(istrue) {

			if (istrue) {
				
				var ids = me.getSelectionIds();

				var pars = [];
				pars.push(ids);

				me.invoke("delete", pars, function(data) {
					me.initDataGrid();
				});
			}
		});
	}
});

org.netsharp.controls.AttachmentUpload = org.netsharp.controls.OSSUpload.Extends({
	ctor: function() {
		this.base();
		this.parent = null;
	},
	getButtonId:function(){
		
		return "btn_upload";
	},
	preview:function(path,file){
		
		if(System.isnull(path)){
			return;
		}
		debugger;
		this.parent.save(path,file.type,file.name);
	}
});
