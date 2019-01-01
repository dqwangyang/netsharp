/// <reference path="panda.js"/>

org.netsharp.panda.commerce.DetailPart = org.netsharp.panda.core.View.Extends({

	dataSource : null,
	ctor : function() {
		
		this.base();
		this.referenceId = "";
		this.dataSource = new org.netsharp.panda.commerce.DetailCollection();
        this.viewModel = new org.netsharp.panda.commerce.DetailPartFormModel();
        this.viewModel.controller = this;
		this.Sd = new org.netsharp.tools.SdDetailPart(this);
		this.isHeight = false;
		this.disabled = false;
		this.cmenu = null;
	},
	onHeaderContextMenu: function(e, field){
		
	},
	getDialog:function(){
		
		return $("#"+this.context.code+"_dialog");
	},
	getGrid:function(){
		
		return $('#' + this.context.id);
	},

	getSelections : function() {

		return this.getGrid().datagrid('getSelections');
	},
	
	setRelationItem : function(relationItem) {

		this.relationItem = relationItem;

		if (this.relationItem == null) {

		} else {

			this.referenceId = relationItem["id"];
			eval("this.dataSource.subs = relationItem." + this.context.relationRole + ";");
			if (this.dataSource.subs == undefined || this.dataSource.subs == null) {
				this.dataSource.subs = [];
				eval("relationItem." + this.context.relationRole + "=this.dataSource.subs;");
			}

			var $grid = this.getGrid();
			var fields = $grid.datagrid('getColumnFields', false);
			$.each(this.dataSource.subs, function(id, item) {

				$.each(fields, function(index, field) {

					var columnOption = $grid.datagrid('getColumnOption', field);
					var foreignName = columnOption.foreignName;

					if (field[field.length - 1] != "_" && field.indexOf('_') != -1) {

						var practicalField = field.replace('_', '.');
						var obj = eval('item.' + practicalField.split('.')[0]);
						if (obj != undefined) {
							var expression = 'item.' + field + '=item.' + practicalField;
							eval(expression);
						}
					}
				});

			});

			this.dataSource.renderRows();
			this.databind();
		}
	},
	
	/******************************************
	 * 
	 * 处理引用字段显示问题
	 */
	referenceField:function(entity){
		
		var $grid = this.getGrid();
		var fields = $grid.datagrid('getColumnFields', false);
		$.each(fields, function(index, field) {

			var columnOption = $grid.datagrid('getColumnOption', field);
			var foreignName = columnOption.foreignName;
			if (field[field.length - 1] != "_" && field.indexOf('_') != -1) {

				var practicalField = field.replace('_', '.');
				var obj = eval('entity.' + practicalField.split('.')[0]);
				if (obj != undefined) {
					var expression = 'entity.' + field + '=entity.' + practicalField;
					eval(expression);
				}
			}
		});
	},
	onHeaderContextMenu:function(){
		
		
	},
	/******************************************
	 * 
	 * 表格右击事件：创建右击菜单
	 * 
	 */
	onRowContextMenu:function(e, index, row){

		return;
		if(this.disabled === true){
			
			return;
		}
        e.preventDefault();
        var $grid = this.getGrid();
        $grid.datagrid('unselectAll');
        $grid.datagrid('selectRow', index);
        if (!this.cmenu){

        	var me = this;
            this.cmenu = $('<div/>').appendTo('body');
            this.cmenu.menu();
            this.cmenu.menu('appendItem', {
                text: '删除',
                name: 'remove',
                iconCls: 'fa fa-trash-o',
                onclick:function(){
                	
                	me.remove(index,row);
                }
            });
            
            this.cmenu.menu('appendItem', {
                text: '修改',
                name: 'edit',
                iconCls: 'fa fa-edit',
                onclick:function(){
                	me.edit(row);
                }
            });
            
            this.cmenu.menu('appendItem', {
                text: '复制',
                name: 'copy',
                iconCls: 'fa fa-copy',
                onclick:function(){
                	
                	me.copy(index,row);
                }
            });
            
            this.cmenu.menu('appendItem', {
                text: '置顶',
                name: 'top',
                iconCls: 'fa fa-chevron-up',
                onclick:function(){
                	
                	me.top(index,row);
                }
            });
            this.cmenu.menu('appendItem', {
                text: '上移',
                name: 'up',
                iconCls: 'fa fa-chevron-up',
                onclick:function(){
                	
                	me.up(index,row);
                }
            });
            
            this.cmenu.menu('appendItem', {
                text: '下移',
                name: 'down',
                iconCls: 'fa fa-chevron-down',
                onclick:function(){
                	
                	me.down(index,row);
                }
            });
            this.cmenu.menu('appendItem', {
                text: '置底',
                name: 'bottom',
                iconCls: 'fa fa-chevron-down',
                onclick:function(){
                	
                	me.bottom(index,row);
                }
            });
        }
        
        this.cmenu.menu('show', {
            left:e.pageX,
            top:e.pageY
        });
        
	},
	
	/******************************************
	 * 
	 * 表格数据绑定
	 * 
	 */
	databind : function() {
		
		if(this.dataSource.subs){
			
			$(this.dataSource.subs).each(function(i,item){
				//选单过来的
				if(item.entityState == EntityState.New){
					item.isSelectVoucher=true;
				}
			});
		}
		
		this.getGrid().datagrid('loadData', this.dataSource.subs);
		this.showAggregateColumn();
	},
	
	/******************************************
	 * 
	 * 判断一条行数据是否存在
	 * 
	 */
	isHasRow:function(rows,item){
		
		var isHasRow=false;
		rows.forEach(function(row, index, array) {
			
			if(item.id == row.id){
				isHasRow=true;
				return ;
			}
		});
		return isHasRow;
	},
	
	/******************************************
	 * 
	 * 获取明细数据
	 * 
	 */
	getDetails : function() {

		// 处理引用实体的ID
		var $grid = this.getGrid();
		var fields = $grid.datagrid('getColumnFields', false);
		var rows = [];
		//处理状态(没有变化的呢？,应该也要带到后台)
        var insertedRows = $grid.datagrid("getChanges", "inserted");
        var deletedRows = $grid.datagrid("getChanges", "deleted");
        var updatedRows = $grid.datagrid("getChanges", "updated");
        var allRows = $grid.datagrid("getRows");

        $(insertedRows).each(function (i, row) {
            row.entityState = EntityState.New;
            row.id = null;//主要解决int类型  hw bug
            rows.push(row);
        });

        $(deletedRows).each(function (i, row) {
            //数据库中存在的
            if (row.entityState) {
                row.entityState = EntityState.Deleted;
                rows.push(row);
            }
        });

        $(updatedRows).each(function (i, row) {
            row.entityState = EntityState.Persist;
            rows.push(row);
        });
        
        var me = this;
        $(allRows).each(function (i, row) {
        	if(row.isSelectVoucher==true&&row.entityState==EntityState.New){
        		rows.push(row);	
        	}else if(!me.isHasRow(rows,row)){
	            row.entityState = EntityState.Persist;
	            rows.push(row);	
       		}
        });
        
        var returnRows = [];
		$.each(rows, function(id, item) {
			
			if(item.isSelectVoucher==true&&item.entityState == EntityState.Deleted){
				
			}else{
				
				if(item.isSelectVoucher==true){

					eval("delete item.isSelectVoucher");
				}
				
				if (item.entityState != EntityState.New && item.entityState != EntityState.Deleted) {
	
					item.entityState = EntityState.Persist;
				}

				$.each(fields, function(index, field) {

					if (field.indexOf('_') != -1) {
						eval("delete item." + field);
					}
				});

				returnRows.push(item);
			}
			
		});

		return returnRows;
	},

	/******************************************
	 * 
	 * 表格加载完成事件
	 * 
	 */
	loadSuccess : function(data, grid_Id) {

	},
	
	/******************************************
	 * 
	 * 在用户选择一行的时候触发
	 * 
	 */
	onSelect : function(rowIndex, rowData) {

	},
	
	/******************************************
	 * 
	 * 在用户双击一行的时候触发，参数包括：
	* rowIndex：点击的行的索引值，该索引值从0开始。
	* rowData：对应于点击行的记录。
	 * 
	 */	
	doubleClickRow : function(rowIndex, rowData) {
		
		this.edit(rowData);
	},
	
	/******************************************
	 * 
	 * 保存数据
	 * 
	 */
	save:function(){
		
		var isValidated = $("#" + this.context.formName).form('validate');
        if (!isValidated) {
            return;
        }
        layer.closeAll();
        this.viewModel.context = this.context;
        var entity = this.viewModel.getEntity();
        this.saveBefore(entity);
        this.viewModel.clear();
        
        //特殊处理引用字段
        this.referenceField(entity);
        
        //这里主要解决int自增Id的BUG,在提交保存时要删除状态为new的明细实体Id
        entity.id = entity.id || System.GUID.newGUID();
        
        var $grid = this.getGrid();
        
        $grid.datagrid('unselectAll');
        
        //选中一行
        $grid.datagrid('selectRecord', entity.id);
        
        var selectedRow = $grid.datagrid('getSelected');
        if(selectedRow == null){
        	
        	//BUG：在修改新增数据时有问题
        	$grid.datagrid('appendRow', entity);
            
        }else{
        	
        	var rowIndex = $grid.datagrid('getRowIndex', entity);
        	$grid.datagrid('updateRow',{index: rowIndex,row: entity});
        	$grid.datagrid('refreshRow', rowIndex);
        }
        
        this.getDialog().dialog('close');
		this.saveAfter(entity);
	},

	/******************************************
	 * 
	 * 新增，主要处理引用的值
	 * 
	 */
	add : function() {

		this.addBefore();
		var me = this;
		this.invokeService("newInstance", [], function(data) {

			//处理关联
			eval('data.'+me.context.foreignKey+'='+me.referenceId);
			me.viewModel.setEntity(data);
			//me.getDialog().dialog('open');

			var width = me.context.width;
			var height = me.context.height;
			var title = me.context.title;
			
	        var formName = me.context.formName;
//	        $('#'+formName).show();
//	        $('#'+formName).css('display','block'); 
	        $('#'+formName).attr("style","");
	    	layer.open({
			  type:1,
			  zIndex:100000,
			  title: title,
			  fixed: false,
			  maxmin: false,
			  shadeClose:true,
			  area: [width, height],
			  btn: ['确定', '取消'],
			  content:  $('#'+formName),
			  yes:function(index, layero){
				  
				  me.save();
				  //layer.close(index);
			  },
			  end:function(){
				  
				 // $('#'+formName).hide();
				  $('#'+formName).attr("style","display:none");
			  }
			});
	    	me.addAfter();
	    	
		});
	},

	/******************************************
	 * 
	 * 修改
	 * 
	 */
	edit:function(rowData){

		this.editBefore();
		var me = this;
		this.viewModel.setEntity(rowData);
//		this.getDialog().dialog('open');
		var width = this.context.width;
		var height = this.context.height;
		var title = this.context.title;
        var formName = me.context.formName;
        if($('#'+formName).length==0){
        	
        	return;
        }
        $('#'+formName).attr("style","");
    	layer.open({
		  type:1,
		  zIndex:100000,
		  title: title,
		  fixed: false,
		  maxmin: false,
		  shadeClose:true,
		  area: [width, height],
		  btn: ['确定', '取消'],
		  content:  $('#'+formName),
		  yes:function(index, layero){
			  
			  me.save();
			  //layer.close(index);
		  },
		  end:function(){
			  
//			  $('#'+formName).css('display','none');
			  $('#'+formName).attr("style","display:none");
		  }
		});
	},
	addBefore:function(){
		
	},
	addAfter:function(){
		//表格明细弹出窗口后调用，等同于onload
	},
	editBefore:function(){
		
	},
	saveAfter:function(){
		//表格明细弹出窗体后，点击确认时调用，可以修改实体
	},
	saveBefore:function(){
		
	},

	/******************************************
	 * 
	 * 删除一行数据
	 * 
	 */
	remove : function(rowIndex,row) {

		var $grid = this.getGrid();
		if(row){
			
			$grid.datagrid('deleteRow',rowIndex);
			this.dataSource.remove(row);
			return;
		}
		
		var rows = this.getSelections();
		if (rows.length == 0) {
			
			IMessageBox.toast("请选择行记录");
			return;
		}
		
		var me = this;
		rows.forEach(function(row, index, array) {
			  
			rowIndex = $grid.datagrid('getRowIndex',row); 
			$grid.datagrid('deleteRow',rowIndex);
			me.dataSource.remove(row);
		});
		
	},
	
	/******************************************
	 * 
	 * 复制一行数据
	 * 
	 */
	copy : function(rowIndex,row) {

		if(row == null){
			return;
		}
		
		delete row.id;
		this.getGrid().datagrid('appendRow', row);
	},
	
	/******************************************
	 * 
	 * 置顶一行数据
	 * 
	 */
	top : function(index,row) {

		if (this.dataSource.rows[0] == row) {
			return;
		}

		this.dataSource.rows.splice(index, 1);
		this.dataSource.rows.splice(0, 0, row);
		this.databind();
		this.dataSource.sortSubs();
	},

	/******************************************
	 * 
	 * 向上移动一行数据
	 * 
	 */
	up : function(index,row) {

		if (this.dataSource.rows[0] == row) {

			return;
		}

		this.dataSource.rows.splice(index, 1);
		this.dataSource.rows.splice(index - 1, 0, row);
		this.databind();
		this.dataSource.sortSubs();
	},

	/******************************************
	 * 
	 * 置底一行数据
	 * 
	 */
	down : function(index,row) {

		if ((index + 1) == this.dataSource.rows.length) {
			return;
		}

		this.dataSource.rows.splice(index, 1);
		this.dataSource.rows.splice(index + 1, 0, row);
		this.databind();
		this.dataSource.sortSubs();
	},
	
	/******************************************
	 * 
	 * 向下移动一行数据
	 * 
	 */
	bottom : function(index,row) {

		if ((index + 1) == this.dataSource.rows.length) {
			return;
		}

		this.dataSource.rows.splice(index, 1);
		this.dataSource.rows.push(row);
		this.databind();
		this.dataSource.sortSubs();
	},

	uiProperty : function() {

		var url = "/Panda/UiProperty/DatagridProject.view";
		url = url + "?id=" + this.context.datagridId;

		// var me = this;

		var windowHeight = window.screen.height;
		var windowWidth = window.screen.width;
		var height = 600;
		var width = 800;

		var top = (windowHeight - height) / 2;
		var left = (windowWidth - width) / 2;

		url = System.Url.getUrl(url);
		window.open(url, "栏目设置", "height=" + height + ",width=" + width + ",top=" + top + ",left=" + left + ",toolbar=no, menubar=no,scrollbars=no,resizable=no,location=no,status=no");
	},
	
	/******************************************
	 * 
	 * Tabs选中事件
	 * 
	 */
	onTabSelect : function(title, index) {

		if (!this.isHeight) {
			var subheight = $("#tab" + this.context.relationRole).height();
			this.getGrid().datagrid('resize',{
				"height" : subheight - 31
			});
			this.isHeight = true;
		}
	},
	
	/******************************************
	 * 
	 * 显示底部统计列
	 * 
	 */
	showAggregateColumn:function(){
		
		var $grid = this.getGrid();
		var fields = $grid.datagrid('getColumnFields');
		var rows = $grid.datagrid('getRows');
		var aggregateFields = [];
		var footer = [];
		var footerItem = {};
		$(fields).each(function(i,field){
			
			var options = $grid.datagrid('getColumnOption',field);
			if(options.aggregateType == AggregateType.Sum){
				
				var sumField = 0;
				var val = 0;
				$(rows).each(function(i,row){
					
					val = eval("row."+field);
					sumField += parseFloat(val)
				});
				eval("footerItem." + field + "=sumField;");
			}
		});
		footer.push(footerItem);
		$grid.datagrid('reloadFooter',footer);
	},
	
	/******************************************
	 * 
	 * 禁用，禁止工具栏的使用状态
	 * 
	 */
    disable:function(){
    	
    	this.disabled = true;
    	this.setState(); 
    },
    
	/******************************************
	 * 
	 * 启用，启用工具栏的使用状态
	 * 
	 */
    enable:function(){
    	
    	this.disabled = false;
    	this.setState(); 
    },

	/******************************************
	 * 
	 * 部件加载完成事件
	 * 可以处理样式，或绑定事件等操作
	 * 
	 */	
    onload: function () {
    	
    	this.resize();
    },
    resize:function(){
    	
    	var subheight = $('#center').height();
    	if($('#center').find('.easyui-tabs').length >0){
    		
    		subheight = subheight-35;
    	}else{
    		
    		subheight=subheight-10;
    	}
    	this.getGrid().datagrid('resize',{ 'height': subheight });
    },
	addState : function() {
		
		if (this.disabled === true) {

			return UiElementState.Disable;
		}

		return UiElementState.Empty;
	},
	removeState : function() {
		
		if (this.disabled === true) {

			return UiElementState.Disable;
		}

		return UiElementState.Empty;
	},
});


