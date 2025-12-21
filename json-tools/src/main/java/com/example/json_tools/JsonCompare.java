package com.example.json_tools;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JsonCompare {

    /*

     * TODO zwrocic roznice w JSON

     */

    @GetMapping("/compare")
    public String comparePage() {
        return """
            <html>
                <body>
                    <h1>JSON Tools – compare</h1>
                    <p>Use POST /compare with JSON body.</p>
                </body>
            </html>
        """;
    }


    @PostMapping("/compare")
    public Map<String, Object> compare(@RequestBody Map<String, Object> body) {
        return body;
    }
}
