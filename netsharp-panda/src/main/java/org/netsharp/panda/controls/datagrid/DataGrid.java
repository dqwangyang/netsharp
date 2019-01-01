package org.netsharp.panda.controls.datagrid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.controls.table.TH;
import org.netsharp.panda.controls.table.TR;
import org.netsharp.panda.controls.table.Table;
import org.netsharp.panda.controls.table.Thead;
import org.netsharp.util.StringManager;

public class DataGrid extends Table {
	
	@HtmlAttr(html = "pageSize")
	public int pageSize;

	@HtmlAttr(html = "title")
	public String title;

	@HtmlAttr(html = "pageList")
	public String pageList;

	@HtmlAttr(html = "singleSelect")
	public boolean singleSelect;

	@HtmlAttr(html = "pagination")
	public boolean pagination;

	@HtmlAttr(html = "rownumbers")
	public boolean rownumbers;

	@HtmlAttr(html = "fitColumns")
	public boolean fitColumns;

	@DataOption(html = "iconCls")
	public String iconCls;
	
	@DataOption(html = "idField")
	public String idField = "id";
	
	@DataOption(html = "nowrap")
	public boolean nowrap = true;

	@DataOption(html = "emptyMsg")
	public String emptyMsg = "暂无记录";
	
	@HtmlAttr(html = "toolbar")
	public String toolbar;

	@HtmlAttr(html = "url")
	public String url;

	@HtmlAttr(html = "grouped")
	public boolean grouped;

	@DataOption(html = "height")
	public int height;

	@DataOption(html = "selectOnCheck")
	public boolean selectOnCheck;

	@DataOption(html = "checkOnSelect")
	public boolean checkOnSelect;

	@DataOption(html = "remoteSort")
	public boolean remoteSort;

	@DataOption(html = "striped")
	public boolean striped = true;
	
	@DataOption(html = "showFooter")
	public boolean showFooter;

	@DataOption(html = "relevanceField")
	public String relevanceField;

	@DataOption(html = "onDblClickRow", isEvent = true)
	public String onDblClickRow;

	@DataOption(html = "onSelect", isEvent = true)
	public String onSelect;

	@DataOption(html = "onLoadSuccess", isEvent = true)
	public String onLoadSuccess;

	@DataOption(html = "onClickCell", isEvent = true)
	public String onClickCell;

	@DataOption(html = "onAfterEdit", isEvent = true)
	public String onAfterEdit;
	
	@DataOption(html = "onResizeColumn", isEvent = true)
	public String onResizeColumn;
	
	@DataOption(html = "groupField", isEvent = true)
	public String groupField;
	
	@DataOption(html = "onRowContextMenu", isEvent = true)
	public String onRowContextMenu;
	
	@DataOption(html = "onHeaderContextMenu", isEvent = true)
	public String onHeaderContextMenu;
	
	@DataOption(html = "groupFormatter")
	public String groupFormatter;
	
	@DataOption(html = "rowStyler", isEvent = true)
	public String rowStyler;
	
	public ArrayList<DataGridColumn> columns = new ArrayList<DataGridColumn>();

	@Override
	public void initialize() {
		
		
		this.setClassName("easyui-datagrid");

		// 普通列
		Thead thead = new Thead();
		this.getControls().add(thead);

		TR tr = new TR();
		thead.getControls().add(tr);

		// 固定列
		Thead frozenThead = new Thead();
		frozenThead.frozen = true;

		TR frozenTr = new TR();
		frozenThead.getControls().add(frozenTr);

		TR groupTr = new TR();

		// 记录第一行的分组列
		List<TH> groupTHs = new ArrayList<TH>();

		// 合并列
		Collection<DataGridColumnGroup> groups = DataGridColumnGroup.groupby(this);

		// 存在分组
		int size = groups.size();
		TH th = null;

		for (DataGridColumn column : columns) {

			if (column == null) {
				continue;
			}

			if (size >= 2) {
				column.rowspan = 2;
			}

			if (column.frozened) {
				column.rowspan=null;
				// 冻结列
				frozenTr.getControls().add(column);

			} else if (StringManager.isNullOrEmpty(column.groupName)) {
				// 未分组列
				tr.getControls().add(column);
			} else {
				// 分组列
				if (size >= 2) {
					for (DataGridColumnGroup info : groups) {
						String groupName = info.getGroupName();
						if (!StringManager.isNullOrEmpty(groupName) && column.groupName.equals(groupName)) {

							if (this.isAddTh(groupTHs, groupName)) {
								// 如果已经添加，不再添加。
							} else {
								th = new TH();
								{
									th.value = info.getGroupName();
									th.colspan = info.size();
								}
								tr.getControls().add(th);
								groupTHs.add(th);
							}
						}
					}
				}

				column.rowspan = null;
				groupTr.getControls().add(column);
			}
		}

		if (groupTr.getControls().size() > 0) {
			thead.getControls().add(groupTr);
		}

		if (frozenTr.getControls().size() > 0) {
			this.getControls().add(frozenThead);
		}
		super.initialize();
	}

	/**
	 * @Title: isAddTh
	 * @Description: TODO
	 * @param @param groupTHs
	 * @param @param groupName
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	private Boolean isAddTh(List<TH> groupTHs, String groupName) {
		for (TH th : groupTHs) {
			if (th.value.equals(groupName)) {
				return true;
			}
		}
		return false;
	}
}
