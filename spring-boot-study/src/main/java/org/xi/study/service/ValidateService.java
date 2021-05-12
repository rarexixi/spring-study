package org.xi.study.service;

import org.springframework.validation.annotation.Validated;
import org.xi.common.model.ResultVo;
import org.xi.common.validate.DataAdd;
import org.xi.study.model.ValidateModel;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ValidateService {
    String hello(@Validated({DataAdd.class}) ValidateModel validateModel, @NotNull String message);
    List<ValidateModel> getList();
}
