package org.netsharp.panda.controls.query;

import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.util.ReflectManager;

public class PropertyQueryControlFactory {

	public static IPropertyQueryControl create(PQueryItem queryItem) {
		if (queryItem.getControlType() == ControlTypes.CUSTOM) {
			return (IPropertyQueryControl) ReflectManager.newInstance(queryItem
					.getCustomControlType());
		} else {
			String type = "org.netsharp.panda.controls.query.PropertyQuery"
					+ queryItem.getControlType().getName();

			return (IPropertyQueryControl) ReflectManager.newInstance(type);
		}
	}
}
