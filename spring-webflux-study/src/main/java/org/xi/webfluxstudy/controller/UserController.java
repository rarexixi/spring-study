package org.xi.webfluxstudy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xi.webfluxstudy.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {

    static Map<Integer, User> userMap = new HashMap<>();

    @PostConstruct
    public void init() {
        userMap.put(1, new User(1, "rare", "rare@outlook.com"));
        userMap.put(2, new User(2, "xi", "xi@outlook.com"));
        userMap.put(2, new User(3, "xish", "xish@outlook.com"));
    }

    /**
     * 获取所有用户
     *
     * @return
     */
    @GetMapping("/")
    public Flux<User> index() {

        List<User> userList = userMap.entrySet().stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
        return Flux.fromIterable(userList);
    }

    /**
     * 获取单个用户
     *
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public Mono<User> get(@PathVariable Integer id) {

        User user = userMap.getOrDefault(id, null);
        return Mono.justOrEmpty(user);
    }

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    @PutMapping("/put")
    public Mono<ResponseEntity<String>> put(@RequestBody User user) {

        userMap.put(user.getId(), user);
        return Mono.just(new ResponseEntity<>("Put Successfully!", HttpStatus.CREATED));
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @PostMapping("/update")
    public Mono<ResponseEntity<User>> update(@RequestBody User user) {

        userMap.put(user.getId(), user);
        return Mono.just(new ResponseEntity<>(user, HttpStatus.CREATED));
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<String>> delete(@PathVariable Integer id) {

        userMap.remove(id);
        return Mono.just(new ResponseEntity<>("Delete Successfully!", HttpStatus.ACCEPTED));
    }
}