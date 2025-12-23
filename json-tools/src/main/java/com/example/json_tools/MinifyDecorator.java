package com.example.json_tools;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MinifyDecorator implements TextTransformer{
    private TextTransformer body;
    private ObjectMapper mapper = new ObjectMapper();

    public MinifyDecorator(TextTransformer b)
    {
        this.body = b;
    }

    @Override
    public String transform(String input)
    {
        try {
            Object json = mapper.readValue(input, Object.class);
            return mapper.writeValueAsString(json);
        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON");
        }
    }


}
