package org.xi.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xi.common.model.ResultVo;
import org.xi.study.model.ValidateModel;
import org.xi.study.service.ValidateService;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/validate")
@Validated
public class ValidateController {

    @Autowired
    ValidateService validateService;

    @RequestMapping("/hello")
    public ResultVo<String> hello(ValidateModel model) {
        return validateService.hello(model);
    }

    @RequestMapping("/detail")
    public ResultVo<String> getDetail(@NotNull Integer id) {
        return new ResultVo<>("detail" + id);
    }
}
