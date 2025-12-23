package com.example.json_tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * JsonCompare class is responsible for handling routes under /compare
 *
 * @version 1.0
 */
@RestController
public class JsonCompare {

    final Logger logger = LoggerFactory.getLogger(JsonCompare.class);

    /**
     * this method is responsible for handling requests to GET /compare
     *
     * @return String html explaining usage of /compare routes
     */
    @GetMapping("/compare")
    public String comparePage() {
        logger.info("request on GET /compare");
        return """
            <html>
                <body>
                    <h1>JSON Tools – compare</h1>
                    <p>Use POST /compare with JSON body:</p>
                    <pre>
{
  "left": { ... },
  "right": { ... }
}
                    </pre>
                </body>
            </html>
        """;
    }

    /**
     * this method is responsible for handling requests to POST /compare
     *
     * @param body body of POST /compare request
     * @return String describing diff between left and right
     */
    @PostMapping("/compare")
    public String compare(@RequestBody String body) {
        logger.info("request on POST /compare");

        TextTransformer transformer =
                new CompareTransformer();

        return transformer.transform(body);
    }
}
