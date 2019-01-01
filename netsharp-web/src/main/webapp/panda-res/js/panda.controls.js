/// <reference path="core.js"/>

System.Declare("org.netsharp.controls");

org.netsharp.controls.Toolbar = System.Object.Extends({
	ctor: function() {
		this.items = null;
	}
});

org.netsharp.controls.Control = System.Object.Extends({
	ctor: function() {
		this.propertyName = null;
		this.uiElement = null;
		this.formName = null;
	},
	init:function(){
		
	},
	get:function(entity){
		
	},
	set:function(entity){
		
	},
	clear: function() {

	},
	disable: function() {
	},
	enable: function() {
	}
});
org.netsharp.controls.Label = org.netsharp.controls.Control.Extends({
	ctor: function() {
		this.base();
	},
	set: function(entity) {
		
		var propertyValue = null;
		var expression;
		if (this.propertyName.indexOf('_') != -1) {

			var arr = this.propertyName.split('_');
			try{

				var expression = 'propertyValue=entity.'+arr.join('.')+';';
				eval(expression);
			}catch(e){
				
			}
		} else {
			
			propertyValue = entity[this.propertyName];
		}

		var optionsExpression = $(this.uiElement).attr('data-options');
		if(!System.isnull(optionsExpression)){
			
			eval(' var options = {'+optionsExpression+'};');
			var type = options.type;
			if(type){
				
				var items = PandaHelper.Enum.get(type);
				propertyValue = items[propertyValue];
			}
		}
		if (System.isnull(propertyValue)) {
			propertyValue = "-";
		}
		$(this.uiElement).text(propertyValue);
	}
});

org.netsharp.controls.TextBox = org.netsharp.controls.Control.Extends({
	ctor: function() {
		this.base();
	},
	get: function(entity) {

		var propertyValue = this.uiElement.value;
		propertyValue = propertyValue.replace(new RegExp("\r\n|\n", "gim"), "");
		var expression;
		if (this.propertyName.indexOf('.') != -1) {
			
			var leftProperty = this.propertyName.split('.')[0];
			if(entity[leftProperty] != null){
				
				entity[this.propertyName] = propertyValue;
			}
		} else {
			
			entity[this.propertyName] = propertyValue;
		}

	},

	set: function(entity) {

		var propertyValue = null;

		var expression;
		if (this.propertyName.indexOf('.') != -1) {
			
			var leftProperty = this.propertyName.split('.')[0];
			if(entity[leftProperty] != null){
				
				propertyValue = entity[this.propertyName];
			}
		} else {
			
			propertyValue = entity[this.propertyName];
		}

		if (System.isnull(propertyValue)) {
			propertyValue = "";
		}

		this.uiElement.value = propertyValue;
		this.setAfter();
	},

	setAfter: function() {

	},

	clear: function() {
		this.uiElement.value = "";
	},
	disable: function() {
		$(this.uiElement).prop("disabled", true);
	},
	enable: function() {
		var _disabled = $(this.uiElement).attr("_disabled");
		if (_disabled &&_disabled == 'true') {
			return;
		}
		$(this.uiElement).prop("disabled", false);
	}
});

org.netsharp.controls.EncryptionBox = org.netsharp.controls.TextBox.Extends({
	ctor: function() {
		this.base();
	},
	get: function(entity) {

		var propertyValue = $(this.uiElement).passwordbox('getValue');
		propertyValue = propertyValue.replace(new RegExp("\r\n|\n", "gim"), "");
		var expression;
		if (this.propertyName.indexOf('.') != -1) {
			
			var leftProperty = this.propertyName.split('.')[0];
			if(entity[leftProperty] != null){
				
				entity[this.propertyName] = propertyValue;
			}
		} else {
			
			entity[this.propertyName] = propertyValue;
		}

	},

	set: function(entity) {

		var propertyValue = null;

		var expression;
		if (this.propertyName.indexOf('.') != -1) {
			
			var leftProperty = this.propertyName.split('.')[0];
			if(entity[leftProperty] != null){
				
				propertyValue = entity[this.propertyName];
			}
		} else {
			
			propertyValue = entity[this.propertyName];
		}

		if (System.isnull(propertyValue)) {
			propertyValue = "";
		}

		$(this.uiElement).passwordbox('setValue',propertyValue);
		this.setAfter();
	},

	setAfter: function() {

	},

	clear: function() {
		$(this.uiElement).passwordbox('clear');
	},
	disable: function() {
		
		$(this.uiElement).passwordbox('disable');
	},
	enable: function() {
		var _disabled = $(this.uiElement).attr("_disabled");
		if (_disabled &&_disabled == 'true') {
			return;
		}
		$(this.uiElement).passwordbox('enable');
	}
});

org.netsharp.controls.PasswordTextBox = org.netsharp.controls.TextBox.Extends({
	ctor: function() {
		this.base();
	}
});

