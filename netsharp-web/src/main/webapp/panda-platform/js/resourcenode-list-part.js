System.Declare("org.netsharp.web");

org.netsharp.web.ResourceNodeTreegridPart = org.netsharp.panda.commerce.TreegridPart.Extends({
	
    sql : function(){
        var item = this.getSelectedItem();
        if(item==null){
        	alert("请选择资源节点记录");
        }
    	
    	var pars = [];
    	pars.push("id="+item.id);
    	pars.push("type=resourcenode");
    	var url = "/nav/panda-platform/sqlGenerator?" + pars.join("&") ;
		window.open(url);
    },
 // -----------------------
	// 新增
	// -----------------------
	add : function() {

		if (!this.onAdding()) {
			return;
		}

		var fks = [];
      //wangyand 2015年10月9日 14:34:04注释 
		//if (this.context.relationRole != null && this.relationItem!=null&& this.context.relationRole != "parentId") {
	    if (this.context.relationRole != null && this.relationItem!=null) {
			fks.push(this.context.relationRole + ":" + this.relationItem.id);
		}

		var parentId =  this.getSelectedItem().id;
		if (parentId != null && parentId != "") {
			fks.push("parentId:" + parentId);
		}

		this.doAdd("fk=" + fks.join(";"));
	},
});
