System.Declare("org.netsharp.web");

org.netsharp.web.NLogListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
	}
});

$(function() {

	var creatorId = controllernLogList.queryString('creatorId');
	var url = controllernLogList.context.queryUrl;
	if(System.isnull(creatorId)){
	
		controllernLogList.resetUrl(url,[]);
	}else{

	    var qp = new org.netsharp.core.FilterParameter();
	    qp.key = "creatorId";
	    qp.value1 = creatorId;
	    qp.intelligentMode1 = org.netsharp.core.intelligentMode.EQUALS;
		controllernLogList.resetUrl(url,[qp]);
	}
});
