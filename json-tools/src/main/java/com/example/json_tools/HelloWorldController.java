package com.example.json_tools;

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

    /**
     * this method is responsible for handling requests to GET /
     *
     * @return String html explaining usage of / routes
     */
	@GetMapping("/")
	public String index() {
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