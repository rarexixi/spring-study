package org.xi.multidatasourse.model;

import lombok.Data;

@Data
public class TransferApiParamModel {
    private String paramName;
    private Integer paramPosition;
    private String requireParam;
    private String defaultValue;
}
