package org.netsharp.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class JsonBigDecimalSerializer extends JsonSerializer<BigDecimal> {

	@Override
	public void serialize(BigDecimal value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {

        String v = "";
        if (value != null) {
        	
        	BigDecimal result = value.divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
        	DecimalFormat decimalFormat=new DecimalFormat("0.00");
            v = decimalFormat.format(result);
        }
        jgen.writeString(v);
	}

}
