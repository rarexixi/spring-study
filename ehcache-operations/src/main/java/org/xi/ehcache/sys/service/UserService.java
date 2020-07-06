package org.xi.ehcache.sys.service;

import org.xi.ehcache.model.UserModel;

public interface UserService {
    UserModel getUserByToken(String authorization, boolean get);
}
