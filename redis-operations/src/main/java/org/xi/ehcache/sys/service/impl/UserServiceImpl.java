package org.xi.ehcache.sys.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.xi.ehcache.UserDetail;
import org.xi.ehcache.sys.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @CachePut(value = "user-detail", key = "#id")
    @Override
    public UserDetail add(int id) {
        System.out.println("add============================");
        return new UserDetail(id, "rere2");
    }

    @Cacheable(value = "user-detail", key = "#id")
    @Override
    public UserDetail detail(int id) {
        System.out.println("detail============================");
        return new UserDetail(id, "rere");
    }

    @CacheEvict(value = "user-detail", key = "#id")
    @Override
    public void delete(int id) {
        System.out.println("delete============================");
    }

    @CacheEvict(value = "user-detail", allEntries=true)
    @Override
    public void cleanCache() {
    }
}
