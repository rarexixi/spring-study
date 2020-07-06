package org.xi.test;

import lombok.Data;

@Data
public class Rule {
    private String className;
    private String fieldName;
    private String condition;
    private String value;
    private Integer type;
    private Double score;
}
