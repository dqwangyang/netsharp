/// <reference path="system.js"/>

System.Declare("org.netsharp.core");

var EntityState =
    {
        Transient: "Transient",
        New: "New",
        Persist: "Persist",
        Deleted: "Deleted"
    };

var UiElementState =
    {
        Empty: 1,
        Hide: 2,
        Show: 3,
        Disable: 4,
        Enable: 5
    };

var ResultType =
    {
        info: "info",
        warn: "warn",
        error: "error",
        loginTimeout: "loginTimeout"
    };

var OpenType =
    {
        redirect: 0,
        window: 1,
        open: 2,
        tabs: 3
    };

var AggregateType =
    {
        None: "None",
        Count: "Count",
        Sum: "Sum",
        Min: "Min",
        Max: "Max",
        Average: "Average"
    };


//-------------------------------------------------------------------------------------------------------------------------------

org.netsharp.core.JServiceLocator = System.Object.Extends({

    ctor: function () {
        this.url = System.Url.getUrl("/panda/rest/service");
    },

    invoke: function (service, methodName, pars, callback, vid, isAsyn, errorCallback) {

        if (System.isnull(vid)) {
            vid = null;
        }

        var message = {
            service: service,
            methodName: methodName,
            vid: vid,
            parameters: []
        };

        if (pars) {
            for (var i = 0; i < pars.length; i++) {

                var j = JSON.stringify(pars[i]);
                var jpa = { value: encodeURIComponent(j) };
                message.parameters.push(jpa);
            }
        }

        this.doInvoke(this.url, message, callback, isAsyn, errorCallback);
    },
    doInvoke: function (url, message, callback, isAsyn, errorCallback) {

        if (isAsyn == undefined || isAsyn == null) {
            isAsyn = true;
        }
        var me = this;
        var jstr = JSON.stringify(message);
        $.ajax({
            url: url,
            type: "Post",
            dataType: "text",
            contentType: "application/json;charset=utf-8",
            data: jstr,
            async: isAsyn,
            success: function (json) {

                var result = null;
                try {
                    result = order = JSON.parse(json);
                }
                catch (error2) {
                    IMessageBox.error("系统异常");
                    return;
                }

                if (result.type == ResultType.info) {

                    if (callback == undefined || callback == null) {
                        return;
                    }
                    callback(result.data);
                }
                else if (result.type == ResultType.warn) {

                    if (errorCallback == undefined || errorCallback == null) {
                        IMessageBox.warning(result.message);
                    } else {
                        IMessageBox.warning(result.message, errorCallback);
                    }
                }
                else if (result.type == ResultType.error) {

                    if (errorCallback == undefined || errorCallback == null) {
                        IMessageBox.error(result.message);
                    } else {
                        IMessageBox.error(result.message, errorCallback);
                    }
                } else if (result.type == ResultType.loginTimeout) {
                	window.top.location.href='/nav/panda-bizbase/authorization/login';
                }
                if ($.messager) {
                    $.messager.progress('close');
                }

                IMessageBox.loading.hide();
            },
            error: function (p1, p2, p3) {
                try {

                    if (p1.responseText != "") {

                    	IMessageBox.info(p1.responseText);
                    }
                    return;
                }
                catch (error) {
                	IMessageBox.info(data);
                }
            }
        });
    }
});

//-------------------------------------------------------------------------------------------------------------------------------
var IMessageBox =
    {
        info: function (message, callback) {

        	window.top.layer.alert(message,{title:'提示'},function (index, layero) {
                
                if (callback) {

                    callback();
                }
                window.top.layer.close(index);
            });
        },

        warning: function (message, callback) {

            window.top.layer.alert(message,{title:'警告',icon: 0},function (index, layero) {
                
                if (callback) {

                    callback();
                }
                window.top.layer.close(index);
            });
        },

        error: function (message, callback) {

        	window.top.layer.alert(message,{title:'错误',icon: 2},function (index, layero) {
                
                if (callback) {

                    callback();
                }
                window.top.layer.close(index);
            });
        },

        confirm: function (message, callback) {

        	window.top.layer.confirm(message,{title:'确认',icon: 3}, function (index, layero) {
                
        		window.top.layer.close(index);
            	callback(true);
            }, function () {
            	callback(false);
            });
        },
        toast:function(message,icon,callback){

        	var _icon = icon || 1;
        	window.top.layer.msg(message, {time: 3000, icon:_icon},function(){
        		
        		if(callback){
        			
        			callback();
        		}
        	});
        },
        loading:{
        	
        	show:function(){
        		
        		layer.load(1,{shade: [0.3, '#000']});
        	},
        	hide:function(){
        		
        		layer.closeAll('loading');
        	}        	
        },
        prompt: function (title, message, callback) {

        	layer.prompt({title: title, formType: 0}, function(pass, index){
              if (callback && pass) {
                  callback(pass,index);
              }
    		  //layer.close(index);
    		});
        },
        open: function (header, url, width, height, callback) {

        	width = width+'px';
        	height = height+'px';
        	window.top.layer.open({
        		  type: 2,
        		  title: header,
        		  fixed: false,
        		  maxmin: true,
        		  shadeClose:true,
        		  area: [width, height],
        		  content: url,
        		  cancel: function(){ 
        			  
                    if (callback) {
  
                          callback();
                      }
    			  }
        	});
        },
        area:function(value){
        	
        	layer.prompt({
      		  formType: 2,
      		  value: value,
      		  area: ['800px', '350px']
      		}, function(value, index, elem){
    		  layer.close(index);
      		});
        },
        closeWindow: function () {
            $('#pandaWindow').window("close");
        }
    };

var PandaHelper = {};

