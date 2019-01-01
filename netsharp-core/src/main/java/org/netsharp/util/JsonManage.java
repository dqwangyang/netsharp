package org.netsharp.util;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.netsharp.json.JacksonObjectMapper;

public class JsonManage {

	private static final Log logger = LogFactory.getLog(JsonManage.class);

	public static String serialize(Object obj) throws JsonGenerationException, JsonMappingException, IOException {

		ObjectMapper mapper = new JacksonObjectMapper();

		String json = mapper.writeValueAsString(obj);

		return json;
	}

	public static String serialize2(Object obj) {
		try {
			return serialize(obj);
		} catch (Exception ex) {
			logger.error("json序列化异常", ex);
		}

		return "";
	}

	public static Object deSerialize(Class<?> type, String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new JacksonObjectMapper();
		Object obj = mapper.readValue(json, type);

		return obj;
	}

	public static Object deSerialize2(Class<?> type, String json) {

		try {
			return deSerialize(type, json);
		} catch (Exception ex) {

			logger.error(" json反序列化异常(" + type.getName() + "):" + json + "\n" + ex.getMessage(),ex);
		}

		return "";
	}

	// 反序列化List类型
	// type为list项目的类型
	public static <T> List<T> deSerializeList(Class<T> type, String json) {

		try {

			ObjectMapper mapper = new JacksonObjectMapper();
			JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, type);
			// 如果是Map类型
			// mapper.getTypeFactory().constructParametricType(HashMap.class,String.class,
			// Bean.class);

			@SuppressWarnings("unchecked")
			List<T> obj = (List<T>) mapper.readValue(json, javaType);

			return obj;
		} catch (Exception ex) {

			ex.printStackTrace();
			logger.error(" json反序列化异常(" + type.getName() + "):" + json + "\n" + ex.getMessage(),ex);
		}

		return null;
	}
}
