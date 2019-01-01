System.Declare("org.netsharp.web");
org.netsharp.web.ModifyPasswordFormPart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
    },
    save: function () {
    	
        var isValidated = this.validate();
        if (!isValidated) {
            return;
        }
        
        var me = this;
        var salt = "user!@#123";
        var originalPassword = $.md5($('#originalPassword').val() + salt).substring(8,24);
        var newPassword = $.md5($('#newPassword').val() + salt).substring(8,24);
        var confirmPassword = $.md5($('#confirmPassword').val() + salt).substring(8,24);
        this.invokeService("save", [originalPassword,newPassword,confirmPassword], function (jmessage) {
        	
        	IMessageBox.info("修改成功,请重新登录！",function(){
        		
        		window.top.location.href="/nav/panda-bizbase/authorization/login";
        	});
       });
    }
});
