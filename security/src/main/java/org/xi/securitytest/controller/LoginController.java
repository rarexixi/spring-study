package org.xi.securitytest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @PostMapping("/login")
    public String login() {
        return "redirect:index.html";
    }

    @PostMapping("/index")
    public String index() {
        return "redirect:index.html";
    }
}
