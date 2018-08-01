package org.xi.study.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.xi.common.model.ResultVo;
import org.xi.common.validate.DataAdd;
import org.xi.study.model.ValidateModel;
import org.xi.study.service.ValidateService;

@Service("validateService")
public class ValidateServiceImpl implements ValidateService {

    public ResultVo<String> hello(@Validated({DataAdd.class}) ValidateModel validateModel) {
        return new ResultVo<>(validateModel.getName() + " says: " + validateModel.getWords());
    }
}
