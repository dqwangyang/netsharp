package org.netsharp.panda.commerce.base.controls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.panda.base.IPDatagridService;
import org.netsharp.panda.controls.datagrid.DataGridColumn;
import org.netsharp.panda.controls.datagrid.JavaEnumBoxColumn;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.util.JavaEnumManager;
import org.netsharp.util.StringManager;

public class PropertyColumnJavaEnumBox extends PropertyColumnTextBox {
	@Override
	public DataGridColumn create(PDatagridColumn dcolumn) {

		JavaEnumBoxColumn javaEnumBoxColumn = new JavaEnumBoxColumn();
		{
			javaEnumBoxColumn.editorWidth = dcolumn.getWidth() - 1;
			javaEnumBoxColumn.required = dcolumn.isRequired();
			PDatagrid pdatagrid = dcolumn.getDatagrid();
			if (pdatagrid == null) {
				IPDatagridService service = ServiceFactory.create(IPDatagridService.class);
				pdatagrid = service.byId(dcolumn.getDatagridId());
			}

			Mtable meta = MtableManager.getMtable(pdatagrid.getEntityId());
			Column column = meta.findProperty(dcolumn.getPropertyName());
			
			if(column==null){
				throw new PandaException("未能创建DataGridColumn:"+dcolumn.getDatagrid().getEntityId()+"."+dcolumn.getPropertyName());
			}

			Class<?> type = column.getType();
			if (!type.isEnum()) {
				String message = dcolumn.getPropertyName() + "字段配置成枚举，但是类型为:" + type.getName();
				throw new PandaException(message);
			}
			Map<String, String> map = JavaEnumManager.enumAsMapList2(type);

			List<String> items = new ArrayList<String>();
			for (String key : map.keySet()) {

				String display = map.get(key);
				items.add("{text: '" + display + "',value: '" + key + "'}");
			}
			javaEnumBoxColumn.data = "[" + StringManager.join(",", items) + "]";

		}
		;
		this.render(javaEnumBoxColumn, dcolumn);
		return javaEnumBoxColumn;
	}
}
