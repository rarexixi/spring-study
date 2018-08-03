package org.xi.study.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.xi.common.model.ResultVo;
import org.xi.common.validate.DataAdd;
import org.xi.study.model.ValidateModel;
import org.xi.study.service.ValidateService;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service("validateService")
@Validated
public class ValidateServiceImpl implements ValidateService {

    @Override
    public ResultVo<String> hello(@Validated({DataAdd.class}) ValidateModel validateModel, @NotNull String message) {
        return new ResultVo<>(validateModel.getName() + " says: " + validateModel.getWords());
    }

    @Override
    public ResultVo<List<ValidateModel>> getList(@NotNull @Validated({DataAdd.class}) List<ValidateModel> validateModel) {
        return new ResultVo<>(validateModel);
    }

    @Override
    public ResultVo<List<ValidateModel>> getList() {
        List<ValidateModel> list = new ArrayList<>();
        list.add(new ValidateModel("xi", "xi"));
        list.add(new ValidateModel("yi", "yi"));
        return new ResultVo<>(list);
    }
}
