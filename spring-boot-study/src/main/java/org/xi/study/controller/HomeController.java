package org.xi.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @RequestMapping("")
    public String index(String name, @RequestParam("sessionId") String sessionId) {
        if (name == null) {
            int i = 0 / 0;
        }
        return "hello";
    }
}
