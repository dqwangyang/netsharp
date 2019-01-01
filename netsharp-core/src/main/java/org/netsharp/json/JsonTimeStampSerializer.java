package org.netsharp.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class JsonTimeStampSerializer extends JsonSerializer<Date> {
	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	@Override
	public void serialize(Date date, JsonGenerator jgen, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		String v = "";
		if (date != null) {
			v = sdf.format(date);
		}
		jgen.writeString(v);
	}
}