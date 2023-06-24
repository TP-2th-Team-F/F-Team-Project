package com.jojoldu.book.springboot.service.NumBall;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.AttributeConverter;
import java.util.List;

public class ListToJsonConverter implements AttributeConverter<List<String>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (Exception e) {
            // Handle the exception according to your requirements
            throw new RuntimeException("Failed to convert list to JSON string: " + e.getMessage(), e);
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            // Handle the exception according to your requirements
            throw new RuntimeException("Failed to convert JSON string to list: " + e.getMessage(), e);
        }
    }
}
