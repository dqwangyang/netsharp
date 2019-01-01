System.Declare("org.netsharp.scrum.web");

org.netsharp.scrum.web.StoryTraceListPart = org.netsharp.panda.commerce.ListPart.Extends({
	
	ctor : function() { 
		this.base();
	}
});

$(function() {
	
	var storyId = controllerstoryTraceList.queryString("storyId");
	if(!System.isnull(storyId)){

		$('#story_name').combogrid('setValue',storyId);
		$('#story_name').combogrid('disable');
		
		controllerstoryTraceList.query();
	}
});