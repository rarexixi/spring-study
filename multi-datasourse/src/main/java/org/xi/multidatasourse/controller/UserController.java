package org.xi.multidatasourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xi.multidatasourse.mapper.User1Mapper;
import org.xi.multidatasourse.mapper2.User2Mapper;
import org.xi.multidatasourse.model.User1;
import org.xi.multidatasourse.model.User2;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private User1Mapper user1Mapper;

    @Autowired
    private User2Mapper user2Mapper;

    @RequestMapping("/getUsers1")
    public List<User1> getUsers1() {
        return user1Mapper.select();
    }

    @RequestMapping("/getUsers2")
    public List<User2> getUsers2() {
        return user2Mapper.select();
    }
}
