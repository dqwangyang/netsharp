package org.netsharp.wx.sdk.tp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {


    private static final String DEFAULT_DATA = "yyyy-MM-dd HH:mm:ss";

    private static final ThreadLocal<ObjectMapper> mapper = new ThreadLocal<>();
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DEFAULT_DATA);

    /**
     * 对象 -> Json
     *
     * @param obj
     * @param dateFormat 日期格式化，默认yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String objectToJson(Object obj, String... dateFormat) {
        String jsonStr = "";
        if (null != obj) {
            try {
                setDataFormat(dateFormat);
                return getObjectMapper().writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                //log.error(e);
                return jsonStr;
            }
        }
        return jsonStr;
    }

    /**
     * Json -> 对象
     *
     * @param json
     * @param classType  类型
     * @param dateFormat 日期格式化，默认yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static <T> T jsonToObject(String json, Class<T> classType, String... dateFormat) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            setDataFormat(dateFormat);
            return getObjectMapper().readValue(json, classType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> jsonToList(String json, Class classType, String... dateFormat) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            setDataFormat(dateFormat);
            JavaType javaType = getObjectMapper().getTypeFactory().constructParametrizedType(ArrayList.class, List.class, classType);
            return getObjectMapper().readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <K, V> Map<K, V> jsonToMap(String json, Class<K> keyClassType, Class<V> valueClassType, String... dateFormat) {
        if (StringUtils.isBlank(json)) {
            return null;
        }

        try {
            setDataFormat(dateFormat);
            JavaType javaType = getObjectMapper().getTypeFactory().constructParametrizedType(HashMap.class, Map.class, keyClassType, valueClassType);
            return getObjectMapper().readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置日期格式
     *
     * @param dateFormat
     */
    private static void setDataFormat(String... dateFormat) {
        if (ArrayUtils.isEmpty(dateFormat)) {
            getObjectMapper().setDateFormat(DATE_FORMAT);
        } else {
            DateFormat DATE_FORMAT = new SimpleDateFormat(dateFormat[0]);
            getObjectMapper().setDateFormat(DATE_FORMAT);
        }
    }

    private static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = mapper.get();
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.set(objectMapper);
        }
        return mapper.get();
    }

    public static <T> T cloneObj(T t, Class<T> classType) {
        return cloneObj(t, classType, DEFAULT_DATA);
    }

    public static <T> T cloneObj(T t, Class<T> classType, String dateFormat) {
        return jsonToObject(objectToJson(t, dateFormat), classType, dateFormat);
    }


}
