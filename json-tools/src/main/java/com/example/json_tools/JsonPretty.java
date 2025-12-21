package com.example.json_tools;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Map;

@RestController
public class JsonPretty {


    private ObjectMapper prettyMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }

   @GetMapping("/pretty")
    public String prettyPage() {
        return """
            <html>
                <body>
                    <h1>JSON Tools – Pretty</h1>
                    <p>Use POST /pretty with JSON body.</p>
                </body>
            </html>
        """;
    }

    @PostMapping("/pretty")
    public ResponseEntity<String>  pretty(@RequestBody Map<String, Object> body) throws Exception {
        String json = prettyMapper().writeValueAsString(body);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
    }

}