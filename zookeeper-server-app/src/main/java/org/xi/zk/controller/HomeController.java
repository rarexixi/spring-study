package org.xi.zk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/hello/{id}")
    public Map<String, Object> hello(@PathVariable("id") Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", "你好");
        return map;
    }
}