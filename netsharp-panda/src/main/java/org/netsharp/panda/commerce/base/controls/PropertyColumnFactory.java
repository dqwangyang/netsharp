package org.netsharp.panda.commerce.base.controls;

import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.util.ReflectManager;

public class PropertyColumnFactory {
	
	public static IPropertyColumn create(PDatagridColumn dcolumn) {
		IPropertyColumn column = null;
		if (dcolumn.getControlType() == ControlTypes.CUSTOM ) {
			column = (IPropertyColumn) ReflectManager.newInstance(dcolumn.getCustomControlType());
		} else {
			String type = "org.netsharp.panda.commerce.base.controls.PropertyColumn" + dcolumn.getControlType().getName();
			column = (IPropertyColumn) ReflectManager.newInstance(type);
		}

		if (column == null) {
			throw new PandaException("未能创建列:" + dcolumn.getPropertyName() + ";" + dcolumn.getControlType());
		} else {
			return column;
		}
	}
}
