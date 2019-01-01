/**  
 * @Title: DataGridColumnGroup.java 
 * @Package org.netsharp.panda.controls.datagrid 
 * @Description: TODO
 * @author hanwei
 * @date 2015-5-30 下午5:36:21 
 * @version V1.0  
 */

package org.netsharp.panda.controls.datagrid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: DataGridColumnGroup
 * @Description: TODO
 * @author hanwei
 * @date 2015-5-30 下午5:36:21
 * 
 */

public class DataGridColumnGroup extends ArrayList<DataGridColumn> {
	private static final long serialVersionUID = 1L;

	private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public static Collection<DataGridColumnGroup> groupby(DataGrid dataGrid) {

		HashMap<String, DataGridColumnGroup> map = new HashMap<String, DataGridColumnGroup>();
		List<DataGridColumn> columns = dataGrid.columns;
		for (DataGridColumn column : columns) {

			String groupName = column.groupName;
			DataGridColumnGroup group = map.get(groupName);
			if (group == null) {
				group = new DataGridColumnGroup();
				{
					group.setGroupName(column.groupName);
				}

				map.put(groupName, group);
			}

			group.add(column);
		}

		return map.values();
	}
}
