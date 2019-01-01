System.Declare("org.netsharp.scrum.web");

org.netsharp.scrum.web.StoryListPart = org.netsharp.panda.commerce.ListPart.Extends({
	
	traces : function(){
		
		var item = this.getSelectedItem();
		if(item == null ){
			return;
		}
		
		var url = "/panda/scrum/my/trace/list?storyId="+item.id;
		window.open(url);
	},
	
	addtrace : function(){
		
		var item = this.getSelectedItem();
		if(item == null ){
			return;
		}
		
		var me = this;
		var url = "/panda/scrum/my/trace/form?storyId="+item.id;
		IMessageBox.open("任务跟进", url, 1050, 500, function() {
			
			me.reload();
		});
	},
});
