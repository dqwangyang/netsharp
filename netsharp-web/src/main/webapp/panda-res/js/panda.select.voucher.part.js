
//选择单界面
org.netsharp.panda.commerce.SelectVoucherListPart = org.netsharp.panda.commerce.ListPart.Extends({
	setStyle : function() {
		$("#"+this.context.id).datagrid({height:280});
	},
	onSelect : function(rowIndex, rowData) {
		if (rowData != null) {

			this.currentItem = rowData;

			this.notifyCurrentItemChanged();
		}
	},
	onLoadSuccess:function(data){
		if(data){
			if(data.total>0){
				//默认选中第一行
				$("#"+this.context.id).datagrid("selectRow",0);
			}
		}
	},
	select:function(){
		
		//获取表头id
		var voucherId = this.getSelectionIds();
		
		if(!voucherId){
			IMessageBox.warning("最少选择一张订单");
			return;
		}
		
		//获取明细ids
		var detailIds = this.subs[0].getSelectionIds();
		if(!detailIds){
			IMessageBox.warning("最少选择一条明细");
			return;
		}
		
		var instanceName = this.queryString("instanceName");
		var sourceVoucherType = this.queryString("sourceVoucherType");
		var expression="window.parent."+instanceName+".convertVoucher(sourceVoucherType,voucherId,detailIds);";
		eval(expression);
		
		//关闭当前窗口
		window.parent.IMessageBox.closeWindow();
	}
});