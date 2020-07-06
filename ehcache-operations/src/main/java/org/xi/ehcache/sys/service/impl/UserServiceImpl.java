package org.xi.ehcache.sys.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.xi.ehcache.model.UserModel;
import org.xi.ehcache.sys.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    @Cacheable(value = "dmp:token:user", key = "#authorization")
    public UserModel getUserByToken(String authorization, boolean get) {
        System.out.println("============================" + get);
        if (StringUtils.isBlank(authorization)) return null;
        UserModel user = new UserModel();
        user.setName(authorization);
        return user;
    }
}
