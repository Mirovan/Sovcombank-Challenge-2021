package ru.bigint.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MapperUtil<T> {
    private Class<T> classType;

    public MapperUtil(Class<T> classType) {
        this.classType = classType;
    }

    public T jsonToObject(String body) {
        T obj = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper()
                    .findAndRegisterModules()
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);;
            obj = objectMapper.readValue(body, classType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public String objectToJson(T obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        String res = null;
        try {
            res = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }
}
