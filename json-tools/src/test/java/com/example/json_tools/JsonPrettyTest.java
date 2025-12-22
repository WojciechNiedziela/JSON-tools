package com.example.json_tools;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

@WebMvcTest(JsonPretty.class)
class JsonPrettyTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsPretty() throws Exception {
        String requestJson = "{\"hello\":\"world\",\"languages\":[\"c\",\"c++\"],\"abc\":{\"yyz\":\"lax\"}}";
        String responseJson = "{\n" +
                "  \"hello\" : \"world\",\n" +
                "  \"languages\" : [ \"c\", \"c++\" ],\n" +
                "  \"abc\" : {\n" +
                "    \"yyz\" : \"lax\"\n" +
                "  }\n" +
                "}";

        mockMvc.perform(post("/pretty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(content().string(responseJson));
    }


    @Test
    void rejectsInvalid() throws Exception {
        String requestJson = "{\"hello\":\"world\",\"yyz\"}";

        mockMvc.perform(post("/pretty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }
}