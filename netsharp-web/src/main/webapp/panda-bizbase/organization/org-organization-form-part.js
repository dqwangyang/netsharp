
System.Declare("org.netsharp.organization.controller.organization");
org.netsharp.organization.controller.organization.OrganizationFormPart = org.netsharp.panda.commerce.FormPart.Extends( {

	disabled:function(){
		
        var id = this.viewModel.currentItem.id.toString();
        if(!id){
        	return;
        }
        var me = this;
        var disabledCallback = function (isConfirmed) {

            if (!isConfirmed) {
                return;
            }

            me.invokeService("disabled", [id], function (jmessage) {

                if (jmessage) {

                	me.byId(id);
                	IMessageBox.toast("停用成功！");
                }else {

                    IMessageBox.warning("停用失败！");
                }
            });
        };

        IMessageBox.confirm("停用后所有子机构都得停用，确定要执行此操作吗？", disabledCallback);
	}
});

