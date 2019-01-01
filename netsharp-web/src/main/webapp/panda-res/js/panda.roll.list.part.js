
//这个列表支持弹出表单的上下翻页
//列表要支持弹出表单的上下翻页，那么需要在触发弹出的方法里要先调用initRollPage方法。
//在弹出表单要预制好工具栏next.prev
//表单也要继承翻页表单
org.netsharp.panda.commerce.RollgridPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
	},
	//初始化表单翻页数据
	initRollPage : function() {
		var sel = this.getSelectedItem();
		if (sel) {
			this.context.pos = $("#" + this.context.id).datagrid(
					'getRowIndex', sel);
		}else{
			this.context.pos = null;//记录当前翻页行的位置索引
		}
		//缓存当前列表选中的所有行
		this.pageRows = $("#" + this.context.id).datagrid('getRows');
		
		//缓存当前的控制器
		window.controller=this;
		//return pageRows;
	},
	getPageDataPos : function() {
		return this.context.pos ;
	},
	setPageDataPos : function(p) {
		this.context.pos = p;
		
		//取消所有选择
		$("#" + this.context.id).datagrid('uncheckAll');
		//按照位置设置当前行
		$("#" + this.context.id).datagrid('selectRow',p);
	},
	/**
	 * 是否是当前页的最后一行
	 */
	isLastRowInPage:function(){
		if(this.getPageDataPos()==this.pageRows.length-1)
			return true;
		else
			return false;
	},
	/**
	 * 分页列表删除的与表单翻页删除的同步
	 */
	deleteCurrentRowInPage:function(){
		var curPos=this.getPageDataPos();
		if(!this.isLastRowInPage()){
			//选中下一行
			$("#" + this.context.id).datagrid('selectRow',++curPos);
		
		}else{
			if(curPos!=0)
		        $("#" + this.context.id).datagrid('selectRow',--curPos);
			else
				$("#" + this.context.id).datagrid('selectRow',0);
			
		}	
		$("#" + this.context.id).datagrid("deleteRow",curPos);
		//删除后重新初始化列表页，不去服务器抓数据
		this.initRollPage();
	},
	//获取当前选中行的id值，为表单服务
	getCurrentId:function(){
		return this.pageRows[this.context.pos].id;
	},
	//获取下一行
	getNextId:function(){
		if(this.context.pos<=this.pageRows.length-1){
			if(this.context.pos!=this.pageRows.length-1){
				this.context.pos+=1;
			}else{
				//this.context.pos-=1;
			}
			
			this.setPageDataPos(this.context.pos);
			return this.getCurrentId();
		}else{
			return null;
		}
		
	},
	//获取上一行
	getPrevId:function(){
		if(this.context.pos>=0){
			if(this.context.pos!=0){
				this.context.pos-=1;
			}
			else{
				
			}
			this.setPageDataPos(this.context.pos);
			return this.getCurrentId();
		}else{
			return null;
		}
	},
	getListController:function(){
		
		return window.controller;
	}
});
