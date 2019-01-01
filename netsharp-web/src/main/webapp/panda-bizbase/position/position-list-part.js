System.Declare("org.netsharp.organization.controller.position");


org.netsharp.organization.controller.position.OperationPositionPart = org.netsharp.panda.commerce.ListPart.Extends({

	doAuthorization:function(){
		var sel=this.getSelectedItem();
		if (sel != null) {
			var url = "/panda/position/permission/form?id=" + sel.id;
			IMessageBox.open("职务授权", url,1000,550, function(){
			});
		}
	},
	
});
