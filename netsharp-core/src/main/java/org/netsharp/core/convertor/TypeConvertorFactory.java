package org.netsharp.core.convertor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.netsharp.core.convertor.impl.BinaryConvertor;
import org.netsharp.core.convertor.impl.BooleanConvertor;
import org.netsharp.core.convertor.impl.DateConvertor;
import org.netsharp.core.convertor.impl.DecimalConvertor;
import org.netsharp.core.convertor.impl.DoubleConvertor;
import org.netsharp.core.convertor.impl.FloatConvertor;
import org.netsharp.core.convertor.impl.GuidConvertor;
import org.netsharp.core.convertor.impl.IntegerConvertor;
import org.netsharp.core.convertor.impl.JavaEnumConvertor;
import org.netsharp.core.convertor.impl.LongConvertor;
import org.netsharp.core.convertor.impl.ShortConvertor;
import org.netsharp.core.convertor.impl.StringConvertor;
import org.netsharp.core.convertor.impl.TimestampConvertor;

public class TypeConvertorFactory {

	private static HashMap<String ,ITypeConvertor> convertors = new HashMap<String, ITypeConvertor>();

	// / <summary>
	// / 创建类型转换器
	// / </summary>
	// / <param name="type">类型</param>
	// / <returns>转换器</returns>
	public static ITypeConvertor create(Class<?> type) {
		Initialize();

		if (type.isEnum()) {
			JavaEnumConvertor c = new JavaEnumConvertor(type);

			return c;
		}

		ITypeConvertor convertor = convertors.get(type.getName());

		return convertor;
	}

	private static void Initialize() {
		if (convertors.size() > 0) {
			return;
		}

		convertors.put(String.class.getName(), new StringConvertor());
		convertors.put(UUID.class.getName(), new GuidConvertor());
		convertors.put(Boolean.class.getName(), new BooleanConvertor());
		convertors.put(boolean.class.getName(), new BooleanConvertor());
		convertors.put(BigDecimal.class.getName(), new DecimalConvertor());
		convertors.put(Date.class.getName(), new DateConvertor());
		convertors.put(java.sql.Date.class.getName(), new DateConvertor());
		convertors.put(Timestamp.class.getName(), new TimestampConvertor());
//		convertors.put(EnumItem.class.getName(), new EnumItemConvertor());
		convertors.put(byte[].class.getName(), new BinaryConvertor());
		convertors.put(Long.class.getName(), new LongConvertor());
		convertors.put(long.class.getName(), new LongConvertor());
		convertors.put(Integer.class.getName(), new IntegerConvertor());
		convertors.put(int.class.getName(), new IntegerConvertor());
		convertors.put(Short.class.getName(), new ShortConvertor());
		convertors.put(short.class.getName(), new ShortConvertor());
		convertors.put(float.class.getName(), new FloatConvertor());
		convertors.put(Float.class.getName(), new FloatConvertor());
		convertors.put(double.class.getName(), new DoubleConvertor());
		convertors.put(Double.class.getName(), new DoubleConvertor());
	}
}
