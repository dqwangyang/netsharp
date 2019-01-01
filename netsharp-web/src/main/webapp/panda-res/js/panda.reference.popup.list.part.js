org.netsharp.panda.commerce.ReferencePopupListPart = org.netsharp.panda.commerce.ListPart.Extends({
	
	select : function() {
		
		var selectedItem = this.getSelectedItem();
		if(selectedItem == null){
			
			return;
		}
		var controlId = "#"+this.queryString("controlId");
		var parentReferencebox = window.parent.$(controlId);
		parentReferencebox.referencebox("setObject",selectedItem);
        window.parent.$.data(parentReferencebox[0], "combo").window.dialog("close");
	},
	doubleClickRow : function(index, obj) {

		this.select();

	}
});