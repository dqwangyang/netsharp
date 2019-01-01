package org.netsharp.json;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class JsonTimeStampDeserializer extends JsonDeserializer<Date> {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat mdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat ldf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (jp.getText() == null) {
            return null;
        }
        try {
            return ldf.parse(jp.getText());
        } catch (ParseException e) {
        	try{
        		return mdf.parse(jp.getText());
        	}catch(ParseException e2){
	            try {
	                return sdf.parse(jp.getText());
	            } catch (ParseException e3) {
	                return null;
	            }
        	}
        }
    }
}
