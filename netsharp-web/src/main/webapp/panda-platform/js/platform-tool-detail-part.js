System.Declare("org.netsharp.web");

org.netsharp.web.PlatformToolDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({
	ctor : function() {
		
		this.base();
		this.newEntity = null;
	},
	selectFileds : function(){

		var metaEntity = this.context.metaEntity;
		if(System.isnull(metaEntity)){

			var grid = $("#resourceNode_pathName").combogrid('grid');
			var row = grid.datagrid('getSelected');
			if(row == null || System.isnull(row.entityId)){

				IMessageBox.info('没有选择资源节点或资源节点没有对应实体');
				return;
			}
			
			metaEntity = this.context.metaEntity = row.entityId;
		}
		
    	var me = this;
    	var $dialog = $('#selectFileds_dialog');
    	if($dialog.length==0){
    		
    		$('<div id="selectFileds_dialog"></div>').appendTo('body');
    		$('<ul id="selectFileds_tree" class="easyui-tree"></ul>').appendTo("#selectFileds_dialog");
    		$('#selectFileds_dialog').dialog({    
    		    title: '选择字段',    
    		    width: 400,    
    		    height: 600,    
    		    closed: false,    
    		    cache: false, 
    		    modal: true,
    		    buttons:[{
    				text:'确定',
    				iconCls:'fa fa-check',
    				handler:function(){
    					
    					$('#selectFileds_dialog').dialog('close');
    				}
    			}]
    		}); 

    		this.invokeService("getFileds", [metaEntity], function(data){
    			
    			me.renderTree(data);
    		});
    	}else{
    		
    		$('#selectFileds_dialog').dialog('open');
    	}
    },
    renderTree:function(data){
    	
    	var me = this;
		$('#selectFileds_tree').tree({
			animate:true,
			lines:true,
			checkbox:function(node){
				
				if(node.state == "closed" || node.children.length>0){
					
					return false;
				}
				
				//这时还没创建dom，所以target为undefined,导致bug
				//存在于明细表格中的选中
				//node.checked = me.existDetailGrid(node);
				return true;
			},
			formatter:function(node){
				
				var text = node.text + ' ['+ node.attributes.filedName +']';
				return text;
			},
			onClick:function(node){
				
				if(node.attributes.entityId != null){
					
					$(this).tree('toggle',node.target);
				}else{
					
					if(node.checkState == 'unchecked'){

						$(this).tree('check',node.target);
					}else{
						$(this).tree('uncheck',node.target);
					}
				}
			},
			onCheck:function(node, checked){
				
				me.onCheck(node, checked);
			},
			onBeforeExpand:function(node){
				
				var childrens = $('#selectFileds_tree').tree('getChildren',node.target);
				if(childrens.length==0){

					me.onBeforeExpand(node);
					return false;
				}else{

					return true;
				}
			},
			onLoadSuccess:function(node, data){
				
				me.syncCheckState(data);
			},
			data:data
		});
    },
    onBeforeExpand:function(node){
    	
		var me = this;
		var refMetaEntity = node.attributes.entityId;
		me.invokeService("getFileds", [refMetaEntity], function(data){
			
			$('#selectFileds_tree').tree('append',{
				parent: node.target,
				data: data
			});
			$('#selectFileds_tree').tree('expand',node.target);
			me.syncCheckState(data);
		});
    },
    onCheck:function(node, checked){
    	
    	if(checked){
    		
    		var exist = this.existDetailGrid(node);
    		if(exist){
    			
    			return;
    		}
    		var me = this;
    		if(this.newEntity == null){

        		this.invokeService("newInstance", [], function(data) {

        			me.newEntity = data;
        			me.selectAdd(node,data);
        		});
    		}else{
    			
        		this.selectAdd(node,this.newEntity);
    		}
    		
    	}else{
    		
    		this.unSelectRemove(node);
    	}
    },
    syncCheckState:function(data){
    	
    	var me = this;
		data.forEach(function(row, index, array) {
			
			var exist = me.existDetailGrid(row);
			if(exist){
				
				var node = $('#selectFileds_tree').tree('find', row.id);
				$('#selectFileds_tree').tree('check',node.target);
			}
		});
    },
    existDetailGrid:function(node){
    	
    	//存在效率问题，数据量不大无关
		var rows = this.getGrid().datagrid('getRows');
		if (rows.length == 0) {
			return false;
		}
		
		var propertyName = this.getFiledName(node);
		console.log(propertyName);
		for(var i=0;i<rows.length;i++){
			
			if(propertyName == rows[i].propertyName){

		    	return true;
			}
		}
		return false;
    },
    unSelectRemove:function(node){
    	
		var $grid = this.getGrid();
		
		//清空所有选中的行
		$grid.datagrid('unselectAll');
		
		var rows = $grid.datagrid('getRows');
		if (rows.length == 0) {
			return;
		}
		
		var propertyName = this.getFiledName(node);
		var me = this;
		rows.forEach(function(row, index, array) {
			
			if(propertyName == row.propertyName){

				rowIndex = $grid.datagrid('getRowIndex',row); 
				$grid.datagrid('deleteRow',rowIndex);
				me.dataSource.remove(row);
			}
		});
    },
    
    selectAdd:function(node,entity){
    	
    	entity = this.createEntity(node,entity);
    	
    	//只有表格才有
    	entity.formatter = node.attributes.formatter;
    	
    	this.getGrid().datagrid('appendRow', entity);
    },
    
    getFiledName:function(node){
    	
    	var parent = $('#selectFileds_tree').tree('getParent',node.target);
    	if(parent != null){
    		
    		return parent.attributes.filedName + '.' + node.attributes.filedName;
    	}else{
    		
    		return node.attributes.filedName;
    	}
    	
    	//表单、表格只支持2级，查询项可支持多级，未处理
    },
    
    createEntity:function(node,entity){
    	
		//处理关联
		eval('entity.'+this.context.foreignKey+'="'+this.referenceId+'"');
		
		entity.propertyName = this.getFiledName(node);
		var parent = $('#selectFileds_tree').tree('getParent',node.target);
		if(parent != null){
			
	    	//默认使用参照控件
			var parent = $('#selectFileds_tree').tree('getParent',node.target);
	    	entity.controlType = parent.attributes.controlType;
		}else{
			
	    	entity.controlType = node.attributes.controlType;
		}
		
    	entity.header = node.text;
    	return entity;
    }
});