org.netsharp.controls.SwitchButton = org.netsharp.controls.Control.Extends({
	ctor: function() {
		this.base();
	},
	get: function(entity) {
		
		var propertyValue = $(this.uiElement).switchbutton('options').checked;
		entity[this.propertyName] = propertyValue;
	},
	set: function(entity) {

		var propertyValue = entity[this.propertyName]||false;
		//先全部设置为true，才能触发改变事件
		$(this.uiElement).switchbutton('check');
		if(propertyValue===false){

			$(this.uiElement).switchbutton('uncheck');
		}
	},
	clear: function() {
		
		$(this.uiElement).switchbutton('reset');
	},
	disable: function() {
		
		$(this.uiElement).switchbutton('disable');
	},
	enable: function() {
		$(this.uiElement).switchbutton('enable');
	}
});


org.netsharp.controls.QiNiuUpload = org.netsharp.controls.TextBox.Extends({
	ctor: function() {
		this.base();
	},
	init: function() {

		var buttonId = "button_" + this.propertyName;
		var me = this;
		var uploader = Qiniu.uploader({
			runtimes: 'html5,flash,html4',
			browse_button: buttonId,
			max_file_size: '1000mb',
			flash_swf_url: 'plupload/Moxie.swf',
			dragdrop: true,
			chunk_size: '4mb',
			multi_selection: false,
			filters: {
				mime_types: [{
					title: "Image files",
					extensions: "jpg,jpeg,gif,png"
				}]
			},
			uptoken_func: function(file) {
				var token = '';
				var serviceLocator = new org.netsharp.core.JServiceLocator();
				serviceLocator.invoke("org.netsharp.web.QiniuController", "getToken", [],
					function(date) {

						token = date;

					},
					null, false);
				return token;
			},

			domain: 'http://o9sowo9h1.bkt.clouddn.com/',
			get_new_uptoken: true,
			// downtoken_url: '/downtoken',
			// unique_names: true,
			// save_key: true,
			// x_vars: {
			//     'id': '1234',
			//     'time': function(up, file) {
			//         var time = (new Date()).getTime();
			//         // do something with 'time'
			//         return time;
			//     },
			// },
			auto_start: true,
			log_level: 5,
			init: {
				'FilesAdded': function(up, files) {
					plupload.each(files, function(file) {

					});
				},
				'BeforeUpload': function(up, file) {

				},
				'UploadProgress': function(up, file) {

				},
				'UploadComplete': function() {

				},
				'FileUploaded': function(up, file, info) {
					var url = 'http://o9sowo9h1.bkt.clouddn.com/' + file.id;
					$("#" + me.propertyName).filebox("setText", url);
					me.preview(url,file);
				},
				'Error': function(up, err, errTip) {
					IMessageBox.info('服务器繁忙，请刷新重试!' + errTip);
				},
				'Key': function(up, file) {
					return file.id;
				}
			}
		});
	},
	get: function(entity) {

		var propertyValue = $("#" + this.propertyName).filebox("getText");
		propertyValue = propertyValue.replace(new RegExp("\r\n|\n", "gim"), "");
		entity[this.propertyName] = propertyValue;
	},

	set: function(entity) {

		var propertyValue = entity[this.propertyName];
		if (System.isnull(propertyValue)) {
			propertyValue = "";
		}
		$("#" + this.propertyName).filebox("setText", propertyValue);
		
		this.preview(propertyValue);
	},

	clear: function() {
		$("#" + this.propertyName).filebox("setValue", "");
	},
	disable: function() {
		$("#" + this.propertyName).filebox("disabled");
	},
	enable: function() {
		$("#" + this.propertyName).filebox("enable");
	},
	preview:function(path,file){

	    var filebox = $(this.uiElement).next();
	    var labelTd = filebox.parent().prev();
	    var text = labelTd.text().trim();
		if(System.isnull(path)){
			
			labelTd.html(text);
			return;
		}
	    labelTd.html('<a target="_blank" href="'+path+'" class="btn-preview">'+text+'</a>');
	}
});

