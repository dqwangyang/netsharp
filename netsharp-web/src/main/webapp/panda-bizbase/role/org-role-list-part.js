System.Declare("org.netsharp.organization.controller");

org.netsharp.organization.controller.RoleListPart = org.netsharp.panda.commerce.ListPart.Extends({

	accredit : function () {

		var row=this.getSelectedItem();
		if (row != null) {
			var url = "/panda/role/permission/form?id=" + row.id;
			IMessageBox.open("角色授权", url,1000,550, function(){});
		}
    }
});