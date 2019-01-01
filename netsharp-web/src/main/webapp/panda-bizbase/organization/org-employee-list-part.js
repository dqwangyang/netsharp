System.Declare("org.netsharp.organization.controller");

org.netsharp.organization.controller.EmployeeListPart = org.netsharp.panda.commerce.ListPart.Extends({

    resetpwd : function () {

        var ids = this.getSelectionIds();
        var pars = new Array();
        pars.push(ids);

        if (ids == null || ids == '') {

            IMessageBox.info("没有选中员工");

        } else {

            var me = this;

            IMessageBox.confirm("确认要重置密码吗？", function (istrue) {
                
                if (istrue) {
                    me.invokeService("resetPassword", pars, function (data) {
                        if (data==true) {
                            IMessageBox.info("重置成功");
                        } else {
                             IMessageBox.error("重置失败");
                        }
                    });
                }
            });
        }
    },
    permissions:function(){
    	
    	var me = this;
        var rows=   $('#datagridemployeeList').datagrid("getSelections");
        if(rows.length>1||rows.length==0){
        	IMessageBox.info("只能选择一行记录!");
        	return;
        }
    	 
        me.invokeService("permissions", [rows[0].id], function (data) {
        	
        	if(data.length == 0){
        		
        		IMessageBox.info("此用户没有任何权限!");
        		return;
        	}
        	data.sort();
		    var list=[];       		  	
   		  	for(var i=0;i<data.length;i++){
   		  		
   		  	   var arr = [];
   		       arr = data[i].split("：");       		 
   		  	   var ent={};
   		  	   ent.code=arr[0];
   		  	   ent.name=arr[1];
   		  	   list.push(ent);
   		  	}
       		  	
        	if($("#pandaWindow").length ==0){

    			$(document.body).append("<div id='pandaWindow'></div>");
        	}
   		  	$('#pandaWindow').window({
   		        title: "权限列表",
   		        width:800 ,
   		        height: 500 ,       
   		        modal: true,
   		        maximized: false,
   		        minimizable: false,
   		        maximizable: false,
   		        shadow: true,
   		        cache: false,
   		        closed: false,
   		        collapsible: false,
   		        resizable: true,
   		        inline: true,
   		        content:"<table id='permissionsdg'></table>"
   		    });
   		  
   		 $("#permissionsdg").datagrid({
				width:785,   
				height:462, 
				singleSelect:true,
				nowrap:false,
				columns:[[   
                          {field:'code',title:'菜单',width:220},
				          {field:'name',title:'操作权限',width:500}	          
				         ]] 		
		});

		$("#permissionsdg").datagrid("loadData",list);
			
    });    
  }
});

org.netsharp.organization.controller.ClassificationEmployeeListPart= org.netsharp.organization.controller.EmployeeListPart.Extends({
	
});