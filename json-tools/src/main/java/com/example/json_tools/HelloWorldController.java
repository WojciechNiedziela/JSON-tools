package com.example.json_tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * HelloWorldController class is responsible for handling routes under /
 *
 * @version 1.0
 */
@RestController
public class HelloWorldController {

    final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
    /**
     * this method is responsible for handling requests to GET /
     *
     * @return String html explaining usage of / routes
     */
	@GetMapping("/")
	public String index() {
        logger.info("request on GET /");
		// return "JSON Tools";
		return """ 
            <html> JSON Tools
                <body>
                    <h1>JSON Tools</h1>
                    <ul>
                        <li><a href="/minify">Minify JSON</a></li>
                        <li><a href="/pretty">Pretty Print JSON</a></li>
                        <li><a href="/filter">Filter JSON</a></li>
                        <li><a href="/compare">Compare JSON</a></li>
                        <li><a href="/object">Object JSON</a></li>
                    </ul>

                    <p>Use POST requests with JSON body to access functionality.</p>
                </body>
            </html>
        """;
	}

}