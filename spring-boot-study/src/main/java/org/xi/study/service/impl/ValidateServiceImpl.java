package org.xi.study.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.xi.study.model.ValidateModel;
import org.xi.study.service.ValidateService;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service("validateService")
@Validated
public class ValidateServiceImpl implements ValidateService {

    @Override
    public String hello(ValidateModel validateModel, @NotNull(message = "service 层，消息不能为空") String message) {
        return validateModel.getName() + " says: " + validateModel.getWords();
    }

    @Override
    public List<ValidateModel> getList() {
        List<ValidateModel> list = new ArrayList<>();
        list.add(new ValidateModel("xi", "xi", 1));
        list.add(new ValidateModel("yi", "yi", 1));
        return list;
    }
}