/*********************************************************************
 * 
 * 明细表格收集器
 * 
 ********************************************************************/
org.netsharp.panda.commerce.DetailCollection = System.Object.Extends({

	subs : [], // 包括删除状态的明细行
	rows : [], // 界面显示的明细行
	total : null, // 界面显示的总行数

	ctor : function() {

	},

	/******************************************
	 * 
	 * 初始化数据
	 * 
	 */	
	renderRows : function() {

		this.rows.length = 0;
		for ( var i = 0; i < this.subs.length; i++) {

			var sub = this.subs[i];
			var isEmpty = System.isnull(sub.entityState);
			if (isEmpty || sub.entityState != EntityState.Deleted) {
				
				this.rows.push(sub);
			}
		}

		this.total = this.rows.length;
	},
	
	/******************************************
	 * 
	 * 新增一条数据
	 * 
	 */	
	add : function(item) {

		this.subs.push(item);
		for ( var i = 0; i < this.rows; i++) {

			item = this.rows[i];
			item.Seq = i + 1;
			if (item.entityState != EntityState.New) {

				item.entityState = EntityState.Persist;
			}
		}
	},

	/******************************************
	 * 
	 * 更新一条数据
	 * 
	 */	
	update : function(item) {

		for ( var i = 0; i < this.rows.length; i++) {
			
			if (this.rows[i].id == item.id) {
				this.rows[i] = item;
				break;
			}
		}

	},
	
	/******************************************
	 * 
	 * 删除一条数据
	 * 
	 */	
	remove : function(item) {

		if (item.entityState == EntityState.New) {

			var index = null;
			for ( var i = 0; i < this.subs.length; i++) {
				
				if (this.subs[i] == item) {
					
					index = i;
					break;
				}
			}

			if (index != null) {
				this.subs.splice(index, 1);
			}
		} else {
			item.entityState = EntityState.Deleted;
		}
	},

	/******************************************
	 * 
	 * 获取行数据在集合中的索引
	 * 
	 */		
	indexofRows : function(item) {

		for ( var i = 0; i < this.rows.length; i++) {
			
			if (item == this.rows[i]) {
				return i;
			}
		}

		return -1;
	},

	/******************************************
	 * 
	 *  删除的保留，其他的顺序根据rows来就行
	 * 
	 */		
	sortSubs : function() {
		
		var deletedItems = new Array();
		for ( var i = 0; i < this.subs.length; i++) {
			
			var item = this.subs[i];
			if (item.entityState == EntityState.Deleted) {
				deletedItems.push(item);
			}
		}

		this.subs.splice(0, this.subs.length);
		for ( var i = 0; i < this.rows.length; i++) {

			this.rows[i].Seq = i + 1;
			this.subs.push(this.rows[i]);
		}

		for ( var i = 0; i < deletedItems.length; i++) {
			
			this.subs.push(deletedItems[i]);
		}
	}
});

/*********************************************************************
 * 
 * 明细表格对应表单的处理器
 * 
 ********************************************************************/
org.netsharp.panda.commerce.DetailPartFormModel = org.netsharp.panda.commerce.FormPartModel.Extends({
    ctor: function () {
    	
        this.base();
    }
});