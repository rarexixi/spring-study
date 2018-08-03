package org.xi.study.service;

import org.springframework.validation.annotation.Validated;
import org.xi.common.model.ResultVo;
import org.xi.common.validate.DataAdd;
import org.xi.study.model.ValidateModel;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ValidateService {
    ResultVo<String> hello(@Validated({DataAdd.class}) ValidateModel validateModel, @NotNull String message);
    ResultVo<List<ValidateModel>> getList(@NotNull @Validated({DataAdd.class}) List<ValidateModel> validateModel);
    ResultVo<List<ValidateModel>> getList();
}