org.netsharp.controls.OSSUpload = org.netsharp.controls.QiNiuUpload.Extends({
	ctor: function() {
		this.base();
		this.multi_selection = false;
	},
	getButtonId:function(){
		
		return "button_" + this.propertyName;
	},
	init:function(){
		
		var buttonId = this.getButtonId();
		var filtersStr = $('#'+this.propertyName).attr('filters');
		var filtersObj = null;
		if(!System.isnull(filtersStr)){
			
			filtersStr = filtersStr.replaceAll('\'','"');
			filtersObj = JSON.parse(filtersStr);
		}

		var me = this;
		var options = {
				runtimes : 'html5,flash,silverlight,html4',
				browse_button :buttonId, 
				// container: document.getElementById('container'),
				flash_swf_url : '/package/plupload/js/Moxie.swf',
				silverlight_xap_url : '/package/plupload/js/Moxie.xap',
			    url : 'http://oss.aliyuncs.com',
			    multi_selection: me.multi_selection,
			    prevent_duplicates : true,
				init: {
					PostInit: function() {
//			            me.setUploadParam(uploader);
					},

					FilesAdded: function(up, files) {

						me.filesAdded(up, files);
						//me.setUploadParam(up);
						uploader.start();
					},

					UploadProgress: function(up, file) {
						//IMessageBox.loading.show();
						me.uploadProgress(up, file);
					},
					BeforeUpload: function(up, file) {
						
						me.setUploadParam(up);
					},
					FileUploaded: function(up, file, info) {

						me.fileUploaded(up, file, info);
						//IMessageBox.loading.hide();
			            if (info.status == 200)
			            {
			            	var path = up.getOption().url+'/'+ up.getOption().multipart_params.key;
							$("#" + me.propertyName).filebox("setText", path);
							me.preview(path,file);
			            }
			            else
			            {
			            	IMessageBox.info(info.response);
			            } 
					},
					UploadComplete:function(uploader,files){
						
					},
					Error: function(up, err) {

						IMessageBox.error(err.response);
					}
				}
			};
		if(filtersObj){

			options.filters = filtersObj;
		}

		var uploader = new plupload.Uploader(options);
		uploader.init();
	},
	filesAdded:function(up, files){
		
		
	},
	uploadProgress:function(up, files){
		
		
	},
	fileUploaded:function(up, file, info){
		
		
	},
	setUploadParam:function (up)
	{
		var me = this;
		var serviceLocator = new org.netsharp.core.JServiceLocator();
		serviceLocator.invoke("org.netsharp.web.AliyunOssController", "getOssConfig", [],function(data) {

			config = data;
			var lastIndex = up.files.length -1;
			var suffix= me.getSuffix(up.files[lastIndex].name);
			var filename=  me.randomString() + suffix;
	        var newMultipartParams = {
	            'key' : config.dir + '_' + filename,
	            'policy': config.policyBase64,
	            'OSSAccessKeyId': config.accessid, 
	            'success_action_status' : '200',
	            'signature': config.signature,
	        };

	        up.setOption({
	            'url': config.host,
	            'multipart_params': newMultipartParams
	        });
		},null, false);
	},
	randomString:function (len) {
		len = len || 32;
		var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';   
		var maxPos = chars.length;
		var pwd = '';
		for (i = 0; i < len; i++) {
			pwd += chars.charAt(Math.floor(Math.random() * maxPos));
		}
	    return pwd;
	},
	getSuffix:function (filename) {
	    pos = filename.lastIndexOf('.')
	    suffix = ''
	    if (pos != -1) {
	        suffix = filename.substring(pos)
	    }
	    return suffix;
	}
});

org.netsharp.controls.ImageBox = org.netsharp.controls.Control.Extends({
	ctor: function() {
		this.base();
	},
	get: function(entity) {

	},

	set: function(entity) {

		var propertyValue = System.getProperty(entity, this.propertyName);

		if (System.isnull(propertyValue)) {
			this.uiElement.src = System.Url.getUrl("/panda-res/images/nopic.jpg");
		} else {
			this.uiElement.src = propertyValue;
		}

		this.setAfter();
	},

	setAfter: function() {

	},

	clear: function() {

		this.uiElement.value = "";

	}
});


org.netsharp.controls.NumberBox = org.netsharp.controls.Control.Extends({
	ctor: function() {
		this.base();
	},
	get: function(entity) {

		var propertyValue = $(this.uiElement).numberbox('getValue'); // this.uiElement.value;
		entity[this.propertyName] = propertyValue;
	},

	set: function(entity) {

		var propertyValue = entity[this.propertyName];
		if (System.isnull(propertyValue)) {
			propertyValue = "";
		}

		$(this.uiElement).numberbox('setValue', propertyValue);
	},

	clear: function() {

		$(this.uiElement).numberbox('setValue', "");

	},
	disable: function() {
		
		$(this.uiElement).numberbox("disable");
	},
	enable: function() {
		var _disabled = $(this.uiElement).attr("_disabled");
		if (_disabled &&_disabled == 'true') {
			return;
		}
		$(this.uiElement).numberbox("enable");
	}
});

org.netsharp.controls.DecimalBox = org.netsharp.controls.NumberBox.Extends({
	ctor: function() {
		this.base();
	}
});
org.netsharp.controls.DecimalFenBox = org.netsharp.controls.NumberBox.Extends({
	ctor: function() {
		this.base();
	},
	get: function(entity) {

		var propertyValue = $(this.uiElement).numberbox('getFloatValue');
		entity[this.propertyName] = propertyValue*100;
	},

	set: function(entity) {

		var propertyValue = entity[this.propertyName];
		if (System.isnull(propertyValue)) {
			propertyValue = "";
		}

		$(this.uiElement).numberbox('setFloatValue', propertyValue/100);
	}
});

