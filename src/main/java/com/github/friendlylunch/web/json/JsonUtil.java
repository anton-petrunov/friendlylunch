package com.github.friendlylunch.web.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.friendlylunch.util.exception.IllegalRequestDataException;

import java.util.Map;

public class JsonUtil {

    public static void checkJsonMap(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<Map<String, Integer>> typeReference = new TypeReference<>() {
            };
            mapper.readValue(jsonString, typeReference);
        } catch (JsonProcessingException e) {
            throw new IllegalRequestDataException("Cannot parse JSON. Input data must be in JSON {String: Integer} format");
        }
    }
}
