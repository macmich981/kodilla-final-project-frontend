package com.kodilla.carsfrontend.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class JsonMapper {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public HttpEntity mapToJson(Object object) throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(object);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity httpEntity = new HttpEntity(jsonString, httpHeaders);
        return httpEntity;
    }
}
