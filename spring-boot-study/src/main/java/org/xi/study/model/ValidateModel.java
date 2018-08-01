package org.xi.study.model;

import org.xi.study.common.validate.DataAdd;

import javax.validation.constraints.NotNull;

public class ValidateModel {

    @NotNull(groups = {DataAdd.class}, message = "姓名不能为空")
    private String name;

    @NotNull(message = "说话不能为空")
    private String words;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
}
