package org.xi.ehcache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xi.ehcache.model.UserModel;
import org.xi.ehcache.sys.service.UserService;


@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    UserService userService;

    @GetMapping("login")
    public Object login() {
        return "login";
    }

    @GetMapping("info")
    public String info() {
        return "hello";
    }

    @GetMapping("detail")
    public UserModel detail(String token) {
        return userService.getUserByToken(token, true);
    }

}
