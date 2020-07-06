package org.xi.multidatasourse.model;

import lombok.Data;

import java.util.Map;

@Data
public class TransferApiModel {
    private Integer id;
    private String url;
    private String requestType;
    private String type;
    private Map<String, TransferApiParamModel> headerParams;
    private Map<String, TransferApiParamModel> queryParams;
    private Map<String, TransferApiParamModel> bodyParams;
    private Map<String, TransferApiParamModel> pathParams;
}
