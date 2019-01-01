/// <reference path="../../../resources/js/system.js" />
/// <reference path="../../../resources/js/core.js" />
System.Declare("org.netsharp.organization.controller.authorization.operation");

var UiOperationType =
{
    add    : 1,
    remove : 0
};

var OrganizationType = {
		SYSTEM: 1,//子公司
		CORPORATION: 2, //集团
		DEPARTMENT: 3, //部门
		PROJECT: 4,//项目
		CATETORY: 5, //分类
		POST:6//岗位
};

var OperationTypeColumn = {
	No : "fa fa-close",
	Yes : "fa fa-check"
};

//------------------------------------------------------------------------------------------------------------------------------------------
// 授权管理
//------------------------------------------------------------------------------------------------------------------------------------------
org.netsharp.organization.controller.authorization.operation.OperationAuthorizationPart = org.netsharp.panda.commerce.TreegridPart.Extends({

    //授权主体，只能对岗位授权
    authorizationPrincipal: null,
    authorizationPrincipalId: null,

    ctor: function () {
        this.base();
    },

    setRelationItem: function (relationItem) {
    	
        this.relationItem = relationItem;

        var otype = relationItem.attributes.tag;
        
        if ((!System.isnull(otype)) && otype == OrganizationType.POST) {

            this.authorizationPrincipalId = relationItem.id;

            var me = this;

            this.invokeService("authorizationPrincipal", [this.authorizationPrincipalId,OrganizationType.POST], function (data) {

                if (data == null) {

                    me.clearbindAuthorizationPrincipal();
                }
                else {

                    me.authorizationPrincipal = data;
                    me.databindAuthorizationPrincipal();

                }
            });
        }
        else {

            this.authorizationPrincipal = null;
            this.authorizationPrincipalId = null;

            this.clearbindAuthorizationPrincipal();

        }
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

            if (System.isnull(principalOperation)) {

                $(image).attr("class", OperationTypeColumn.No);
            }
            else {
                $(image).attr("class", OperationTypeColumn.Yes);
            }
        });

        this.setAllCheckboxState();
    },

    clearbindAuthorizationPrincipal: function () {

        $("i").each(function (index, image) {

            $(image).attr("class", OperationTypeColumn.No);
        });

        this.setAllCheckboxState();
    },

    onImageClicked: function (image) {

        var no = OperationTypeColumn.No;
        var yes = OperationTypeColumn.Yes;

        if (this.authorizationPrincipal == null) {

            window.alert("请选择岗位");

            return;
        }

        var operationType = null;
        if ($(image).hasClass(no)) {
            operationType = UiOperationType.add;
        }
        else if ($(image).hasClass(yes)) {
            operationType = UiOperationType.remove;
        }
        else {
            alert("开发错误");
        }
        var pars = [this.authorizationPrincipalId,OrganizationType.POST, $(image).attr("operationId"), operationType];
        this.invokeService("updatePrincipalOperation", pars, function (data) {

            var className = null;
            if (operationType == UiOperationType.add) {
            	className = OperationTypeColumn.Yes;
            }
            else {
            	className = OperationTypeColumn.No;
            }

            $(image).attr('class',className);
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

                    if ($(image).hasClass(OperationTypeColumn.Yes)) {

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

        if (this.authorizationPrincipal == null) {

            checkbox.checked = !checkbox.checked;
            window.alert("请选择岗位");

            return;
        }

        var ids = [];
        var images = [];
        var pathCodes = $(checkbox).attr("pathCode");

        var operationType = UiOperationType.remove;
        if (checkbox.checked) {

            operationType = UiOperationType.add;
        }

        $("input[id^='" + pathCodes + "']").each(function (i, n) {
            $(n).prop("checked", checkbox.checked);
        });

        $("i[collected=true]").each(function (index, image) {

            var imagePathCodes = $(image).attr("pathCode");

            if (imagePathCodes.indexOf(pathCodes) == 0) {

                ids.push($(image).attr("operationId"));
                images.push($(image));
            }
        });


        var pars = [this.authorizationPrincipalId,OrganizationType.POST, ids.join("_"), operationType];

        this.invokeService("updatePrincipalOperation", pars, function (data) {

            if (operationType == UiOperationType.add) {

                for (var i = 0; i < images.length; i++) {
                    var image = images[i];
                    image.attr("class", OperationTypeColumn.Yes);
                }
            }
            else {

                for (var i = 0; i < images.length; i++) {
                    var image = images[i];
                    image.attr("class", OperationTypeColumn.No);
                }
            }
        });
    }
});
