org.netsharp.panda.commerce.FormPart = org.netsharp.panda.core.View.Extends({

    ctor: function () {
        this.base();
        this.viewModel = new org.netsharp.panda.commerce.FormPartModel();
        this.viewModel.controller = this;

        this.paging = null;
        this.Sd = new org.netsharp.tools.SdForm(this);
        //缓存当前控制器方便弹出窗体使用
        window.formController = this;
    },

    //----------------------
    // save
    //----------------------
    save: function () {

        var isValidated = this.validate();

        if (!isValidated) {
            return;
        }

        this.viewModel.context = this.context;
        var entity = this.viewModel.getEntity();

        //如果父部件为树部件
        if (this.viewModel.parentId != null && this.viewModel.parentId != "" && this.viewModel.parentId != undefined) {

            entity.parentId = this.viewModel.parentId;
        }

        if (!this.onSaving(entity)) {
            return;
        }

        var fk = this.queryString("fk");
        if (fk != null && fk != "") {

            var properties = fk.split(';');
            for (var i = 0; i < properties.length; i++) {

                var property = properties[i];
                var pair = property.split(':');
                var expression = "entity." + pair[0] + "='" + pair[1] + "';";
                eval(expression);
            }
        }

        this.addExtraProp(entity);
        this.doSave(entity);
    },
    getfkParam: function () {
        var fk = this.queryString("fk");
        var fkParam = {};
        if (fk != null && fk != "") {
            var properties = fk.split(';');
            for (var i = 0; i < properties.length; i++) {

                var property = properties[i];
                var pair = property.split(':');
                fkParam[pair[0]] = pair[1];
            }
        }
        return fkParam;
    },
    doSave: function (entity) {

        var me = this;
        this.invokeService("save", [entity], function (jmessage) {

            me.onSaved(jmessage);
        },true,null,function(){
        	
        	
        });
    },
    addExtraProp: function (entity) {

    },

    onSaving: function (entity) {

        return true;
    },

    onSaved: function (jmessage) {
        this.currentItem = jmessage;
        if (this.currentItem != null) {

            IMessageBox.toast("保存成功！");
            this.currentItem.entityState = EntityState.Persist;
            this.viewModel.currentItem = this.currentItem;
            this.databind();
        } else {
            IMessageBox.error("保存失败！");
        }
    },

    getCurrentItem: function () {

        return this.viewModel.currentItem;
    },

    setCurrentItem: function (currentItem) {

        this.viewModel.currentItem = currentItem;
    },

    databind: function () {

        if (this.viewModel.currentItem == null) {

            this.viewModel.clear();
            this.currentItem = null;
        } else {

            this.viewModel.setEntity(this.viewModel.currentItem);
        }

        this.notifyCurrentItemChanged();
        this.databindafter();
        this.databindextra(this.viewModel.currentItem);
    },
    databindafter: function () {
        $('.easyui-combobox,.easyui-combogrid').combobox("initClearBtn");
        $('.easyui-filebox').filebox("initClearBtn");
    },
    databindextra: function (entity) {

    },
    onload: function () {

        var id = this.queryString("id");
        if (System.isnull(id)) {

            this.add();
        } else {
            this.byId(id);
        }

        //
//        var formId = this.context.formName;
//        var $toolbar = $('#'+formId).prev();
//        if($toolbar){
//
//        	var toolbarId = $toolbar.attr('id');
//        	var html = $toolbar.prop("outerHTML");
//        	$toolbar.remove();
//
//        	var $panel = $('body.easyui-layout>.layout-panel:first');
//        	$panel.append(html);
//
//        	$('#'+toolbarId).addClass('global-toolbar');
//        }
    },

    //-----------------------
    // remove
    //----------------------
    remove: function () {

        var id = this.viewModel.currentItem.id.toString();
        var me = this;
        var removeCallback = function (isConfirmed) {

            if (!isConfirmed) {
                return;
            }

            me.invokeService("remove", [id, me.paging], function (jmessage) {

                if (jmessage.IsSucceed) {

                    me.viewModel.currentItem = jmessage.ReturnValue;
                    if (me.viewModel.currentItem != null) {

                        me.viewModel.currentItem.entityState = EntityState.Transient;
                    }

                    me.databind();
                    IMessageBox.toast("删除成功！");
                } else {

                    IMessageBox.warning(jmessage.Exception.Message);
                }
            });
        };

        IMessageBox.confirm("删除后不能还原，确定要执行此操作吗？", removeCallback);
    },

    //-----------------------
    // audit
    //----------------------
    audit: function () {

        var isValidated = this.validate();
        if (!isValidated) {
            return;
        }

        //明细表验证
        $.each(this.subs, function (index, sub) {

            isValidated = sub.validate();
            if (!isValidated) {
                return;
            }
        });

        this.viewModel.context = this.context;
        var entity = this.viewModel.getEntity();
        if (!System.isnull(this.viewModel.parentId)) {

            entity.parentId = this.viewModel.parentId;
        }

        if (!this.onAuditing()) {
            return;
        }

        var fk = this.queryString("fk");
        if (fk != null && fk != "") {

            var properties = fk.split(';');
            for (var i = 0; i < properties.length; i++) {

                var property = properties[i];
                var pair = property.split(':');
                var expression = "entity." + pair[0] + "='" + pair[1] + "';";
                eval(expression);
            }
        }

        var me = this;

        this.invokeService("audit", [entity], function (jmessage) {

            me.onAudited(jmessage);
        });
    },

    onAuditing: function () {

        return true;
    },

    onAudited: function (jmessage) {

        this.currentItem = jmessage;
        this.currentItem.entityState = EntityState.Persist;
        this.viewModel.currentItem = this.currentItem;
        this.databind();
        IMessageBox.info("审核成功!");
    },

    //-----------------------
    // unaudit
    //----------------------
    unaudit: function () {

        var isValidated = this.validate();
        if (!isValidated) {
            return;
        }

        //明细表验证
        $.each(this.subs, function (index, sub) {

            isValidated = sub.validate();
            if (!isValidated) {
                return;
            }
        });

        this.viewModel.context = this.context;
        var entity = this.viewModel.getEntity();
        if (this.viewModel.parentId != null && this.viewModel.parentId != "" && this.viewModel.parentId != undefined) {

            entity.parentId = this.viewModel.parentId;
        }

        if (!this.onUnAuditing()) {
            return;
        }

        var fk = this.queryString("fk");
        if (fk != null && fk != "") {

            var properties = fk.split(';');
            for (var i = 0; i < properties.length; i++) {

                var property = properties[i];
                var pair = property.split(':');
                var expression = "entity." + pair[0] + "='" + pair[1] + "';";
                eval(expression);
            }
        }

        var me = this;
        this.invokeService("unAudit", [entity], function (jmessage) {

            me.onUnAudited(jmessage);

        });
    },

    onUnAuditing: function () {

        return true;
    },

    onUnAudited: function (jmessage) {
        this.currentItem = jmessage;
        this.currentItem.entityState = EntityState.Persist;
        this.viewModel.currentItem = this.currentItem;
        this.databind();
        IMessageBox.info("弃审成功！");
    },
    //-----------------------
    // print
    //----------------------
    print: function () {

        var id = this.queryString("id");
        if (System.isnull(id)) {
            IMessageBox.warning("新增状态下不能打印！");
            return;
        }

        var vid = this.workspaceId;
        var url = "/panda/formDetail?viewid=" + vid + "&id=" + id;
        $.get(url, function (data) {

            var printHtml = data;
            LODOP = getLodop();
            LODOP.PRINT_INIT("打印");
            LODOP.SET_PRINTER_INDEX(-1);
            LODOP.SET_PRINT_PAGESIZE(0, 0, 0, "A4");
            LODOP.ADD_PRINT_HTM("1mm", "1mm", "200mm", "287mm", printHtml);
            //LODOP.PREVIEW();
            LODOP.PRINTA();
        });
    },

    //导出excel
    excel: function () {

    },

    //-----------------------
    // reload
    //----------------------
    reload: function () {

        window.location.reload();
    },

    //-----------------------
    // add
    //----------------------
    add: function () {

        var me = this;
        var url = window.location.href;
        var parentId = System.Url.getParameter(this.context.parentId);
        this.invokeService("newInstance", [parentId], function (jmessage) {

            me.currentItem = jmessage;
            var fk = me.queryString("fk");
            if (fk != null && fk != "") {

                var properties = fk.split(';');
                for (var i = 0; i < properties.length; i++) {

                    var property = properties[i];
                    var pair = property.split(':');
                    var expression = "me.currentItem." + pair[0] + "='" + pair[1] + "';";
                    eval(expression);
                }
            }

            me.viewModel.currentItem = me.currentItem;
            me.currentItem.entityState = EntityState.New;
            me.added(me.currentItem);
            if (me.currentItem == null) {

                me.viewModel.clear();
            } else {

                me.databind();
            }
        });
    },

    added: function (currentItem) {

    },

    //-----------------------
    // byId
    //----------------------
    byId: function (id) {

        var vm = this.viewModel;
        var me = this;

        this.invokeService("byId", [id], function (jmessage) {

            var nav = jmessage;
            vm.currentItem = nav.Entity;
            vm.currentItem.entityState = EntityState.Persist;
            me.paging = nav.Paging;
            me.databind();
        });
    },

    copy: function () {

        var item = this.viewModel.currentItem;

        if (System.isnull(item.entityState)) {

            IMessageBox.warning("请先保存当前记录！");
            return;
        } else if (item.entityState == EntityState.New || item.entityState == EntityState.Deleted) {

            IMessageBox.warning("请先保存当前记录！");
            return;
        }

        var vm = this.viewModel;
        var me = this;
        this.invokeService("copy", [item.id], function (jmessage) {

            if (jmessage.IsSucceed) {

                vm.currentItem = jmessage.ReturnValue;
                me.databind();
            } else {

                IMessageBox.warning(jmessage.Exception.Message);
            }
        });
    },

    //-----------------------
    // first
    //----------------------
    first: function () {

        this.navigation("first", []);
    },

    //-----------------------
    // pre
    //----------------------
    pre: function () {

        var item = this.viewModel.currentItem;
        this.navigation("pre", [item.id, this.paging]);
    },

    //-----------------------
    // next
    //----------------------
    next: function () {

        var item = this.viewModel.currentItem;
        this.navigation("next", [item.id, this.paging]);
    },

    //-----------------------
    // last
    //----------------------
    last: function () {

        var item = this.viewModel.currentItem;
        this.navigation("last", [item.id]);
    },

    reload: function () {

        var id = null;
        if (this.currentItem) {

            id = this.currentItem.id;
        } else {
            id = this.viewModel.currentItem.id;
        }

        this.byId(id);
    },

    navigation: function (methodName, pars) {

        var vm = this.viewModel;
        var me = this;
        this.invokeService(methodName, pars, function (jmessage) {
            var nav = jmessage;
            vm.currentItem = nav.Entity;
            me.paging = nav.Paging;
            me.databind();
        });
    },

    validate: function () {

        var isValidate = $("#" + this.context.formName).form('validate');
        return isValidate;
    },

    attachment: function () {

        var foreignKey = this.queryString("id");
        if (System.isnull(foreignKey)) {

            IMessageBox.info("新增时不能上传附件！");
            return;
        }
        var url = '/nav/panda-platform/attachment/attachment-list?foreignKey=' + foreignKey + "&entityId=" + this.context.entityId;
        var me = this;
        var close = function () {
            me.reload();
        };

        IMessageBox.open("附件", url, 1000, 600, close, true);
    },

    //==================================
    // 状态控制
    //==================================
    getfirstState: function () {

        if (this.paging.PageIndex == 1) {

            return UiElementState.Disable;
        }

        if (this.paging.TotalCount <= 1) {

            return UiElementState.Disable;
        }

        return UiElementState.Empty;
    },

    getpreState: function () {

        return this.getfirstState();
    },

    getnextState: function () {

        if (this.paging.TotalCount <= 1) {

            return UiElementState.Disable;
        }

        if (this.paging.TotalCount == this.paging.PageIndex) {

            return UiElementState.Disable;
        }

        return UiElementState.Empty;
    },

    getendState: function () {

        return this.getnextState();
    },

    getsaveState: function () {

        return UiElementState.Empty;
    },

    getexportState: function () {

        return UiElementState.Hide;

    },
    getprintState: function () {

        //return UiElementState.Hide;

    },
    getremoveState: function () {

        if (this.viewModel.currentItem == null) {

            return UiElementState.Disable;
        }

        return UiElementState.Empty;
    },

    getokState: function () {

        return UiElementState.Hide;
    },

    getreloadState: function () {

        if (this.viewModel.currentItem == null) {

            return UiElementState.Disable;
        }

        return UiElementState.Empty;
    },
    getaddState: function () {

        return UiElementState.Empty;
    },

    jsondata: function () {

        var pars = [this.viewModel.currentItem.id];

        var message = new org.netsharp.core.JMessage();

        message.Service = this.service;
        message.Method = "byId";
        message.ViewId = this.context.ViewId;
        message.Service = this.context.service;

        var jpars = new Array();

        if (pars) {
            for (var i = 0; i < pars.length; i++) {

                var jpa = new org.netsharp.core.JParameter();

                jpa.Value = pars[i];

                //
                var flag = new org.netsharp.core.JsonObjectSerializor().isPrimitiveType(jpa.Value);
                jpa.IsPrimivieType = flag.IsPrimitiveType;

                jpars.push(jpa);
            }
        }

        message.Parameters = jpars;

        var jstr = org.netsharp.core.JsonSerializor.seralize(message);

        $.ajax({
            url: "/JService.jservice",
            type: "Post",
            dataType: "text",
            data: jstr,
            async: true,
            success: function (data) {

                IMessageBox.info(data);
            },
            error: function (p1, p2, p3) {

                IMessageBox.error(data);
            }
        });
    },

    disable: function () {

        this.viewModel.disable();

        var me = this;
        for (var i = 0; i < this.statusControls.length; i++) {

            var uielement = me.statusControls[i];
            if (uielement == undefined || uielement == null) {

                continue;
            }
            me.setElementState(uielement, UiElementState.Disable);
        }
    },
    enable: function () {

        this.viewModel.enable();

        var me = this;
        for (var i = 0; i < this.statusControls.length; i++) {

            var uielement = me.statusControls[i];
            if (uielement == undefined || uielement == null) {

                continue;
            }
            me.setElementState(uielement, UiElementState.Enable);
        }
    }
});