org.netsharp.controls.PercentageBox = org.netsharp.controls.NumberBox.Extends({
	ctor: function() {
		this.base();
	}
});

org.netsharp.controls.CurrencyBox = org.netsharp.controls.NumberBox.Extends({
	ctor: function() {
		this.base();
	}
});
org.netsharp.controls.FileBox = org.netsharp.controls.TextBox.Extends({
	ctor: function() {
		this.base();
	},
	get: function(entity) {

		var propertyValue = $(this.uiElement).filebox('getText'); // this.uiElement.value;
		entity[this.propertyName] = propertyValue;
	},
	init:function(){

		var me = this;
		var text =  $(this.uiElement).filebox('getText');
		$(this.uiElement).filebox({
			onChange:function(newValue, oldValue){
				
				fileBoxChange(this,newValue, oldValue);
			}
		});
		$(this.uiElement).filebox('setText',text);
		
		if(text){

			this.preview(text);
		}
		
		//更新事件
		var fileBoxChange=function(fileObj,newValue, oldValue){
			
			if (!(window.File || window.FileReader || window.FileList || window.Blob)) {

			    IMessageBox.error("请更换Chrome浏览器！");
			    return false;
			}
			
			var $fileElement = $(fileObj).next().children('input[type="file"]:first');
			var files = $fileElement.prop('files');
			if(files.length == 0){

			    return false;
			}

			var fileElementId = $fileElement.attr('id');
			doUpload(fileElementId,newValue);
		};
		
		//执行上传
		var doUpload=function(fileElementId,name){
			
	        $.ajaxFileUpload({
	            url: '/upload?folder=default&name='+name,
	            secureuri: false,
	            //data:{folder:'default',name:'name'},
	            fileElementId: fileElementId,
	            dataType: 'json',
	            success: function (data, status){
	            	
	            	if(data.type == 'error'){

	            		IMessageBox.error(data.message);
	            	}else{

		            	var path = data.data;
		            	$(me.uiElement).filebox('setText',path);
						//重新初始化
		            	me.init();
	            	}
	            },
	            error: function (data, status, e){

	                IMessageBox.error('上传失败');
	            }
	        })
	        return false;
		}
	},
	preview:function(path){
		
		if(System.isnull(path)){
			
			return;
		}
		if(System.isnull(path)){
			return;
		}
	    var filebox = $(this.uiElement).next();
	    var labelTd = filebox.parent().prev();
	    var text =labelTd.text().trim();
	    labelTd.html('<a target="_blank" href="'+path+'" class="btn-preview">'+text+'</a>');
//	    var filebox = $(this.uiElement).next();
//	    filebox.parent().find('.btn-preview').remove();
//	    filebox.after('<a target="_blank" href="'+path+'" class="btn-preview">下载</a>');
	},
	set: function(entity) {
		
		var propertyValue = entity[this.propertyName];
		if (System.isnull(propertyValue)) {
			propertyValue = "";
		}

		$(this.uiElement).filebox('setText', propertyValue);
		this.preview(propertyValue);
	},

	clear: function() {

		$(this.uiElement).filebox('setText', "");

	},
	disable: function() {
		$(this.uiElement).filebox("disable");
	},
	enable: function() {
		var _disabled = $(this.uiElement).attr("_disabled");
		if (_disabled &&_disabled == 'true') {
			return;
		}
		$(this.uiElement).filebox("enable");
	}
});

org.netsharp.controls.PictureFileBox = org.netsharp.controls.FileBox.Extends({
	ctor: function() {
		this.base();
	},	
	preview:function(path){
		if(System.isnull(path)){
			
			return;
		}
	    var filebox = $(this.uiElement).next();
	    filebox.parent().find('.btn-preview').remove();
	    filebox.after('<a target="_blank" href="'+path+'" class="btn-preview">预览</a>');
//	    filebox.after('<a href="javascript:void(0)" class="btn-preview">预览</a>');
//		$(filebox.next()).tooltip({
//			position: 'bottom',
//			showEvent:'click',
//			hideDelay:1000,
//			content: '<img class="image-preview" src="'+path+'"/>'
//		});
	}
});


org.netsharp.controls.HyperLink = org.netsharp.controls.Control.Extends({

	set: function(entity) {

		var propertyValue = entity[this.propertyName];
		if (propertyValue == undefined || propertyValue == null) {
			propertyValue = "";
		}

		$("#" + this.propertyName).attr("value", propertyValue);

		if (propertyValue != null && propertyValue != undefined) {

			$("#" + this.propertyName).text("查看");

			var path = $("#" + this.propertyName).attr("path");
			// $("#" + this.propertyName).attr("href", "/Dev/uploadfile/" +
			// propertyValue);

			$("#" + this.propertyName).attr("href", path + propertyValue);

		} else {

			$("#" + this.propertyName).text("暂无");
			$("#" + this.propertyName).attr("disabled", "disabled");
		}

		// this.uiElement.value = propertyValue;
	},
	get: function(entity) {

		var propertyValue = $("#" + this.propertyName).attr("value");
		entity[this.propertyName] = propertyValue;
	}
});

