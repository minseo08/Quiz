package com.example.quiz.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JsonUtility {

    private final ObjectMapper objectMapper;

    public JsonUtility(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<String> parse(String jsonString) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, new TypeReference<List<String>>() {});
    }

    public String toJson(List<String> options) throws JsonProcessingException {
        return objectMapper.writeValueAsString(options);
    }
}
