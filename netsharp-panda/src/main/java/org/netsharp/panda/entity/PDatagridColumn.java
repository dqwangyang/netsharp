package org.netsharp.panda.entity;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.panda.dic.AggregateType;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OrderbyMode;

@Table(name = "ui_pa_datagrid_column",header="网格列")
public class PDatagridColumn extends PInputField {

	private static final long serialVersionUID = -340036564414964055L;

	@Column(name = "datagrid_id",header="表格方案Id")
	private Long datagridId;

	@JsonBackReference
	@Reference(foreignKey = "datagridId")
	private PDatagrid datagrid;
	
	@Column(name = "filterable",header="显示过滤")
	protected boolean filterable = false;
	
	@Column(name = "frozen",header="冻结列")
	protected boolean frozen = false;
	
	@Column(name = "orderby_mode",header="排序方式")
	protected OrderbyMode orderbyMode;
	
	@Column(name = "aggregate_type",header="合计类型")
	protected AggregateType aggregateType;
	
	@Column(name = "align",header="横向对齐方式")
	private DatagridAlign align = DatagridAlign.LEFT;

	@Column(name = "styler",size = 500,header="显示样式")
	private String styler;

	@Column(name = "imported",header="导入字段")
	private boolean imported = false;

	@Exclusive
	@JsonIgnore
	private String columnName;

	public String getColumnName() {

		if (columnName == null) {
			if (propertyName.contains(".")) {
				columnName = propertyName.replaceAll("\\.", "_");
			} else {
				columnName = propertyName;
			}
		}

		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public boolean isFilterable() {
		return filterable;
	}

	public void setFilterable(boolean filterable) {
		this.filterable = filterable;
	}

	public boolean isFrozen() {
		return frozen;
	}

	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	public OrderbyMode getOrderbyMode() {
		return this.orderbyMode;
	}

	public PDatagridColumn setOrderbyMode(OrderbyMode orderbyMode) {
		this.orderbyMode = orderbyMode;
		return this;
	}

	public AggregateType getAggregateType() {
		return this.aggregateType;
	}

	public PDatagridColumn setAggregateType(AggregateType aggregateType) {
		this.aggregateType = aggregateType;
		return this;
	}

	public Long getDatagridId() {
		return datagridId;
	}

	public void setDatagridId(Long datagridId) {
		this.datagridId = datagridId;
	}

	public PDatagrid getDatagrid() {
		return datagrid;
	}

	public void setDatagrid(PDatagrid datagrid) {
		this.datagrid = datagrid;
	}

	public DatagridAlign getAlign() {
		return align;
	}

	public void setAlign(DatagridAlign align) {
		this.align = align;
	}

	public String getStyler() {
		return styler;
	}

	public void setStyler(String styler) {
		this.styler = styler;
	}
}