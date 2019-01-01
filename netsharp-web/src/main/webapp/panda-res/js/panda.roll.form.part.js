
//翻页表单类
//需要与相应的RollgridPart配合使用
//需要注意表单的打开方式是window.open
//todo 处理弹出层的表单
org.netsharp.panda.commerce.RollPageFormPart = org.netsharp.panda.commerce.FormPart.Extends({
	 ctor: function () {
	        this.base();
	       
	    },
	    onload:function(){
	    	 this.listController=window.opener.controller;
	    	 var id=null;
	    	 if(this.listController.getCurrentId()){
	    		 id=this.listController.getCurrentId();
	    	 }else{
	    		 id = this.queryString("id");
	    	 }
	         if (System.isnull(id)) {

	             this.add();
	         }
	         else {
	             this.byId(id);         
	         }
	     	 //$("body").focus();
	    },
	    prev:function(){
	    	var pid=this.listController.getPrevId();
	    	if(pid){
	    		this.onload();
	    	}
	    },
	    next:function(){
	    	var pid=this.listController.getNextId();
	    	if(pid){
	    		this.onload();
	    	}
	    }
});

org.netsharp.panda.commerce.ReferenceFormPart = org.netsharp.panda.commerce.FormPart.Extends({

    setRelationItem: function (relationItem) {

        this.relationItem = relationItem;

        var role = this.context.Role;
        currentItem = this.relationItem[role];

        this.setCurrentItem(currentItem);

        this.databind();
    }
});

org.netsharp.panda.commerce.VoucherFormPart = org.netsharp.panda.commerce.FormPart.Extends({

    ctor: function () {
        this.base();
        this.voucherState = {audit:"audit",unaudit:"unaudit"};
    },
    marked:function(text){
    	
    	var html="<span class='WaterMarked'>"+text+"</span>";
    	$("#"+this.context.formName).append(html);
    },
    databindafter:function(){
    	
    	try{
		     var state = $("#voucherState").combobox("getValue");    
		     if(state == this.voucherState.audit){
		    	
		    	 this.marked("已审"); 
		    	 this.viewModel.disable();
		    	 this.setState();
		     }else{
		    	 
		    	 $(".WaterMarked").remove();
		    	 this.viewModel.enable();
		     }
	     }catch(e){
	    	 
	     }
    },
    getauditState:function(){

        if (this.viewModel.currentItem == null) {

            return UiElementState.Disable;
        }
        
        if (this.viewModel.currentItem.entityState == EntityState.New) {

            return UiElementState.Disable;
        }
        
        if (this.viewModel.currentItem.voucherState == this.voucherState.audit) {

            return UiElementState.Disable;
        }

        return UiElementState.Enable;
    },
    getunauditState:function(){

        if (this.viewModel.currentItem == null) {

            return UiElementState.Disable;
        }
        
        if (this.viewModel.currentItem.entityState == EntityState.New) {

            return UiElementState.Disable;
        }
        
        if (this.viewModel.currentItem.voucherState == this.voucherState.unaudit) {

            return UiElementState.Disable;
        }

        return UiElementState.Enable;
    },
    getaddState:function(){

        return UiElementState.Enable;
    },
    getsaveState:function(){
    	
        if (this.viewModel.currentItem == null) {

            return UiElementState.Enable;
        }
        
        if (this.viewModel.currentItem.entityState == EntityState.New) {

            return UiElementState.Enable;
        }
        
        if (this.viewModel.currentItem.voucherState == this.voucherState.audit) {

            return UiElementState.Disable;
        }
        return UiElementState.Enable;
    }
});

