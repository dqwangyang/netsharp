package org.netsharp.application;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.NetsharpException;
import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.core.convertor.TypeConvertorFactory;
import org.netsharp.util.PropertyConfigurer;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

public class ApplicationConfiguration {

	public void deserialize() {
		
		this.deserialize(null);

	}

	public void deserialize(String cat) {
		Class<?> type = this.getClass();

		Configuration confile = type.getAnnotation(Configuration.class);
		if (confile == null) {
			throw new NetsharpException("类上必须标注@Configuration：" + type.getName());
		}

		PropertyConfigurer conf = PropertyConfigurer.newInstance(confile.file());

		Field[] fields = ReflectManager.getDeclaredFields(type);
		for (Field field : fields) {
			this.parseField(confile, conf, field, cat);
		}
	}

	private void parseField(Configuration confile, PropertyConfigurer conf, Field field, String cat) {

		ConfigItem item = field.getAnnotation(ConfigItem.class);
		if (item == null) {
			return;
		}
		
		String key = item.key();
		if(StringManager.isNullOrEmpty(cat)) {
			key = key.replaceAll(".xxx", "");
		}else {
			key = key.replaceAll("xxx", cat);
		}

		String value = conf.get(key);
		if (StringManager.isNullOrEmpty(value)) {
			value = item.defaultValue();
		}

		if (item.required() && StringManager.isNullOrEmpty(value)) {
			throw new NetsharpException(confile.file() + "必须配置" + key);
		}

		ITypeConvertor convertor = TypeConvertorFactory.create(field.getType());
		if (convertor == null) {
			throw new NetsharpException("can't read " + key);
		}
		Object propertyValue = convertor.fromString(value);

		try {
			field.setAccessible(true);
			field.set(this, propertyValue);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new NetsharpException("can't read " + key, e);
		}

		if (field.getType().isEnum() && propertyValue == null) {
			throw new NetsharpException(key + "配置不正确，配置的值为：" + value);
		}
	}

	@Override
	public String toString() {

		Class<?> type = this.getClass();
		List<String> ss = new ArrayList<String>();

		Field[] fields = ReflectManager.getDeclaredFields(type);
		for (Field field : fields) {
			ConfigItem item = field.getAnnotation(ConfigItem.class);
			if (item == null) {
				continue;
			}

			try {
				ss.add(field.getName() + " : " + field.get(this).toString());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return StringManager.join(StringManager.NewLine, ss);
	}
}
