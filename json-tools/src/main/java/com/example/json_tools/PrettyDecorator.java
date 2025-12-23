package com.example.json_tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class PrettyDecorator implements TextTransformer {

    private final TextTransformer body;
    private final ObjectMapper mapper;

    public PrettyDecorator(TextTransformer b) {
        this.body = b;
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public String transform(String input) {
        try {
            Object json = mapper.readValue(input, Object.class);
            return mapper.writeValueAsString(json);
        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON");
        }
    }
}