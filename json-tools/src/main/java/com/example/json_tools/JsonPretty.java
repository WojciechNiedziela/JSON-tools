package com.example.json_tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Map;


/**
 * JsonPretty class is responsible for handling routes on /pretty
 *
 * @version 1.0
 */
@RestController
public class JsonPretty {

    final Logger logger = LoggerFactory.getLogger(JsonPretty.class);

    /**
     * this method creates new Object mapper configured to pretty format json
     *
     * @return ObjectMapper configured to return nicely formatted json
     */
    private ObjectMapper prettyMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }

    /**
     * this method is responsible for handling requests to GET /pretty
     *
     * @return String html explaining usage of /pretty routes
     */
   @GetMapping("/pretty")
    public String prettyPage() {
       logger.info("request on GET /pretty");
        return """
            <html>
                <body>
                    <h1>JSON Tools – Pretty</h1>
                    <p>Use POST /pretty with JSON body.</p>
                </body>
            </html>
        """;
    }

    /**
     * this method is responsible for handling requests to POST /pretty
     *
     * @param body body of POST /pretty request
     * @return ResponseEntity with status ok and body containing pretty formated json
     * @throws Exception ObjectMapper failed to generate json string
     */
    @PostMapping("/pretty")
    public ResponseEntity<String>  pretty(@RequestBody Map<String, Object> body) throws Exception {
        logger.info("request on POST /pretty");
        String json = prettyMapper().writeValueAsString(body);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
    }

}