org.netsharp.controls.TextArea = org.netsharp.controls.Control.Extends({

	get: function(entity) {

		var propertyValue = this.uiElement.value;
		entity[this.propertyName] = propertyValue;
	},

	set: function(entity) {

		var propertyValue = entity[this.propertyName];
		if (propertyValue == undefined || propertyValue == null) {
			propertyValue = "";
		}

		this.uiElement.value = propertyValue;
	},

	clear: function() {

		this.uiElement.value = "";

	},
	disable: function() {
		$(this.uiElement).prop("disabled", true);
	},
	enable: function() {
		var _disabled = $(this.uiElement).attr("_disabled");
		if (_disabled &&_disabled == 'true') {
			return;
		}
		$(this.uiElement).prop("disabled", false);
	}
});

org.netsharp.controls.Editor = org.netsharp.controls.Control.Extends({

	ctor: function() {

		this.editorObj = null;
	},

	loadEditor: function() {

		var height = $(this.uiElement).height();
		this.editorObj = $(this.uiElement).froalaEditor({
			
			height: height,
			language: 'zh_cn',
			saveURL: '/nav/package/froala-editor/jsp/imgupload',//上传图片地址
	        toolbarButtons: ['fullscreen', 'bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', 'fontFamily', 'fontSize',
	                         '|', 'color', 'inlineStyle', 'paragraphStyle',
	                         '|', 'paragraphFormat', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', '-', 'insertLink', 'insertImage', 'insertTable',
	                         '|', 'quote', 'insertHR', 'undo', 'redo', 'clearFormatting', 'selectAll']
	      
		});
		
		//上传暂时未实现
	},

	get: function(entity) {

		if (System.isnull(this.editorObj)) {
			this.loadEditor();
		}

		
		var propertyValue = encodeURIComponent($(this.uiElement).froalaEditor('html.get'));

		/*
		 * var isValidated = this.validate();
		 *
		 * if (!isValidated) {
		 *
		 * return; }
		 */
		entity[this.propertyName] = propertyValue;
	},

	set: function(entity) {

		if (System.isnull(this.editorObj)) {
			this.loadEditor();
		}

		this.clear('');

		var propertyValue = entity[this.propertyName];
		if (propertyValue == undefined || propertyValue == null) {
			propertyValue = "";
		}

		var value = decodeURIComponent(propertyValue);
		$(this.uiElement).froalaEditor('html.set', value);
	},

	clear: function() {
		
		if (!System.isnull(this.editorObj)) {
			$(this.uiElement).froalaEditor('html.set', '');
		}
	}
});

org.netsharp.controls.CheckBox = org.netsharp.controls.Control.Extends({

	get: function(entity) {

		var propertyValue = this.uiElement.checked;
		entity[this.propertyName] = propertyValue;
	},

	set: function(entity) {

		var propertyValue = entity[this.propertyName];
		if (propertyValue == undefined || propertyValue == null) {
			propertyValue = "";
		}

		this.uiElement.checked = propertyValue == "true" || propertyValue == true;
	},

	clear: function() {

		this.uiElement.checked = false;

	},
	disable: function() {
		$(this.uiElement).prop("disabled", true);
	},
	enable: function() {
		var _disabled = $(this.uiElement).attr("_disabled");
		if (_disabled &&_disabled == 'true') {
			return;
		}
		$(this.uiElement).prop("disabled", false);
	}
});

org.netsharp.controls.DateBox = org.netsharp.controls.Control.Extends({
	get: function(entity) {

		var propertyValue = $(this.uiElement).datebox('getValue');
		if (propertyValue == "") {

			propertyValue == null;
		}
		
		entity[this.propertyName] = propertyValue;
	},

	set: function(entity) {

		var propertyValue = entity[this.propertyName];
		if (propertyValue == undefined || propertyValue == null) {
			propertyValue = "";
		} else if (typeof(propertyValue) == "string") {

			propertyValue = propertyValue.split(" ")[0];
		}

		$(this.uiElement).datebox('setValue', propertyValue);
		// this.uiElement.value = propertyValue;
	},

	clear: function() {
		$(this.uiElement).datebox('setValue', "");
	},
	disable: function() {
		$(this.uiElement).datebox("disable");
	},
	enable: function() {
		var _disabled = $(this.uiElement).attr("_disabled");
		if (_disabled &&_disabled == 'true') {
			return;
		}
		$(this.uiElement).datebox("enable");
	}
});

