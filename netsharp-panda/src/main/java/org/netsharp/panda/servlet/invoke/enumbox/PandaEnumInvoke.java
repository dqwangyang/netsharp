package org.netsharp.panda.servlet.invoke.enumbox;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.base.IEnum;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.comunication.IRequest;
import org.netsharp.panda.json.EnumResultJson;
import org.netsharp.panda.servlet.filters.LogFilter;
import org.netsharp.panda.servlet.invoke.PandaContext;
import org.netsharp.panda.servlet.invoke.PandaInvoke;
import org.netsharp.panda.servlet.invoke.PandaInvokeType;
import org.netsharp.util.JsonManage;
import org.netsharp.util.OrdinalMap;

public class PandaEnumInvoke extends PandaInvoke {

	private static final Log logger = LogFactory.getLog( PandaEnumInvoke.class.getName());

	public PandaEnumInvoke() {

		this.filters.add(new LogFilter(PandaInvokeType.enumbox));

	}

	public void doProcessRequest(PandaContext pandaContext) {

		HttpContext context = HttpContext.getCurrent();
		IRequest request = context.getRequest();
		String enumName = request.getParameter("name");
		String json;
		try {
			json = this.getEnumItems(enumName);
			context.getWriter().write(json);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private String getEnumItems(String enumName) throws ClassNotFoundException {

		Class<?> type = Class.forName(enumName);
		OrdinalMap<String, String> maps = new OrdinalMap<String, String>();
		Enum<?>[] enumElems = (Enum<?>[]) type.getEnumConstants();
		Field textField = null;
		try {
			textField = type.getDeclaredField("text");
		} catch (Exception ex) {
			String message = "枚举" + type.getName() + "没有text属性";
			logger.error(message);
		}

		List<EnumResultJson> list = new ArrayList<EnumResultJson>();
		EnumResultJson item = null;
		for (int i = 0; i < enumElems.length; i++) {
			IEnum enumElem = (IEnum) enumElems[i];
			String name = enumElem.getValue().toString();
			String text = name;
			if (textField != null) {
				try {
					textField.setAccessible(true);
					text = (String) textField.get(enumElem);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			maps.put(name, text);
			item = new EnumResultJson();
			{
				item.setValue(name);
				item.setText(text);
			}
			list.add(item);
		}

		String json = JsonManage.serialize2(list);
		return json;
	}
}
