package org.netsharp.panda.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

//$("#grid").datagrid({
//    rowStyler: function(index, row) {
//            
//           if(row && row.quantity){
//
//                  if(parseInt(row.quantity)>0){
//                          
//                          return 'color:red';
//                  }
//           }
//           return 'background-color:#f6f6f6;color:#000000';           
//    }
//})
@Table(name="ui_pa_datagrid_row_styler",header="列表行样式")
public class PRowStyler extends Entity {


	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -7444853018311770675L;

	@Column(name="datagrid_id")
	private Integer datagridId;
	
	@Column(name="row_condition",size=500,header="条件表达式")
	private String rowCondition;
	
	@Column(name="row_style",size=200,header="样式")
	private String rowStyle;

	public String getCondition() {
		return rowCondition;
	}

	public Integer getDatagridId() {
		return datagridId;
	}

	public void setDatagridId(Integer datagridId) {
		this.datagridId = datagridId;
	}

	public String getRowCondition() {
		return rowCondition;
	}

	public void setRowCondition(String rowCondition) {
		this.rowCondition = rowCondition;
	}

	public String getRowStyle() {
		return rowStyle;
	}

	public void setRowStyle(String rowStyle) {
		this.rowStyle = rowStyle;
	}
}