org.netsharp.controls.DateTimeBox = org.netsharp.controls.Control.Extends({

	get: function(entity) {

		var propertyValue = this.uiElement.value;
		var propertyValue = $(this.uiElement).datetimebox('getValue');
		if (propertyValue == "") {

			propertyValue == null;
		}
		entity[this.propertyName] = propertyValue;
	},

	set: function(entity) {

		var propertyValue = entity[this.propertyName];
		if (propertyValue == undefined || propertyValue == null) {
			propertyValue = "";
		} else if (typeof(propertyValue) == "string") {

			// propertyValue = propertyValue.split(" ")[0];
			// propertyValue = propertyValue;
		}

		$(this.uiElement).datetimebox('setValue', propertyValue);
		// this.uiElement.value = propertyValue;
	},

	clear: function() {

		$(this.uiElement).datetimebox('setValue', "");

	},
	disable: function() {
		$(this.uiElement).datebox("disable");
	},
	enable: function() {
		var _disabled = $(this.uiElement).attr("_disabled");
		if (_disabled &&_disabled == 'true') {
			return;
		}
		$(this.uiElement).datebox("enable");
	}
});

org.netsharp.controls.EnumBox = org.netsharp.controls.Control.Extends({

	get: function(entity) {

		var propertyValue = $(this.uiElement).combobox('getValue'); // this.uiElement.value;

		if (propertyValue = '') {
			propertyValue == null;
		}
		entity[this.propertyName] = propertyValue;
	},

	set: function(entity) {

		var propertyValue = entity[this.propertyName];
		if (propertyValue == undefined || propertyValue == null) {
			propertyValue = "";
		}

		$(this.uiElement).combobox('setValue', propertyValue);
	},

	clear: function() {

		$(this.uiElement).combobox('setValue', "");

	},
	disable: function() {
		$(this.uiElement).combobox("disable");
	},
	enable: function() {
		var _disabled = $(this.uiElement).attr("_disabled");
		if (_disabled &&_disabled == 'true') {
			return;
		}
		$(this.uiElement).combobox("enable");
	}
});

org.netsharp.controls.ReferenceBox = org.netsharp.controls.Control.Extends({

	getValue: function() {
		return $(this.uiElement).combogrid('getValue');
	},
	getText: function() {
		return $(this.uiElement).combogrid('getText');
	},

	setValue: function(val) {

		$(this.uiElement).combogrid('setValue', val);
	},

	setText: function(text) {

		$(this.uiElement).combogrid('setText', text);
	},

	get: function(entity) {

		var propertyValue = this.getValue();
		var propertyText = this.getText();
		var expression = "entity." + $(this.uiElement).attr("foreignkey") + " = propertyValue;";
		eval(expression);

		var foreignName = $(this.uiElement).attr("foreignName");
		var propertyExpression = "entity." + foreignName.split('.')[0];
		var propertyExpressionText = "entity." + foreignName;

		expression = propertyExpression + ".id = propertyValue;";
		eval(expression);
		
		var expressionText = propertyExpressionText + " = propertyText;";
		eval(expressionText);
		
		if (entity.entityState == "New") {
			return;
		}

		if (System.isnull(propertyValue)) {
			propertyValue = "null";
		}

		var expression1 = "entity." + $(this.uiElement).attr("foreignId") + "=" + propertyValue + ";";
		eval(expression1);
	},

	set: function(entity) {

		var propertyValue = null;
		var expression1 = "propertyValue = entity." + $(this.uiElement).attr("foreignkey").replace('_id', 'id') + ";";
		eval(expression1);

		var propertyText = null;

		var foreignName = $(this.uiElement).attr("foreignName");
		var propertyExpression = "entity." + foreignName.split('.')[0];
		eval("if(System.isnull(" + propertyExpression + ")){" + propertyExpression + "={};}");

		propertyExpression = propertyExpression + "." + foreignName.split('.')[1];
		eval("if(System.isnull(" + propertyExpression + ")){" + propertyExpression + "=null;}");

		eval("propertyText = " + propertyExpression + ";");

		if (System.isnull(propertyValue)) {
			propertyValue = "";
		}

		if (System.isnull(propertyText)) {
			propertyText = "";
		}

		this.setValue(propertyValue);
		this.setText(propertyText);
	},

	clear: function() {
		this.setValue("");
		this.setText("");
	},
	disable: function() {
		$(this.uiElement).combogrid("disable");
	},
	enable: function() {
		var _disabled = $(this.uiElement).attr("_disabled");
		if (_disabled &&_disabled == 'true') {
			return;
		}
		$(this.uiElement).combogrid("enable");
	}
});

org.netsharp.controls.ComboTreeBox = org.netsharp.controls.ReferenceBox.Extends({

	getValue: function() {
		return $(this.uiElement).combotree('getValue');
	},

	setValue: function(val) {
		$(this.uiElement).combotree('setValue', val);
	},

	setText: function(text) {

		$(this.uiElement).combotree('setText', text);
	},
	disable: function() {
		$(this.uiElement).combotree("disable");
	},
	enable: function() {
		var _disabled = $(this.uiElement).attr("_disabled");
		if (_disabled &&_disabled == 'true') {
			return;
		}
		$(this.uiElement).combotree("enable");
	}
});

