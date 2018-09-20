package org.xi.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xi.common.model.ResultVo;
import org.xi.common.validate.DataAdd;
import org.xi.study.model.ValidateModel;
import org.xi.study.service.ValidateService;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/validate")
@Validated
public class ValidateController {

    @Autowired
    ValidateService validateService;

    @RequestMapping("/hello")
    public ResultVo<String> hello(ValidateModel model) {
        return validateService.hello(model, null);
    }

    @RequestMapping("/hello2")
    public ResultVo<String> hello2(@Validated({DataAdd.class}) ValidateModel model, Errors errors) {
        return validateService.hello(model, null);
    }

    @RequestMapping("/detail")
    public ResultVo<String> getDetail(@NotNull Integer id) {
        return new ResultVo<>("detail" + id);
    }

    @RequestMapping("/list")
    public ResultVo<List<ValidateModel>> getList() {
        List<ValidateModel> list = new ArrayList<>();
        list.add(new ValidateModel("xi", "xi"));
        list.add(new ValidateModel("yi", "yi"));
        return validateService.getList(list);
    }
}
