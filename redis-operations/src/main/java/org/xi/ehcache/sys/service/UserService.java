package org.xi.ehcache.sys.service;

import org.xi.ehcache.UserDetail;

public interface UserService {
    UserDetail add(int id);
    UserDetail detail(int id);
    void delete(int id);
    void cleanCache();
}
