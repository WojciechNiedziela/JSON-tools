package com.example.json_tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.Map;

/**
 * JsonMinify class is responsible for handling routes under /minify
 *
 * @version 1.0
 */
@RestController
public class JsonMinify {

    final Logger logger = LoggerFactory.getLogger(JsonMinify.class);

    /**
     * this method is responsible for handling requests to GET /minify
     *
     * @return String html explaining usage of /minify routes
     */
    @GetMapping("/minify")
    public String minifyPage() {
        logger.info("request on GET /minify");
        return """
            <html>
                <body>
                    <h1>JSON Tools – Minify</h1>
                    <p>Use POST /minify with JSON body.</p>
                </body>
            </html>
        """;
    }

    /**
     * this method is responsible for handling requests to POST /minify
     *
     * @param body body of POST /minify request
     * @return ResponseEntity with status ok and body containing minified json
     * @throws Exception ObjectMapper failed to generate json string
     */
    @PostMapping("/minify")
    public String minify(@RequestBody String body) {
        logger.info("request on POST /minify");

        MinifyDecorator transformer = new MinifyDecorator(new BaseJsonTransformer());

        return transformer.transform(body);
    }

}
