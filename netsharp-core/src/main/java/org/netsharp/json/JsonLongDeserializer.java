package org.netsharp.json;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class JsonLongDeserializer extends JsonDeserializer<Long> {

	@Override
	public Long deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		if (jp.getText() == null || "null" == jp.getText().toLowerCase()) {
			return null;
		}
		try {
			return Long.parseLong(jp.getText());
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