//end FormPart
//---------------------------------------------------------------------------------
org.netsharp.panda.commerce.FormPartModel = System.Object.Extends({
    ctor: function () {

        this.currentItem = null;
        this.iscollected = false;
        this.context = null;
        this.controller = null;
        this.controls = new Array();
    },
    getEntity: function () {

        this.collectControl();

        if (this.currentItem == null) {

            this.currentItem = {
                "entityState": EntityState.New
            };
        }
        else if (this.currentItem.entityState != EntityState.New) {

            this.currentItem.entityState = EntityState.Persist;
        }

        for (var i = 0; i < this.controls.length; i++) {

            var control = this.controls[i];
            control.get(this.currentItem);
        }


        //明细列表
        var me = this;
        var subs = this.controller.subs;
        $.each(subs, function (index, sub) {

            var details = sub.getDetails();
            var expression = "me.currentItem." + sub.context.relationRole + "=details;";
            eval(expression);
        });

        return this.currentItem;
    },

    setEntity: function (entity) {

        this.collectControl();
        this.currentItem = entity;
        for (var i = 0; i < this.controls.length; i++) {
            var control = this.controls[i];
            control.set(entity);
        }
    },

    clear: function () {

        this.collectControl();
        for (var i = 0; i < this.controls.length; i++) {

            var control = this.controls[i];
            control.clear();
        }

    },
    disable: function () {

        var controls = this.controls;
        $(controls).each(function (i, item) {
            item.disable();
        });

        var subs = this.controller.subs;
        $.each(subs, function (index, sub) {

            sub.disable();
        });

    },
    enable: function () {

        var controls = this.controls;
        $(controls).each(function (i, item) {
            item.enable();
        });

        var subs = this.controller.subs;
        $.each(subs, function (index, sub) {

            sub.enable();
        });

    },
    collectControl: function () {

        if (this.iscollected) {
            return;
        }

        var formName = this.controller.context.formName;
        this.iscollected = true;
        var viewmodel = this;
        viewmodel.controls = [];
        var formName = this.controller.context.formName;
        var items = $("#" + formName).find("input[collected=true],textarea[collected=true],label[collected=true],a[collected=true],select[collected=true],img[collected=true]");

        for (var i = 0; i < items.length; i++) {

            var item = items[i];
            var controlType = $(item).attr("customControlType") || ('org.netsharp.controls.' + $(item).attr("controlType"));
            var control = null;
            var expression = 'control= new ' + controlType;
            eval(expression);
            if (control) {

                control.formName = formName;
                control.uiElement = item;
                control.propertyName = item.id;
                control.init();
                viewmodel.controls.push(control);
            }
        }
    }
});
