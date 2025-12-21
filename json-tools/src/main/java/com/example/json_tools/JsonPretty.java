package com.example.json_tools;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JsonPretty {


    /*

    * TODO zwrocic zformatowany JSON

    */
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
    public Map<String, Object> pretty(@RequestBody Map<String, Object> body) {
        return body;
    }

}