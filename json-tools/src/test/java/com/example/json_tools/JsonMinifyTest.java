package com.example.json_tools;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JsonMinify.class)
class JsonMinifyTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsMinify() throws Exception {
        String requestJson = "{\n" +
                "  \"hello\" : \"world\",\n" +
                "  \"languages\" : [ \"c\", \"c++\" ],\n" +
                "  \"abc\" : {\n" +
                "    \"yyz\" : \"lax\"\n" +
                "  }\n" +
                "}";
        String responseJson = "{\"hello\":\"world\",\"languages\":[\"c\",\"c++\"],\"abc\":{\"yyz\":\"lax\"}}";

        mockMvc.perform(post("/minify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }
}