package org.xi.multidatasourse.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpMethod;

import java.util.Map;

public interface TransferApiService {
    Object getResponse(String url, HttpMethod method, Object data, Map<String, String> headerMap);
    Object getResponse(String moduleName, String apiName, JSONObject params);
}

