package org.xi.ehcache.sys.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.xi.ehcache.UserDetail;
import org.xi.ehcache.sys.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Cacheable(value = "user-detail", key = "#id")
    public UserDetail detail(int id) {
        System.out.println("============================1");
        return new UserDetail("hello", "rere");
    }
}
