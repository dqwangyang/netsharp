System.Declare("org.netsharp.core");

var LoginResult = {};

LoginResult.invalid=0;//登录无效
LoginResult.ok=1;//登录成功
LoginResult.disabled=2;//员工停用
LoginResult.defaultPwd=3;//登录成功,但是默认密码

org.netsharp.core.LoginController = System.Object.Extends({
	ctor : function() {
		this.jServiceLocator = null;
		this.service = "org.netsharp.organization.controller.LoginController";
	},
	invoke : function(month, pars, callback) {
		if (this.jServiceLocator == null) {
			this.jServiceLocator = new org.netsharp.core.JServiceLocator();
		}
		this.jServiceLocator.invoke(this.service, month, pars, callback);
	},
	init : function() {
		this.setShortcutKey();
		this.setCookies();
	},
	setCookies : function() {
		var c = System.Cookies.get("netsharp_login");
		if (c != null) {
			var j = null;
			eval("j=" + c);
			if (j.username) {
				$("#username").val(j.username);
			}
			if (j.password) {
				$("#password").val(j.password);
			}
			$("#remember").prop("checked", true);
		}
	},

	setShortcutKey : function() {
		var me = this;
		$("#username,#password,#remember").keydown(function(e) {
			if (e.keyCode == 13) {
				me.login();
			}
		});
	},

	login : function() {
		
		var username = $("#username").val();
		var password = $("#password").val();
		if (username == "") {
			
			IMessageBox.error("账号不能为空！", function(){
				$("#username").focus();
			});
			return;
		}
		
		if (password == "") {
			IMessageBox.error("密码不能为空！", function(){
				$("#password").focus();
			});
			return;
		}
		
		var mpassword = $.md5(password + "user!@#123").substring(8,24);
		var pars = [];
		pars.push(username);
		pars.push(mpassword);
		var me=this;
		this.invoke("login", pars, function(message) {
			
			if (message.result == LoginResult.ok || message.result == LoginResult.defaultPwd) {
				
				System.Cookies.set("SESSION_TICKET_KEY",message.data,500);
				
				var isRemember = $("#remember").prop('checked');
				if (isRemember == true) {
					var c = "{username:'" + username + "',password:'" + password + "'}";
					System.Cookies.set("netsharp_login", c, 100);
				}
				
				var workbenchList = message.workbenchList;
				if(workbenchList.length == 1){
					window.location.href = workbenchList[0].path;
				}else{
				    var content = '';
				    $(workbenchList).each(function(index,item){
				    	content += '<div class="workbench-item"><a href="'+item.path+'">'+(index+1)+'. '+item.name+'</a></div>';
				    });
				    
				    var offsetLeft = ($('body').width() - 410)+'px';
				    var offsetTop = (($('body').height() - 150)/2)+'px';
				    layer.open({
				        type: 1,
				        closeBtn:0,
				        anim: 2,
				        offset: [offsetTop,offsetLeft],
				        title:'请选择工作台',
				        area: ['350px', '150px'],
				        content: content
				      });
				}
			} else if (message.result == LoginResult.disabled) {
				
				IMessageBox.error("您的帐号已经停用，请联系管理员！");
				
			} else {
				
				IMessageBox.error("您的用户名或密码错误！");
			}
		});
	}
});