package com.example.json_tools;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * JsonFilter class is responsible for handling routes under /filter
 *
 * @version 1.0
 */
@RestController
public class JsonFilter {


    /*

     * TODO zwrocic JSON z filtrowana struktura

     */

    /**
     * this method is responsible for handling requests to GET /filter
     *
     * @return String html explaining usage of /filter routes
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


/**
     * this method is responsible for handling requests to POST /filter
     *
     * @param body body of POST /object request
     * @return passed body
     */
    @PostMapping("/filter")
    public Map<String, Object> filter(@RequestBody Map<String, Object> body, @RequestParam String[] properties) {
        return body;
    }

}
