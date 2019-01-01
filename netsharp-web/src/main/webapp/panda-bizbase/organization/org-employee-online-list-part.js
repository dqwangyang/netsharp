System.Declare("org.netsharp.organization.controller");


org.netsharp.organization.controller.EmployeeOnlineListPart = org.netsharp.panda.commerce.ListPart.Extends({

		query : function() {
			
			controlleremployeeOnLineList.invokeService("query", [], function(data) {				
				$("#datagridemployeeList").datagrid("loadData", data);
			});
			
		},
});