org.netsharp.controls.JavaEnumBox = org.netsharp.controls.Control.Extends({

	get: function(entity) {

		var propertyValue = $(this.uiElement).combobox('getValue'); // this.uiElement.value;
		if (System.isnull(propertyValue)) {
			propertyValue = null;
		}else{
			
			propertyValue = parseInt(propertyValue);
		}
		entity[this.propertyName] = propertyValue;
	},

	set: function(entity) {

		var propertyValue = entity[this.propertyName];
		if (System.isnull(propertyValue)) {

			propertyValue = "";
			var defaultValue = $(this.uiElement).attr('defaultValue');
			if (!System.isnull(defaultValue)) {
				propertyValue = defaultValue;
			}
		}

		$(this.uiElement).combobox('setValue', propertyValue);
	},

	clear: function() {

		$(this.uiElement).combobox('setValue', "");

	},
	disable: function() {
		$(this.uiElement).combobox("disable");
	},
	enable: function() {
		var _disabled = $(this.uiElement).attr("_disabled");
		if (_disabled &&_disabled == 'true') {
			return;
		}
		$(this.uiElement).combobox("enable");
	}
});

org.netsharp.controls.PccBox = org.netsharp.controls.Control.Extends({
	ctor: function() {
		this.base();
		this.service = 'org.netsharp.pcc.web.ProvinceCityCountyController';
	},
	init:function(){
		
		var $ctrl = $(this.uiElement);
		var options = $ctrl.combobox('options');
		var level = options.level;
		var changeCtrlId = options.changeCtrlId;
		var me = this;
		if(changeCtrlId != undefined){

			options.onChange = function(newValue,oldValue){
				
				//if(newValue && typeof newValue === 'number'){
				if(newValue && !System.isnull(newValue.toString().trim())){
					bindData(newValue,changeCtrlId);
				}else{
					$('#' + changeCtrlId).combobox('setValue',null);
					$('#' + changeCtrlId).combobox('loadData',[]);
				}
			}
			$ctrl.combobox(options);
		}
		
		if(level==1){
			
			var data = $ctrl.combobox('getData');
			if(data==null || data.length ==0 || data.length ==1){//2018-03-28 hw 特殊处理，呕心

				bindData(null,this.propertyName);
			}
		}

		function bindData(parentId,bindCtrlId){
			
			if(parentId != null){
				
		    	var parentId = parseInt(parentId);
				if(System.isnull(parentId) || typeof parentId != 'number'){
					
					return;
				}
			}
			
			 var serviceLocator = new org.netsharp.core.JServiceLocator();
			 serviceLocator.invoke(me.service,'queryPcc', [parentId], function(data){
				 
				 if(System.isnull(data) || data.length==0){
					 return;
				 }
				 $('#' + bindCtrlId).combobox('setValue',null);
				 $('#' + bindCtrlId).combobox('loadData',data);
				 if(data.length == 1){
					 
					 $('#' + bindCtrlId).combobox('select',data[0].id);
				 }
				 
			 }, null, false, null);
		}
	},
	
	get: function(entity) {

		var $ctrl = $(this.uiElement);
		var options = $ctrl.combobox('options');
		var propertyValue = $ctrl.combobox('getValue');
		entity[options.foreignkey] = propertyValue || null;
		
		var foreign = options.foreignName.split('.')[0];
		var foreignName = options.foreignName.split('.')[1];
		
		if(entity[foreign] == null){
			
			entity[foreign] = {};
		}
		entity[foreign].id = propertyValue;
		
		var text = $ctrl.combobox('getText');
		entity[foreign][foreignName] = text;
	},

	set: function(entity) {

		var $ctrl = $(this.uiElement);
		var options = $ctrl.combobox('options');
		var propertyValue = entity[options.foreignkey];
		if (System.isnull(propertyValue)) {

			propertyValue = "";
			var defaultValue = $(this.uiElement).attr('defaultValue');
			if (!System.isnull(defaultValue)) {
				propertyValue = defaultValue;
			}
		}

		$(this.uiElement).combobox('setValue', propertyValue);
	},

	clear: function() {

		$(this.uiElement).combobox('setValue', "");

	},
	disable: function() {
		
		$(this.uiElement).combobox("disable");
	},
	enable: function() {
		
		var _disabled = $(this.uiElement).attr("_disabled");
		if (_disabled &&_disabled == 'true') {
			return;
		}
		$(this.uiElement).combobox("enable");
	}
});


