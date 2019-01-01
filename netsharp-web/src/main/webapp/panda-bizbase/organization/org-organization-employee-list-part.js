System.Declare("org.OrganizationEmployeeList.controller");


org.OrganizationEmployeeList.controller.ListPart = org.netsharp.panda.commerce.ListPart.Extends({

		doubleClickRow : function(index, row) {
			var url = "/panda/system/employee/form?id="+row.employeeId;
			window.open(url);
		}
});
