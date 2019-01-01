package org.netsharp.core.property;

import org.netsharp.base.IEnum;

public class JavaEnumProperty extends EntityProperty {

	private static final long serialVersionUID = -1677726550464020240L;

	public Object dbField(Object owner) {

		try {
			IEnum obj = (IEnum) field.get(owner);
			if (obj != null) {

				Object value = obj.getValue();
				return value;
			}

		} catch (Exception e) {

			logger.error(type.getName() + "." + owner + "set异常", e);
		}

		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void dbField(Object owner, Object value) {

		try {
			if (value != null) {

				Class<Enum> cl = (Class<Enum>) type;
				for (Object item : cl.getEnumConstants()) {

					IEnum enumItem = (IEnum) item;
					if (enumItem.getValue().equals(value)) {
						field.set(owner, item);
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error(type.getName() + "." + value + "set异常", e);
		}
	}
}