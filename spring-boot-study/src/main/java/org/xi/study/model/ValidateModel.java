package org.xi.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.xi.common.validate.DataAdd;
import org.xi.study.validation.constraints.ListValue;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateModel {

    @NotNull(groups = {DataAdd.class}, message = "姓名不能为空")
    private String name;

    @NotNull(message = "说话不能为空")
    private String words;

    @ListValue(vals = {0,1}, groups = {DataAdd.class})
    private Integer val;
}
