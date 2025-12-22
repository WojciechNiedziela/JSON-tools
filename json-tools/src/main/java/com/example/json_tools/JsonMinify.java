package com.example.json_tools;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.Map;

@RestController
public class JsonMinify {


    /*

     * TODO zwrocic zminifikowany JSON

     */

    @GetMapping("/minify")
    public String minifyPage() {
        return """
            <html>
                <body>
                    <h1>JSON Tools – Minify</h1>
                    <p>Use POST /minify with JSON body.</p>
                </body>
            </html>
        """;
    }


    @PostMapping("/minify")
    public String minify(@RequestBody Map<String, Object> body) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(body);
    }

}
