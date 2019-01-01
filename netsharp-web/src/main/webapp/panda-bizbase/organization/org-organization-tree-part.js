
System.Declare("org.netsharp.organization.controller.organization");

org.netsharp.organization.controller.organization.OrganizationTreePart = org.netsharp.panda.commerce.TreePart.Extends({

	doCreateWeiXin:function(){
		this.invokeService("doCreateWeiXin", [], function(data) {
          
        	  IMessageBox.info(data);
         
        });
	},
	verifyDrop:function(target, source){
		
//		var parentNodeData = $("#" + this.context.id).tree('getData', target);   
//		debugger;
//    	if(parentNodeData==null || parentNodeData==''){
//    		IMessageBox.info('请选择调入部门。');
//			return false;
//    	}
//    	
//    	var tag = parseInt(parentNodeData.attributes.tag);
//    	if(tag!=3){
//    		IMessageBox.info('调入节点必须为部门。');
//			return false;
//    	}
//    	if(node.id==parentNodeData.id){
//    		IMessageBox.info('不能调入自身部门。');
//			return false;
//    	}
    	
		return true;
	},
	doChangeParent:function(){
		var that=this;
		var tree = $("#" + this.context.id);
		var node = $("#" + this.context.id).tree('getSelected');
		if(!node){
			IMessageBox.info('请选择要调整的部门或岗位。');
			return;
		}
    	if($("#pandaWindow").length ==0){

			$(document.body).append("<div id='pandaWindow'></div>");
    	}
		$("#pandaWindow").html('');
		$("#pandaWindow").html('<div><ul id="parentDeparmentTree" class="easyui-tree"></ul></div>');
		
		$('#parentDeparmentTree').tree({
			multiple:false,
			url:'/panda/rest/service?vid='+that.context.vid+'&method=query'
		});
		
		$("#pandaWindow").dialog({
			title:"请选择上级部门",
			top:$(document).scrollTop() + ($(window).height()-300) * 0.5,
			left:$(document).scrollLeft() + ($(window).width()-550) * 0.5,
			width:550,
			height:300, 
			//按钮  
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: function () {
                	var selectNode=$('#parentDeparmentTree').tree("getSelected");                	
                	if(selectNode==null || selectNode==''){
                		IMessageBox.info('请选择调入部门。');
            			return;
                	}
                	if(selectNode.attributes.tag!='Department'){
                		IMessageBox.info('调入节点必须为部门。');
            			return;
                	}
                	if(node.id==selectNode.id){
                		IMessageBox.info('不能调入自身部门。');
            			return;
                	}
                	that.invokeService("doChangeParent", [node.id,selectNode.id], function(data) {
                		
                		$(tree).tree('reload');
                		$("#pandaWindow").dialog("close");
                	});
                }
            },
            {  
                text: '关闭',  
                iconCls: 'icon-no',  
                handler: function () {  
            		$("#pandaWindow").dialog("close");
                }  
            }]
            
		});
		
	}	
});

org.netsharp.organization.controller.organization.ClassificationOrganizationTreePart = org.netsharp.organization.controller.organization.OrganizationTreePart.Extends({
	ctor: function () {
        this.base();
        this.jServiceLocator = null;
    },
    
    add : function() {

		var tree = $("#" + this.context.id);
		var node = $("#" + this.context.id).tree('getSelected');
		
		var url = this.context.formUrl;

		if (!System.isnull(node)) {
			var parentId = node.id;
			var flag=true;
			this.invokeService("isLogonOrganization", [parentId], function (data) {
	            if(!data){
	            	flag=false;
	            }
	        },false);
			
			if(!flag){
				window.alert("没有该组织的权限，请重新选择！");
            	return;
			}

			if (url.indexOf('?') > 0) {

				url += "&"+this.context.parentId+"=" + parentId;

			} else {

				url += "?"+this.context.parentId+"=" + parentId; // 在弹出层需要记录此parentId
			}
		}else{
			window.alert("不能增加根节点，请选择父级节点！");
        	return;
		}
		
		url = System.Url.getUrl(url);
		url = System.Url.join(url,"openType=window");
		var me=this;
		IMessageBox.open("新增", url, 800, 600, function() {
			me.pathCode();
			$(tree).tree('reload');
		});
	},
	
	remove : function() {

		var tree = $("#" + this.context.id);

		var node = $("#" + this.context.id).tree('getSelected');

		if (node != undefined && node != null) {

			var me = this;
			
			var parentId = node.id;
			var flag=true;
			this.invokeService("isLogonOrganization", [parentId], function (data) {
	            if(!data){
	            	flag=false;
	            }
	        },false);
			
			if(!flag){
				window.alert("没有该组织的权限，请重新选择！");
            	return;
			}
			
			if(flag){
        		this.invoke("isLoginShop",[parentId],function(data){
        			if(data){
        				flag=false;
        			}
        		},false);
        		if(!flag){
        			window.alert("不能删除所在门店，请重新选择！");
        			return;
        		}
			}

			
			IMessageBox.confirm("确认要删除选中的记录吗？", function(istrue) {
				if (istrue) {
					me.doRemove(node);
					$(tree).tree('reload');
				}
			});
		} else {
			IMessageBox.info("请选择节点");
		}
	},
	
	invoke : function (method, pars, callback,isAsyn,errorCallback) {
	       
        if (this.jServiceLocator == null) {
			this.jServiceLocator = new org.netsharp.core.JServiceLocator();
		}
        var me = this;

        var thisCallback = function (data) {
       
            if (!System.isnull(callback)) {
                callback(data);
            }
           
        };

        this.jServiceLocator.invoke("com.ykx.smartshop.system.web.ShopOrganizationControls", method, pars, thisCallback, null, isAsyn, errorCallback);

    },
});