org.netsharp.controls.RadioboxGroup = org.netsharp.controls.Control.Extends({
	ctor: function() {
		this.base();
	},
	set: function(entity) {
		
		var propertyValue = entity[this.propertyName];
		if (System.isnull(propertyValue)) {

			propertyValue = "";
			var defaultValue = $(this.uiElement).attr('defaultValue');
			if (!System.isnull(defaultValue)) {
				propertyValue = defaultValue;
			}
		}
		//如果值为空，则clear 
		$(this.uiElement).radiogroupbox('setValue', propertyValue);
	},
	get: function(entity) {

		var propertyValue = $(this.uiElement).radiogroupbox('getValue');
		if (System.isnull(propertyValue)) {
			propertyValue = null;
		}else{
			
			propertyValue = parseInt(propertyValue);
		}
		entity[this.propertyName] = propertyValue;
	}
});

/***************************************************************************
 * CheckBox组合
 */
org.netsharp.controls.CheckboxGroup = org.netsharp.controls.Control.Extends({
	ctor: function() {
		this.base();
	},
	set: function(entity) {
		
		var propertyValues = entity[this.propertyName];
		propertyValues = propertyValues || [];
		var $ctrl = $(this.uiElement);
		var values = [];
		var enumFieldName = $ctrl.attr('enumFieldName');
		var foreignKey = $ctrl.attr('foreignKey');
		for(var i=0;i<propertyValues.length;i++){
			
			var item = propertyValues[i];
			var value = item[enumFieldName];
			values.push(value);
		}
		
		//如果值为空，则clear
		$ctrl.checkboxgroup('clear');
		$ctrl.checkboxgroup('setValues', values);
	},
	get: function(entity) {//循环太多，需重构 hw
		
		var oldPropertyValues = entity[this.propertyName];
		oldPropertyValues = oldPropertyValues || [];

		var $ctrl = $(this.uiElement);
		var enumFieldName = $ctrl.attr('enumFieldName');
		var foreignKey = $ctrl.attr('foreignKey');
		var values = $ctrl.checkboxgroup('getValues');
		var newPropertyValues = [];
		for(var i=0;i<values.length;i++){
			
			var value = values[i];
			var subItem = {};
			subItem[foreignKey] = entity.id;
			subItem[enumFieldName] = value;
			newPropertyValues.push(subItem);
		}
		
		var tempValues = [];
		
		//处理旧数据
		for(var i=oldPropertyValues.length-1; i>=0; i--){
			
			var oldItem = oldPropertyValues[i];
			if(!isOld(newPropertyValues,oldItem)){

				//将不存在的旧数据设置为“删除”状态
				oldItem['entityState'] = 'Deleted';
			}else{
				oldPropertyValues.splice(i,1);
			}
			tempValues.push(oldItem);
		}
		
		//处理新数据
		for(var i=0;i<newPropertyValues.length;i++){
			
			var newItem = newPropertyValues[i];
			if(isNew(tempValues,newItem)){

				//将存在的旧数据保留
				newItem['entityState'] = 'New';
				tempValues.push(newItem);
			}
		}
		
		entity[this.propertyName] = tempValues;
		
		//判断是否为旧值
		function isOld(newItems,oldItem){
			
			for(var i=0;i<newItems.length;i++){
				
				var newItem = newItems[i];
				if(oldItem[enumFieldName] != null && oldItem[enumFieldName].toString() == newItem[enumFieldName].toString()){
					
					return true;
				}
			}
			
			return false;
		}
		
		//判断是否为新值
		function isNew(oldItems,newItem){
			
			for(var i=0;i<oldItems.length;i++){
				
				var oldItem = oldItems[i];
				if(oldItem[enumFieldName] != null && oldItem[enumFieldName].toString() == newItem[enumFieldName].toString()){
					
					return false;
				}
			}
			
			return true;
		}
	}
});

/***
 * 公司宝扩展
 * ****/
org.netsharp.controls.OrganizationComboBox = org.netsharp.controls.Control.Extends({

	get: function(entity) {

		var propertyValue = $(this.uiElement).combobox('getValue'); // this.uiElement.value;
		if (System.isnull(propertyValue)) {
			propertyValue = null;
		}else{
			
			propertyValue = parseInt(propertyValue);
		}
		entity[this.propertyName] = propertyValue;
	},

	set: function(entity) {

		var propertyValue = entity[this.propertyName];
		if (System.isnull(propertyValue)||propertyValue == 0) {

			propertyValue = "";
			var defaultValue = $(this.uiElement).attr('defaultValue');
			if (!System.isnull(defaultValue)) {
				propertyValue = defaultValue;
			}
		}

		$(this.uiElement).combobox('setValue', propertyValue);
	},

	clear: function() {

		$(this.uiElement).combobox('setValue', "");

	},
	disable: function() {
		$(this.uiElement).combobox("disable");
	},
	enable: function() {
		var _disabled = $(this.uiElement).attr("_disabled");
		if (_disabled &&_disabled == 'true') {
			return;
		}
		$(this.uiElement).combobox("enable");
	}
});


System.Declare("com.gongsibao.controls");
com.gongsibao.controls.CityComboBox = org.netsharp.controls.PccBox.Extends({
	ctor: function() {
		this.base();
		this.service = 'com.gongsibao.controls.CityComboBoxController';
	}
});

