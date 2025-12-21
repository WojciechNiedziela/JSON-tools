package com.example.json_tools;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JsonMinify {


    /*

     * TODO zwrocic zminifikowany JSON

     */
    @PostMapping("/minify")
    public Map<String, Object> minify(@RequestBody Map<String, Object> body) {
        return null;
    }

}
