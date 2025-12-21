package com.example.json_tools;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JsonFilter {


    /*

     * TODO zwrocic JSON z filtrowana struktura

     */

    @GetMapping("/filter")
    public String filterPage() {
        return """
            <html>
                <body>
                    <h1>JSON Tools – filter</h1>
                    <p>Use POST /filter with JSON body.</p>
                </body>
            </html>
        """;
    }


    @PostMapping("/filter")
    public Map<String, Object> filter(@RequestBody Map<String, Object> body, @RequestParam String[] properties) {
        return body;
    }

}
