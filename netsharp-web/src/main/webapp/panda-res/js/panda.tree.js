/// <reference path="core.js" />
/// <reference path="panda.js" />
/// <reference path="panda.form.js" />

org.netsharp.panda.commerce.TreePart = org.netsharp.panda.core.View.Extends({

	ctor : function() {
		this.base();
		this.paging = null;
	},
	// -----------------------
	// 查看详情
	// -----------------------
	open : function() {

		var me = this;
		var $tree = $('#'+this.context.id);
		var selectedNode = $tree.tree('getSelected');
		if (System.isnull(selectedNode)) {
			IMessageBox.info("请选择节点！");
			return;
		}

		var url = System.Url.getUrl(this.context.formUrl);
		if (url.indexOf('?') > 0) {

			url += "&id=" + selectedNode.id;
		} else {

			url += "?id=" + selectedNode.id;
		}

		url = System.Url.getUrl(url);
		url = System.Url.join(url,"openType=window");
		IMessageBox.open("修改", url, 800, 600, function() {
			
			var parentNode = $tree.tree('getParent',selectedNode.target);
			if(parentNode){
				
				$tree.tree('reload',parentNode.target);
			}else{

				$tree.tree('reload');
			}
		}, true);
	},

	// -----------------------
	// 增加节点
	// -----------------------
	add : function() {
		
		
		var $tree = $('#'+this.context.id);
		var node = $("#" + this.context.id).tree('getSelected');
		var url = this.context.formUrl;
		if (!System.isnull(node)) {
			
			var parentId = node.id;
			if (url.indexOf('?') > 0) {

				url += "&"+this.context.parentId+"=" + parentId;

			} else {

				url += "?"+this.context.parentId+"=" + parentId; // 在弹出层需要记录此parentId
			}
		}
		var me = this;
		url = System.Url.getUrl(url);
		url = System.Url.join(url,"openType=window");
		IMessageBox.open("新增", url, 800, 600, function() {
			me.pathCode(null);
		});
	},

	// -----------------------
	// 整理路径
	// -----------------------
	pathCode : function(node) {
		var me = this;
		var $tree = $("#" + this.context.id);
		this.invokeService("pathCode", [], function(jMessage) {
			
			if(node==null){
				
				$tree.tree('reload');
			}else{

				var selectedNode = $tree.tree('getSelected');
				if(selectedNode){
					
					$tree.tree('reload',selectedNode.target);
				}else{
					
					$tree.tree('reload');
				}
			}

		});
	},
	// -----------------------
	// 删除节点
	// -----------------------

	remove : function() {

		var node = $("#" + this.context.id).tree('getSelected');
		if (node != undefined && node != null) {

			var me = this;
			IMessageBox.confirm("确认要删除选中的记录吗？", function(istrue) {
				if (istrue) {
					me.doRemove(node);
				}
			});
		} else {
			IMessageBox.info("请选择节点");
		}
	},

	doRemove : function(selectedNode) {

		var me = this;
		var pars = [];
		pars.push(selectedNode.id);
		this.invokeService("delete", pars, function(jMessage) {
			
			var $tree = $("#" + me.context.id);
			var parentNode = $tree.tree('getParent',selectedNode.target);
			if(parentNode){
				
				$tree.tree('reload',parentNode.target);
			}else{

				$tree.tree('reload');
			}
		});
	},

//	// -----------------------
//	// 刷新
//	// -----------------------
//	reload : function() {
//
//		$("#" + this.context.id).tree('reload');
//	},

	onClick : function(node) {

		var $tree = $("#" + this.context.id);
		if($tree.tree('isLeaf',node.target)==false){
			$tree.tree('toggle',node.target);
		}
		
		if (node != null) {
			this.currentItem = node;
			this.notifyCurrentItemChanged();
		}
	},
	onLoadSuccess:function(node, data){
		
		//默认展开根节点
		if(node == null){
			
			var roots = $("#" + this.context.id).tree('getRoots');
			if(roots.length>0){
				
				var me = this;
				$(roots).each(function(i,root){
					
					$("#" + me.context.id).tree('expand',root.target);
				});
			}
		}
	},
	onBeforeExpand : function(node, param) {

	},
	onBeforeDrop:function(target, source, point){
		
//		var isVerify = this.verifyDrop(target, source);
//		if(!isVerify){
//			return false;
//		}
	},
	onDragEnter:function(target, source){
		
	},
	onDragOver:function(target, source){
		
	},
	onDragLeave:function(target, source){
		
	},
	onStartDrag:function(node){
		
	},
	onStopDrag:function(node){
		
	},
	onBeforeDrag:function(node){
		
	},
	
	onDrop:function(target, source, point){

		var parentNodeData = $("#" + this.context.id).tree('getData', target);
		var parentId = parentNodeData.id;
		var me = this;
		this.invokeService("changeParent", [source.id,parentId], function(jMessage) {
			
			var $tree = $("#" + me.context.id);
			var parentNode = $tree.tree('find', parentId);
			if(parentNode){
				$tree.tree('reload',parentNode.target);
			}else{

				$tree.tree('reload');
			}
		});
	},
	verifyDrop:function(target, source){
		
		return true;
	}
});
