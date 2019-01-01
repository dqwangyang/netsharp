
org.netsharp.panda.commerce.EmployeeFormPart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
    },
    validate: function () {

        var isValidate = $("#" + this.context.formName).form('validate');
        if(isValidate){
        	
        	if($("#datagridorganizations").length>0){

            	isValidate = $("#datagridorganizations").datagrid("getRows").length > 0;
            	if(!isValidate){
            		
            		IMessageBox.error("必须指定一个岗位！");
            	}
        	}
        }
        return isValidate;
    }
});

