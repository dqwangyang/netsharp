System.Declare("org.netsharp.job.controller");

org.netsharp.job.controller.JobListPart = org.netsharp.panda.commerce.ListPart.Extends({

	createCron : function() {
		
		var url = "/panda-platform/job/quartcron/cron.html";
		IMessageBox.open("生成Cron表达式", url, 900, 600, function() {

		});
	},
	start: function() {
		
		this.invokeMethod("start");		
	},
	resume : function() {

		this.invokeMethod("resume");
	},
	pause : function() {
		
		this.invokeMethod("pause");
	},
	run: function() {
		
		this.invokeMethod("runNow");
	},
	invokeMethod : function(method) {
		
		var count = this.getSelectionCount();
		if (count <= 0) {

			IMessageBox.warning("请选择需要操作的记录！");
			return;
		}
		var item = this.getSelectedItem();
		var me=this;
		this.invokeService(method, [item.id], function(jmessage) {
			if (jmessage) {
				IMessageBox.warning("操作成功！");
				me.reload();
			} else {
				IMessageBox.warning("操作失败,请重试！");
			}
		});
	}
});