PandaHelper.ShowLogin = function () {

//	layer.prompt({
//	  formType: 1,
//	  title: '请输入密码',
//	  area: ['800px', '350px'] //自定义文本域宽高
//	}, function(value, index, elem){
//	  alert(value); //得到value
//	  layer.close(index);
//});

    var content = '<br/><p style="padding-left:50px;">&nbsp;帐号：<input id="loginName" type="text" class="easyui-validatebox nsInput" required="true" style="width:180px;"></input></p>'
        + '<p style="padding-left:50px;">&nbsp;密码：<input id="loginPassword" type="password" class="easyui-validatebox nsInput" required="true" style="width:180px;"></input></p>';

	layer.open({
		  type: 1,
		  title: '登录',
		  fixed: false,
		  maxmin: false,
		  shadeClose:true,
		  area: ['350px', '230px'],
		  content: content,
		  btn: ['登录', '取消'],
		  yes:function(index, layero){
			  
              var loginName = $("#loginName").val();
              var loginPassword = $("#loginPassword").val();
              if (System.isnull(loginName)) {

                  $("#loginName").focus();
                  return;
              }
              if (System.isnull(loginPassword)) {

                  $("#loginPassword").focus();
                  return;
              }
              var pars = [];
              pars.push(loginName);

              try {

                  pars.push($.md5(loginPassword + "user!@#123").substring(8,24));

              } catch (e) {
                  document.write("<script language=javascript src='/package/jquery/jquery.md5.js'></script>");
                  pars.push($.md5(loginPassword + "user!@#123").substring(8,24));
              }
              var jServiceLocator = new org.netsharp.core.JServiceLocator();
              jServiceLocator.invoke("org.netsharp.organization.controller.LoginController", "login", pars, function (message) {
            	  
                  if (message.result == 1) {
                	  
                	  	IMessageBox.toast('登录成功');
                	  	layer.closeAll();
                  } else if (message.result == 2) {
                	  
                  		IMessageBox.info("已经停用的用户不能登录!");
                  } else {
                	  
                  		IMessageBox.info("您的用户名或密码错误!");
                  }
              });
		  }
	});

}

//弹出支柱表单窗口
PandaHelper.openDynamicForm = function(option){

	var items = option.items;
	if(items==null || items.length==0){
		
		IMessageBox.error('items不能为空');
		return;
	}
	var formId = System.GUID.newGUID();
	var builder = new System.StringBuilder();
	
	builder.append('<form id="dynamicForm">');
	builder.append('<div style="margin:10px;">');
	builder.append('<table cellpadding="5" cellspacing="10" class="form-panel">');
	if(!System.isnull(option.explain)){
		
		builder.append('<tr><td class="title"></td><td>'+option.explain+'</td></tr>');
	}
	$(items).each(function(i,item){

		if(item.type == 'textarea'){

			builder.append('<tr><td class="title">'+item.title+'</td><td><textarea id="'+item.id+'" style="width:'
					+item.width+'px;height:'+item.height+'px;" class="'+(item.className||'')+'" ></textarea></td></tr>');
		}else{

			builder.append('<tr><td class="title">'+item.title+'</td><td><input id="'+item.id+'" class="'+(item.className||'')+'" style="'+(item.style||"")+'"/></td></tr>');
		}
	});
	if(!System.isnull(option.notice)){
		
		builder.append('<tr><td class="title"></td><td style="color:red;">'+option.notice+'</td></tr>');
	}
	builder.append('	</table>');
	builder.append('</div>');
	builder.append('</form>');

	layer.open({
		type : 1,
		title : option.title,
		fixed : false,
		maxmin : false,
		shadeClose : true,
		zIndex : 100000,
		area : [ option.width+'px', option.height+'px' ],
		content : builder.toString(),
		btn : [ '提交', '取消' ],
		success : function(layero, index) {
			
			$(items).each(function(i,item){

				if(item.type != 'textarea'){
					
					var expression = '$("#'+item.id+'").'+item.type+'(item.option);';
					eval(expression);
				}else{
					
					var expression = '$("#'+item.id+'").validatebox(item.option);';
					eval(expression);
					
				}
			});
			
			if(option.success){
				
				option.success(layero, index);
			}
		},
		btn1 : function(index, layero) {
			
			if(option.callback){
				
				var obj = {};//获取表单上的值（暂时不实现）
				option.callback(index, layero);
			}
		}
	});
}

//模糊字符串：用*代替后面一半
PandaHelper.dimString = function(str){
	
	var totalLength = str.length;//总长度
	var halfLength = parseInt(totalLength/2);//一半长度
	var surplusLength = totalLength-halfLength;//剩余长度
	var asteriskStr ='';
	for(var i=0;i<surplusLength;i++){
		asteriskStr+='*';
	}
	var value = str.substring(0,halfLength-1)+asteriskStr;
	return value;
}

//-------------------------------------------------------------------------------------------------------------------------------
//处理枚举
PandaHelper.Enum = {
		
	get:function(enumName){
		
		var str = sessionStorage[enumName];
		if(str){
			return JSON.parse(str);
		}
		this.invoke(enumName,function(data){
			
			obj = JSON.parse(data);
			sessionStorage[enumName] = data;
		});
	    return obj;
	},
	invoke:function(enumName,callback){
        var serviceLocator = new org.netsharp.core.JServiceLocator();
        serviceLocator.invoke("org.netsharp.panda.utils.enums.EnumController", "getEnumItems", [enumName], function(data){
        	
        	callback(data);
        }, null, false);
	}
};
//-------------------------------------------------------------------------------------------------------------------------------
var LODOP;//用于Lodop打印控件
