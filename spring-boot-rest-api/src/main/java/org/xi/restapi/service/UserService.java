package org.xi.restapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.xi.restapi.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    static Map<Integer, User> userMap = new HashMap<>();

    static {
        userMap.put(1, new User(1, "rare", "rare@outlook.com"));
        userMap.put(2, new User(2, "xi", "xi@outlook.com"));
        userMap.put(3, new User(3, "xish", "xish@outlook.com"));
    }

    public List<User> list() {
        return new ArrayList<>(userMap.values());
    }

    public User detail(Integer id) {
        return userMap.getOrDefault(id, null);
    }

    public User put(User user) {
        userMap.put(user.getId(), user);
        return user;
    }

    public User update(User user) {
        userMap.put(user.getId(), user);
        return user;
    }

    public User patch(User user) {
        userMap.put(user.getId(), user);
        return user;
    }

    public void delete(Integer id) {
        userMap.remove(id);
    }
}
