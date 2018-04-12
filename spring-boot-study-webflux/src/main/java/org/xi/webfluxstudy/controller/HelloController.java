package org.xi.webfluxstudy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xi.webfluxstudy.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("controller")
public class HelloController {
    Map<Integer, User> users = new HashMap<>();

    @PostConstruct
    public void init() {
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("rare");
        user1.setEmail("rare@outlook.com");
        User user2 = new User();
        user2.setId(2);
        user2.setUsername("xi");
        user2.setEmail("xi@outlook.com");
        users.put(1, user1);
        users.put(2, user2);
    }

    /**
     * 获取所有用户
     *
     * @return
     */
    @GetMapping("/list")
    public Flux<User> getAll() {

        return Flux.fromIterable(users.entrySet().stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList()));
    }

    /**
     * 获取单个用户
     *
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public Mono<User> getUser(@PathVariable Integer id) {
        User user1 = new User();
        user1.setId(id);
        user1.setUsername("rare");
        user1.setEmail("rare@outlook.com");
        return Mono.justOrEmpty(user1);
    }

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    @PutMapping("/put")
    public Mono<ResponseEntity<String>> putUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return Mono.just(new ResponseEntity<>("Put Successfully!", HttpStatus.CREATED));
    }

    /**
     * 修改用户
     *
     * @param id
     * @param user
     * @return
     */
    @PostMapping("/post/{id}")
    public Mono<ResponseEntity<User>> postUser(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        users.put(id, user);
        return Mono.just(new ResponseEntity<>(user, HttpStatus.CREATED));
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<String>> deleteMethod(@PathVariable Integer id) {
        users.remove(id);
        return Mono.just(new ResponseEntity<>("Delete Successfully!", HttpStatus.ACCEPTED));
    }
}