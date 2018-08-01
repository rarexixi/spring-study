package org.xi.study.service;

import org.xi.common.model.ResultVo;
import org.xi.study.model.ValidateModel;

public interface ValidateService {
    ResultVo<String> hello(ValidateModel validateModel);
}
