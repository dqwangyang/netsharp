System.Declare("org.netsharp.scrum.web");

org.netsharp.scrum.web.RoadmapListPart = org.netsharp.panda.commerce.ListPart.Extends({
	
	doubleClickRow : function(index, obj) {

		var item = this.getSelectedItem();
		
		var url = "/nav/addins/scrum/roadmap?id="+item.id;
		window.open(url);
	}
});
