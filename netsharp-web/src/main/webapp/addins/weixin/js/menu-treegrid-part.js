System.Declare("org.netsharp.wx.pa.web");

org.netsharp.wx.pa.web.MenuItemTreegridPart = org.netsharp.panda.commerce.TreegridPart.Extends({
	
	generate : function(){
		
		var publicAccountId = $('#publicAccount_name').combogrid('getValue');
		if(System.isnull(publicAccountId)){
			
			IMessageBox.info("请选择公众号!");
			return;
		}
		var me = this;
		this.invokeService("generate", [publicAccountId], function (jmessage) {
        	
			IMessageBox.toast('生成成功！');
       });
	}
});
