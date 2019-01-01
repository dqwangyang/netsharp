System.Declare("org.netsharp.core");
org.netsharp.core.FeedbackCtrl = System.Object.Extends({
	ctor: function() {
		this.jServiceLocator = null;
		this.service = "org.netsharp.scrum.web.SupportFormPart";
		this.filePathList = new System.Dictionary();
	},
	invokeService : function(month, pars, callback) {
		
		if (this.jServiceLocator == null) {
			
			this.jServiceLocator = new org.netsharp.core.JServiceLocator();
		}
		this.jServiceLocator.invoke(this.service, month, pars, callback);
	},
	init:function(){
		
		var me = this;
		var upload = new org.netsharp.controls.OSSUpload();
		upload.multi_selection = true;
		upload.getButtonId = function(){
			
			return "button_upload";
		},
		upload.fileUploaded = function(up, file, info){
			
			var path = up.getOption().url+'/'+ up.getOption().multipart_params.key;
			var attachment = new Object();
			attachment.entityState = EntityState.New;
			attachment.path = path;
			attachment.alias = file.name;
			attachment.tableName = 'scrum_support';
			attachment.entityId = 'org.netsharp.scrum.entity.Support';
			me.filePathList.add(path,attachment);
			var fileHtml = '<a target="_blank" href="'+path+'">'+file.name+'</a><i title="删除" onclick="ctrl.removeFile(\''+path+'\',this);" class="fa fa-remove"></i>';
			var html = $("#files").html()+fileHtml;
			$("#files").html(html);
		}
		upload.init();
	},
	removeFile:function(path,dom){
		
		this.filePathList.remove(path);
		$(dom).prev().remove();
		$(dom).remove();
	},
	save:function(){
		
		var name = $('#name').val().trim();
		if(System.isnull(name)){
			
			layer.msg("请填写标题");
			return false;
		}
		
		if(name.length>100){
			
			layer.msg("标题长度不能超过100字");
			return false;
		}

		var content = $('#content').val().trim();
		if(System.isnull(content)){
			
			layer.msg("请填写反馈内容");
			return false;
		}
		
		if(content.length>500){
			
			layer.msg("反馈内容长度不能超过1000字");
			return false;
		}
		
		var me = this;
		this.invokeService("newInstance", [null], function (support) {
			  
			support.name = name;
			support.content = content;
			support.type = 5;
			support.ownerId = 3815;//系统升级后要重新设置值
			support.owner = {id:3815,name:'韩伟'};
			support.senderId = 3510;
			support.sender = {id:3510,name:'徐芳波'};
			support.estimateHours=0;
			
			var attachments = [];
			for(var i=0;i<me.filePathList.getLength();i++){
				
				var obj = me.filePathList.byIndex(i);
				if(obj){

					attachments.push(obj.value);
				}
			}
			support.attachments = attachments;
			me.doSave(support);
		});
	},
	doSave:function(support){
		
		this.invokeService("save", [support], function (support) {
			  
			layer.msg("提交成功，我们会尽快处理。");
			
			setTimeout(function(){

				window.top.layer.closeAll();
			},1000);
		});
	}
});