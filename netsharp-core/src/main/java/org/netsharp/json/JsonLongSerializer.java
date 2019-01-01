package org.netsharp.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class JsonLongSerializer extends JsonSerializer<Long> {

	@Override
	public void serialize(Long value, JsonGenerator jgen, SerializerProvider sp) throws IOException, JsonProcessingException {
		String v = null;
		if (value != null) {
			v = value.toString();
		}
		jgen.writeString(v);

	}
}
