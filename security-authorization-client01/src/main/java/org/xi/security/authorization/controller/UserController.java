package org.xi.security.authorization.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get-user")
    public Object getUser(Authentication authentication, HttpServletRequest request) {
        return authentication.getPrincipal();
    }
}
