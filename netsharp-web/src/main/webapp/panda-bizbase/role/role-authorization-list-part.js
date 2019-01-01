/// <reference path="../../../resources/js/system.js" />
/// <reference path="../../../resources/js/core.js" />

$(function () {
	
	$("#datagridPermission").treegrid({onLoadSuccess:function(row, data){
		
		   $("#datagridPermission").treegrid("collapseAll");
	}});
	
//   $("#datagridPermission").treegrid({onClickRow:function(row){
//	   
//	    var childrens = $("#datagridPermission").treegrid("getChildren",row.id);
//	   	if(childrens.length > 0){
//	   		
//	   		$("#datagridPermission").treegrid("toggle",row.id);
//	   	}
//   
//   }});

});

System.Declare("org.netsharp.organization.controller");

var UiOperationType =
{
    add    : 1,
    remove : 0
};

var OrganizationType = {
	System      : "System",//子公司
	Corporation : "Corporation", //集团
	Department  : "Department", //部门
	Catetory    : "Catetory",//分类
	Post        : "Post", //岗位
	Role        : "Role" //角色
};


var OperationTypeColumn = {
	No : "fa fa-close",
	Yes : "fa fa-check"
};

//------------------------------------------------------------------------------------------------------------------------------------------
// 授权管理
//------------------------------------------------------------------------------------------------------------------------------------------
org.netsharp.organization.controller.RoleAuthorizationListPart = org.netsharp.panda.commerce.TreegridPart.Extends({

    //授权主体
    authorizationPrincipal: null,
    authorizationPrincipalId: null,


    
    onload:function(){
    	var me=this;
    	var height = $('body').height();
    	$("#datagridPermission").treegrid({
    		url:"/panda/rest/service?vid="+me.context.vid+"&method=query",    		
    		idField:'id',
    		treeField:'name',
    		animate:true,
    		nowrap:false,
    		height:height,
    		selectOnCheck:false,
    		checkOnSelect:false,
    		remoteSort:false,
    		striped:false,
    		showFooter:false,
    		onLoadSuccess:function(row, data){
    			me.initData();
    			$.each(data, function (i, val) { $('#datagridPermission').treegrid('collapseAll', data[i].id)})
    		}
    	});
    	
    },

    initData: function () {
    	
        this.authorizationPrincipalId = this.queryString("id");
        var me = this;
        this.invokeService("authorizationPrincipal", [this.authorizationPrincipalId,7], function (data) {

            if (data == null) {
                me.clearbindAuthorizationPrincipal();
            }
            else {
                me.authorizationPrincipal = data;
                me.databindAuthorizationPrincipal();
            }
        });
        
    },

    databindAuthorizationPrincipal: function () {
    	
        var dic = new System.Dictionary();

        if (!System.isnull( this.authorizationPrincipal.principalOperations) ) {

            for (var i = 0; i < this.authorizationPrincipal.principalOperations.length; i++) {

                var principalOperation = this.authorizationPrincipal.principalOperations[i];
                dic.add(principalOperation.operationId, principalOperation);

            }
        }

        $("i[collected=true]").each(function (index, image) {

            var operationId = $(image).attr("operationId");
            var principalOperation = dic.byKey(operationId);

            $(image).removeClass();
            if (System.isnull(principalOperation)) {

                $(image).addClass(OperationTypeColumn.No);
            }
            else {
                $(image).addClass(OperationTypeColumn.Yes);
            }
        });

        this.setAllCheckboxState();
    },

    clearbindAuthorizationPrincipal: function () {

        $("i").each(function (index, image) {
        	$(image).removeClass().addClass(OperationTypeColumn.No);
        });

        this.setAllCheckboxState();
    },

    onImageClicked: function (image) {

        var no = OperationTypeColumn.No;
        var yes = OperationTypeColumn.Yes;

        if ($(image).hasClass(no)) {
            operationType = UiOperationType.add;
        }
        else if ($(image).hasClass(yes)) {
            operationType = UiOperationType.remove;
        }
        else {
            alert("开发错误");
        }

        var pars = [this.authorizationPrincipalId,7, $(image).attr("operationId"), operationType];

        this.invokeService("updatePrincipalOperation", pars, function (data) {

            var className = null;
            if (operationType == UiOperationType.add) {
            	className = OperationTypeColumn.Yes;
            }
            else {
            	className = OperationTypeColumn.No;
            }

            $(image).removeClass().addClass(className);
        });
    },

    setAllCheckboxState: function () {

        $("input[isSelectionColumn=true]").each(function (index, checkbox) {

            var pathCodes = $(checkbox).attr("pathCode");

            var isAllAuth = true;
            var isAllNoAuth = true;

            var isAuth = false;

            $("i[collected=true]").each(function (index, image) {

                var imagePathCodes = $(image).attr("pathCode");

                if (imagePathCodes.indexOf(pathCodes) == 0) {

                    if ($(image).attr("class").indexOf(OperationTypeColumn.Yes) >= 0) {

                        isAllNoAuth = false;
                        isAuth = true;
                    }
                    else {
                        isAllAuth = false;
                    }
                }
            });

            if (isAllAuth) {
                //都有权限

                $(checkbox).attr("indeterminate", null);
                $(checkbox).attr("checked", true);
            }
            else if (isAllNoAuth) {
                //都没有权限
                $(checkbox).attr("indeterminate", null);
                $(checkbox).attr("checked", false);
            }
            else {

                $(checkbox).attr("checked", isAuth);
                $(checkbox).attr("indeterminate", true);
            }
        });
    },
    onCheckboxClicked: function (checkbox) {

        var ids = [];
        var images = [];
        var pathCodes = $(checkbox).attr("pathCode");

        var operationType = UiOperationType.remove;
        if (checkbox.checked) {

            operationType = UiOperationType.add;
        }

        $("input[id^='" + pathCodes + "']").each(function (i, n) {
            $(n).attr("checked", checkbox.checked);
        });

        $("i[collected=true]").each(function (index, image) {

            var imagePathCodes = $(image).attr("pathCode");

            if (imagePathCodes.indexOf(pathCodes) == 0) {

                ids.push($(image).attr("operationId"));
                images.push($(image));
            }
        });


        var pars = [this.authorizationPrincipalId,7, ids.join("_"), operationType];
        this.invokeService("updatePrincipalOperation", pars, function (data) {

            if (operationType == "add") {

                for (var i = 0; i < images.length; i++) {
                    var image = images[i];
                    $(image).removeClass().addClass(OperationTypeColumn.Yes);
                }
            }
            else {

                for (var i = 0; i < images.length; i++) {
                    var image = images[i];
                    $(image).removeClass().addClass(OperationTypeColumn.No);
                }
            }
        });
    }
});
