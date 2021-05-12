package org.xi.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.xi.common.validate.DataAdd;
import org.xi.study.model.ValidateModel;
import org.xi.study.service.ValidateService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/validate")
@Validated
public class ValidateController {

    @Autowired
    ValidateService validateService;

    @PostMapping("/hello")
    public ResponseEntity<String> hello(@Valid @RequestBody ValidateModel model) {
        return ResponseEntity.ok(validateService.hello(model, null));
    }

    @PostMapping("/hello2")
    public ResponseEntity<String> hello2(@Validated({DataAdd.class}) @RequestBody ValidateModel model) {
        return ResponseEntity.ok(validateService.hello(model, model.getWords()));
    }

    @GetMapping("/detail")
    public ResponseEntity<String> getDetail(@NotNull Integer id) {
        return ResponseEntity.ok("detail" + id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ValidateModel>> getList() {
        return ResponseEntity.ok(validateService.getList());
    }
}
