package org.netsharp.json;

import java.util.Date;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

/*
 * 对jackson的对象序列化器进行包装
 * 主要解决日期的序列化格式
 * jackson默认日期的序列化是一个long数字，不符合中文的习惯如“2015-12-03”,使用这个类之后不需要在实体上配置单独的序列化的方式了
 * */
public class JacksonObjectMapper extends ObjectMapper {
	public JacksonObjectMapper() {

		SimpleModule module = new SimpleModule("ns-date", new Version(1, 0, 0, "netsharp"));
		{
			// yyyy-MM-dd HH:mm:ss
			module.addSerializer(Date.class, new JsonTimeStampSerializer());
			module.addDeserializer(Date.class, new JsonTimeStampDeserializer());

//			// yyyy-MM-dd HH:mm
//			module.addSerializer(Date.class, new JsonDateTimeSerializer());
//			module.addDeserializer(Date.class, new JsonDateTimeDeserializer());
//
//			// yyyy-MM-dd
//			module.addSerializer(Date.class, new JsonDateSerializer());
//			module.addDeserializer(Date.class, new JsonDateDeserializer());

			//module.addSerializer(BigDecimal.class, new JsonBigDecimalSerializer());

			module.addSerializer(Long.class, new JsonLongSerializer());
			module.addDeserializer(Long.class, new JsonLongDeserializer());

		}
		

		this.registerModule(module);

		this.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		this.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// this.configure(org.codehaus.jackson.map.SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,false);

	}
}