org.netsharp.panda.commerce.TreegridPart = org.netsharp.panda.commerce.ListPart
		.Extends({

			ctor : function() {
				this.base();
			},

			getSelections : function() {

				var rows = $("#" + this.context.id).treegrid('getSelections');
				return rows;
			},

			addSub : function() {

				var count = this.getSelectionCount();
				if (count > 1) {

					IMessageBox.info("只能选择一条记录!");
					return;
				} else if (count == 0) {

					IMessageBox.info("选择记录后才能新增子节点!");
					return;
				}

				var id = this.getSelectionIds();
				if (!this.onAdding()) {
					return;
				}

				var fks = [];
				if (this.context.relationRole != null
						&& this.context.relationRole != "parent_id") {
					fks.push(this.context.relationRole + ":"
							+ this.relationItem.id);
				}

				fks.push("parentId:" + id);
				this.doAdd("fk=" + fks.join(";"));
			},
			onBeforeExpand : function(row) {

				if (this.context.lazy && row) {
					
					this.queryModel.collectControl();
					var qpc = this.queryModel.getQueryParameters();
					var filters = [];
					for ( var i = 0; i < qpc.length; i++) {

						filters.push(qpc[i].Filter);
					}

					var filter = filters.join(" AND ");
					var urls = this.getFilters(filter);
					urls.push("id="+row.id);
					var url = urls.join("&");
					$("#" + this.context.id).treegrid('options').url = url;
				}
			},
			onLoadSuccess : function(row, data) {

				if(this.context.lazy){

					$("#" + this.context.id).treegrid("options").url = '/panda/rest/service?vid='+ this.context.vid + '&method=query';
				}
			},
			resetUrl : function(url) {

				var options = $("#" + this.context.id).treegrid('options');
				options.url = url;
				$("#" + this.context.id).treegrid(options);
			},

			reload : function() {

				$("#" + this.context.id).treegrid('reload');
			},

			onClickCell : function(index, field) {

			},
			doubleClickRow : function(row) {

				var editLength = $("a[code='edit']").length;
				if (editLength > 0) {

					this.edit(row.id);
				}
			},

			onSelect : function(row) {

				$("#" + this.context.id).treegrid('toggle', row.id);
			},
			setStyle : function() {

				var height = $('body').height() - 120
						- $('#queryFrom').height();
				$("#" + this.context.id).treegrid('resize', {
					height : height,
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
						
						$tree.treegrid('reload');
					}else{

						var selectedNode = $tree.tree('getSelected');
						if(selectedNode){
							
							$tree.treegrid('reload',selectedNode.target);
						}else{
							
							$tree.treegrid('reload');
						}
					}

				});
			}
		});
