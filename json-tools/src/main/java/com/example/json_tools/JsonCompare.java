package com.example.json_tools;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JsonCompare {

    /*

     * TODO zwrocic roznice w JSON

     */
    @PostMapping("/compare")
    public Map<String, Object> compare(@RequestBody Map<String, Object> body) {
        return null;
    }
}
