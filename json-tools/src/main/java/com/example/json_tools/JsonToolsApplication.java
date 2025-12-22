package com.example.json_tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JsonToolsApplication starts spring aplication
 *
 * @version 1.0
 */
@SpringBootApplication
public class JsonToolsApplication {

	/** 
	 * main function
	*/
	public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(JsonToolsApplication.class);
        logger.info("starting JsonToolsApplication");
        SpringApplication.run(JsonToolsApplication.class, args);
	}

	

}
