package org.xi.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.xi.restapi.exception.DataNotFoundException;
import org.xi.restapi.model.User;
import org.xi.restapi.model.UserSearchRequestModel;
import org.xi.restapi.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 获取所有用户
     *
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<List<User>> index(@Valid UserSearchRequestModel userSearchRequestModel) {
        List<User> list = userService.list();
        return ResponseEntity.ok(list);
    }

    /**
     * 获取单个用户
     *
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity<User> detail(@NotNull @Min(value = 1, message = "用户id必须大于0") @PathVariable Integer id) {
        User user = userService.detail(id);
        if (user == null) throw new DataNotFoundException("数据不存在");
        return ResponseEntity.ok(user);
    }

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<User> add(@Valid @RequestBody User user) {
        User detail = userService.put(user);
        return ResponseEntity.ok(detail);
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<User> update(@Valid @RequestBody User user) {
        User detail = userService.update(user);
        return ResponseEntity.ok(detail);
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @PatchMapping("/patch/{id}")
    public ResponseEntity<User> patch(@Valid @RequestBody User user) {
        User detail = userService.patch(user);
        return ResponseEntity.ok(detail);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * 删除用户
     *
     * @return
     */
    @PostMapping("/export")
    public void export() {
    }
}