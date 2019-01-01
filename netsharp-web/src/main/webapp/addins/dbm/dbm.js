System.Declare("org.netsharp.dbm.web");

org.netsharp.dbm.web.DbmController = System.Object.Extends({
	 ctor: function () {
		this.base(); 
	 },
	intitailize : function(){
		
		var bodyHeight = $("body").height();
		var txtHeight = $("#txt").height();
		
		txtHeight = txtHeight + 50;
		
		var datagridHeight = bodyHeight - txtHeight - 50;
		
		$("#datagrid").datagrid("resize",{height:datagridHeight});
		$("#txtMessage").height(datagridHeight);
	},
	
	executeSql : function(){
		
		var cmdText= $("#txt").val();
		if(System.isnull(cmdText)){
			return;
		}
		
		var me = this;
		
		var service = "org.netsharp.dbm.web.DbmController";
		var method = "execute";
		
		var serviceLoacator = new org.netsharp.core.JServiceLocator();
		serviceLoacator.invoke(service,method,[cmdText],me.executed,null,true);
	},
	
    executed : function(data){
    	
    	if(data.type=="info"){
    		
    		var data =data.data;
    		
    		if(System.isnull(data.columns)){
    			$("#txtMessage").val("影响的记录数:"+data.count);
    		}
    		else{
    			$("#txtMessage").val("");
    		}

    		var options = $("#datagrid").datagrid("options");
    		{
    			options.columns = [data.columns];
        		options.data = data.items;
    		}
    		
    		$("#datagrid").datagrid(options);
    	}
    	else if(data.type=="error"){
    		
    		alert(data.message);
    		
    		$("#txtMessage").val(data.message);
    	}
    	else{
    		window.alert("位置类型:"+data.type);
    	}
    }
});

var dbm = new org.netsharp.dbm.web.DbmController();

$(function(){
	dbm.intitailize();
})
