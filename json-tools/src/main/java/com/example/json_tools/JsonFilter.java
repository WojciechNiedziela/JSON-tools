package com.example.json_tools;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JsonFilter {


    /*

     * TODO zwrocic JSON z filtrowana struktura

     */
    @PostMapping("/filter")
    public Map<String, Object> filter(@RequestBody Map<String, Object> body, @RequestParam String[] properties) {
        return null;
    }

}
